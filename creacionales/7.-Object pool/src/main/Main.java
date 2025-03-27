package creacionales.objectpool.main;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import creacionales.objectpool.DatosConexion;
import creacionales.objectpool.JdbcConnectionPool;
import creacionales.objectpool.ObjectPool;

public class Main {
	
	// Nombre del fichero log4j 
	private static final String LOG_PROPERTIES_FILE = "log4j.properties";
	// ¿El fichero log4j está en un paquete de la aplicacion?
	private static final boolean LOG_FILE_IN_PACKAGE = true;
	
	
	
	// log para que la clase Main escriba los mensajes que se necesite
	private static final Logger LOG = Logger.getLogger(Main.class);
	
	/*
	 * POOL de conexiones
	 * Visibilidad pública; es un Singleton. Todos los hilos deben leer su valor 
	 */
	public static ObjectPool<Connection> pool;
		
	/*
	 * Inicializados estático.
	 * Leer la configuracion del log4j. 
	 */
	static {		
		Utiles.loadConfigLogging(Main.class, LOG_PROPERTIES_FILE, LOG_FILE_IN_PACKAGE);
		if (LOG.isDebugEnabled())
			LOG.debug("Fichero "+LOG_PROPERTIES_FILE+" cargado.");
	}
	
	// Lista para almacenar los hilos
	private static List<HiloCliente> listaHilos;
	
	/* * * * * * * * * * * * * * * * * * * * * * 
	 * Punto de entrada al programa 
	 * * * * * * * * * * * * * * * * * * * * * */
	public static void main(String args[]) {		
		// nanoTime() devuelve la milmillonesima parte de un segundo
		long tiempoInicial = System.nanoTime();
		
		iniciarPool(); // crear el pool		
		lanzarHilos(); // crear y arrancar los usuarios concurrentes		
		esperarFinalizacionHilos(); // esperar a que acaben todos		
		finalizarPool(); // cierre y limpieza del pool
		
		long duracion = System.nanoTime() - tiempoInicial;
		System.out.println("Tiempo de ejecucion: " + duracion + " nanosegundos.");
		System.out.println("Programa finalizado. Consultar resultados en el LOG.");
	}

	/*
	 * Crear el pool de conexiones
	 */
	private static void iniciarPool() {						
		
		// El pool se creara con una cache que tendra 
		// el siguiente numero de conexiones conexiones
		final short CONEXIONES_CACHEADAS = 3;
		
		// El pool eliminará recursos de la cache si no se 
		// usan más de 30 segundos
		final short CACHE_EXPIRE = 30000;
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("--------------------------------------------------------------------");
			LOG.debug("INICIANDO APLICACION");
			LOG.debug("--------------------------------------------------------------------");
			LOG.debug("Creando pool de conexiones con cache de " + 
					CONEXIONES_CACHEADAS + " conexiones y caducidad de "+ 
					(CACHE_EXPIRE/1000) + " segundos.");
		}
		
		// Bean para encapsular la informacion de la conexion		
		DatosConexion dc = new DatosConexion("com.mysql.jdbc.Driver", 
				"jdbc:mysql://localhost:3306/video",
				"root", "root");		
		
		// Crear el pool de conexiones
		pool = JdbcConnectionPool.getInstance(dc, CONEXIONES_CACHEADAS, CACHE_EXPIRE);		
	}		
	
	/*
	 * Lanzar los hilos (usuarios) simultaneos
	 */
	private static void lanzarHilos() {
		// Numero maximo de hilos concurrentes?	
		final int MAX_NUM_USERS = 10;
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("Lanzando " + MAX_NUM_USERS + " hilos concurrentemente.");
		}
		
		String nombreHilo = null;
		listaHilos = new ArrayList<HiloCliente>();
		for (int i=1; i<MAX_NUM_USERS; i++) {
			nombreHilo = String.valueOf("Hilo " + i);
			HiloCliente hilo = new HiloCliente(nombreHilo);
			
			// Guardar cada objeto en la lista
			listaHilos.add(hilo);
			
			// Arrancar cada hilo
			if (LOG.isDebugEnabled()) {
				LOG.debug("Arrancado el hilo " + i);
			}
			hilo.start();			
		}
		
	}

	/*
	 * Esperar a que finalice cada usuario
	 */
	private static void esperarFinalizacionHilos() {
		
		for (HiloCliente hilo : listaHilos) {
			try {
				if (LOG.isDebugEnabled()) {
					LOG.debug(hilo.getName() + " finalizado.");
				}	
				hilo.join();
			} catch (InterruptedException e) {
				LOG.debug(e);
				e.printStackTrace();
			}
		}
		
	}
	 
	/*
	 * Solicitar al pool que cierre las conexiones cacheadas 
	 */
	private static void finalizarPool() {		
		if (pool != null) {
			pool.liberarTodo();
			if (LOG.isDebugEnabled()) {
				LOG.debug("Conexiones del pool cerradas. Devueltas al Entorno del recurso (SGBD).");
				LOG.debug("Conexiones -- disponibles: " + pool.getDisponiblesEnPool() 
						+ " Ocupadas: " + pool.getOcupadosEnPool() 
						+ " Totales: " + pool.getOcupacionTotal());
			}	
		}		
	}
	
}
