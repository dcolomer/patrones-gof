package creacionales.objectpool;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map.Entry;

/**
 * Pool de conexiones abstracto.
 * Puede gestionar cualquier tipo de recurso. Las clases de 
 * implementaci�n deberan proporcionar el tipo T a cachear.
 * 
 * Incluye el c�digo fundamental para la gesti�n del pool
 * que es v�lido para cualquier subclase. Utiliza dos tablas 
 * Hash, una para almacenar objetos en uso y otra para 
 * almacenar objetos disponibles.
 * 
 * -Funcionamiento de la tabla de objetos disponibles:
 * Cuando un usuario solicita un objeto y todav�a queda alguno
 * en la tabla de disponibles, se quita de la tabla de 
 * disponibles, se pone en la tabla de objetos en uso y se le 
 * entrega al usuario para que lo utilice.
 * 
 * -Funcionamiento de la tabla de objetos  en uso:
 * Cuando el usuario ya no utiliza m�s un objeto, lo devuelve 
 * al pool, esto es, se quita de la tabla de objetos en uso y 
 * se a�ade a la de objetos disponibles.
 * 
 */

// T = java.sql.Connection (en nuestro ejemplo)
public abstract class ObjectPool<T> {
		
	/*
	 * Creamos dos tablas Hash, una para almacenar objetos
	 * bloqueados y otra para objetos disponibles.
	 * -La clave de la tabla es el objeto T, esto es, el recurso,
	 * que en este caso es una Conexion.
	 * -El valor es un Long que representa el instante temporal
	 * en el que el recurso se ha a�adido a la tabla.
	 */		
	protected Hashtable<T, Long> en_uso, disponibles;

	// Tiempo m�ximo que una objeto puede permanecer en el pool
	private long expirationTime;
	
	/**
	 * Constructor
	 */
	public ObjectPool(int expirationTime_) {
		expirationTime = expirationTime_;
		
		// Inicializar las tablas
		en_uso = new Hashtable<T, Long>();
		disponibles = new Hashtable<T, Long>();				
	}

	/*
	 * M�todos abstractos que deben implementar las subclases
	 */

	// Obtiene un objeto desde el Entorno del recurso.
	// Carga bajo demanda (adquisicion perezosa)
	protected abstract T crear();

	protected abstract boolean validar(T o);

	protected abstract void expirar(T o);
				
	/*
	 * Metodo sincronizado que permite al usuario adquirir una conexion.
	 */
	public synchronized T adquirir() {			
		T t; // 't' es una java.sql.Connection
		
		// Si hay objetos disponibles
		if (!disponibles.isEmpty()) {
			// Obtener las claves de la tabla de objetos disponibles
			Enumeration<T> e = disponibles.keys();
			// Recorrer la tabla de objetos disponibles
			while (e.hasMoreElements()) {
				t = e.nextElement();
				// Si el objeto actual ha superado el tiempo maximo de 
				// permanencia en la tabla, hay que desalojarlo de ella 				
				if (haExpirado(t)) {
					// Quitarlo de la tabla de disponibles
					disponibles.remove(t);
					/*
					 * Liberar el recurso --> cerrar la Conexion.
					 * Hay que advertir que la implementacion del metodo 
					 * expire() est� en alguna subclase de esta clase abstracta.					 
					 */
					expirar(t);
					
					// Lista para el recolector de basura
					t = null;
					
				} else { // Si el objeto actual tiene una vigencia adecuada 
					
					/* 
					 * Hay que advertir que la implementacion del metodo 
					 * validate() est� en alguna subclase de esta clase abstracta.					 
					 */
					
					// Si la conexi�n aun est� abierta
					if (validar(t)) {
						// Quitarlo de la tabla de disponibles
						disponibles.remove(t);
						
						// Ponerlo en la tabla de bloqueados
						en_uso.put(t, ahora());
						
						// Devolverlo al usuario
						return (t);
						
					} else { // La conexi�n est� cerrada
						// Quitarlo de la tabla de disponibles
						disponibles.remove(t);
						
						/*
						 * Liberar el recurso --> cerrar la Conexion.
						 * Hay que advertir que la implementacion del metodo 
						 * expire() est� en alguna subclase de esta clase abstracta.					 
						 */
						expirar(t);
						
						// Lista para el recolector de basura
						t = null;
					}
				}
			}
		}

		/*
		 * No hay objetos en la tabla de disponibles, se debe crear uno nuevo.
		 * Hay que advertir que la implementacion del metodo 
		 * create() est� en alguna subclase de esta clase abstracta.
		 * El m�todo accedar� al Entorno del recurso para obtenerlo.
		 */
		t = crear();
		
		// A�adirlo en la tabla de bloqueados
		en_uso.put(t, ahora());
		
		// Devolverlo al usuario
		return (t);
	}

	/*
	 * Metodo sincronizado que permite al usuario devolver una conexion al pool.
	 */
	public synchronized void devolver(T t) {	
		// Quitarlo de la tabla de 'en uso'
		en_uso.remove(t);
		
		// Ponerlo en la tabla de disponibles
		disponibles.put(t, ahora());
	}
	
	/*
	 * Los tres siguientes m�todos permiten conocer 
	 * el estado del pool
	 */
	public int getDisponiblesEnPool() {
		return disponibles.size();		
	}
	
	public int getOcupadosEnPool() {
		return en_uso.size();		
	}
	
	public int getOcupacionTotal() {		
		return getDisponiblesEnPool() + getOcupadosEnPool();
	}
	
	/*
	 * Metodo para liberar todos los recursos del pool.
	 * La aplicacion cliente lo puede invocar antes de terminar. 
	 */
	public void liberarTodo() {
		for (Entry<T, Long> e: disponibles.entrySet()) {
			T t = e.getKey();
			expirar(t);
			t = null;
		}
		disponibles.clear();
		
		for (Entry<T, Long> e: en_uso.entrySet()) {
			T t = e.getKey();
			expirar(t);
			t = null;
		}
		en_uso.clear();
	}
	
	/*
	 * M�todos protegidos/privados
	 */
	
	// Devolver la marca de tiempo actual
	protected long ahora() {
		return System.currentTimeMillis();
	}
	
	/*
	 * Informar si el objeto recibido por par�metro ha 
	 * agotado el tiempo m�ximo de permanencia como
	 * disponible en el pool. Esto es necesario para
	 * mantener un tama�o adecuado de pool.
	 */
	private boolean haExpirado(T t) {
		return (ahora() - disponibles.get(t)) > expirationTime;
	}	
}
