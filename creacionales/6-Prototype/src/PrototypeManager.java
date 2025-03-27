package creacionales.prototype;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/*
 * Factoria de Prototipos, implementada como un Singleton.
 * 
 * Define operaciones públicas para obtener:
 * -la instancia Singleton.
 * -un duplicado de algun ConcretePrototype en funcion de 
 * un token que se utiliza como clave del HashMap o de un
 * valor de un subtipo de Figura2D que permitirá localizar  
 * la entrada correspondiente en el HashMap.
 * 
 * La primera vez que invoca a PrototypeManager se deben
 * crear las ConcretePrototype, por lo que sera un poco
 * lento, quizas. En cambio el resto de  llamadas seran
 * muy rapidas porque las ConcretePrototype se obtienen
 * del HashMap
 */
public class PrototypeManager {
	private static Map<Short, Figura2D> prototipos;
	private static PrototypeManager instancia = 
		new PrototypeManager();
	
	// Retornar la instancia Singleton
	public static PrototypeManager getInstancia() {
		return instancia;
	}
	
	/*
	 * Constructor privado.
	 * Se instancia el HashMap y se añade una instancia 
	 * de cada ConcretePrototype.
	 * 
	 * Estas instancias no se llegan a representar en el
	 * editor grafico, ya que tan solo sirven del molde 
	 * para obtener duplicados.
	 */
	private PrototypeManager() {
		prototipos = new HashMap<Short, Figura2D>();
		prototipos.put(Figura2D.ELIPSE, 
				new Elipse(100, 100, 50, 20, Color.BLUE));
		prototipos.put(Figura2D.RECTANGULO, 
				new Rectangulo(100, 400, 50, 20, Color.BLUE));
	}	
	
	// Retornar un duplicado del ConcretePrototype 
	// a partir de una clave
	public Figura2D getPrototipo(short clave) {
		return prototipos.get(clave).clone();
	}		
	
	// Retornar un duplicado del ConcretePrototype 
	// a partir de un subtipo de Figura2D
	public Figura2D getPrototipo(Figura2D valor) {
		
		// Recorrer el mapa hasta encontrar una entrada cuya clase 
		// coincida con la clase del objeto pasado por parametro		
		for (Entry<Short, Figura2D> e : prototipos.entrySet()) {
			// Obtener la clase de la entrada actual
			Class<?> claseProto = e.getValue().getClass();
			// ¿Coincide con la clase del parametro?
			if (claseProto.equals(valor.getClass())) {				
				// Devolver el objeto asociado a la clave de la entrada actual
				return prototipos.get(e.getKey()).clone();
			}
		}
		throw new RuntimeException("PropertyManager no ha podido recuperar un prototipo a partir del objeto especificado por parametro.");
	}
}
