package comportamiento.strategy.video;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase de utilidad que guarda entidades en un mapa 
 * organizandolas por categorias. Cada clave del mapa
 * es una categoria, y las entidades se almancenan dentro
 * de una lista para una misma categoria.
 */
public class Registro {

	private static Map<String, ArrayList<ObjetoDominio>> registro;  
	
	static {		
		registro = 
				new HashMap<String, ArrayList<ObjetoDominio>>();			
	}
	
	public static void add(String categoria, ObjetoDominio objeto) {
		
		if ("".equals(objeto.getNombre().trim())) {
			throw new RuntimeException("Error: Se ha intentado guardar " +
					"en el Registro un objeto nulo o sin nombre");
		}
		
		ArrayList<ObjetoDominio> listaObjetos = registro.get(categoria);
		if (listaObjetos == null) { // La categoria no existe, la creamos.
			ArrayList<ObjetoDominio> lista = new ArrayList<ObjetoDominio>();
			lista.add(objeto);
			registro.put(categoria, lista);
		} else {
			listaObjetos.add(objeto);			
		}
	}

	/**
	 * Devuelve un ObjetoDominio, según la categoria especificada y 
	 * el nombre del objeto. Esto implica, que el objeto debe tener 
	 * nombre antes de haberse guardado en el mapa. 
	 */
	public static ObjetoDominio get(String categoria, String nombre) {			
		ArrayList<ObjetoDominio> listaObjetos = registro.get(categoria);
		if (listaObjetos == null) { // La categoria no existe
			throw new RuntimeException("Error: La categoria '" + categoria + "' no existe.");
		}	
		
		for (ObjetoDominio objeto : listaObjetos) {
			if (objeto.nombre.equals(nombre)) {
				return objeto;
			}
		}
		
		return null;
	}

}
