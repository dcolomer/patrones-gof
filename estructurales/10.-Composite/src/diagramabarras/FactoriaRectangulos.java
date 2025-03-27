package estructurales.composite.diagramabarras;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;

/*
 * Factoria implementada como Singleton.
 * En funcion del valor de una propiedad del archivo de propiedades
 * 'tipobarra.properties', que debe encontrarse en el mismo paquete 
 * que la clase, instancia dinamicamente el tipo de Rectangulo adecuado.
 * Esto permite que el código cliente no tenga que variar para mostrar
 * uno u otro tipo de barras.  
 */
public class FactoriaRectangulos {

	private static FactoriaRectangulos factory = new FactoriaRectangulos();

	// true indica que ya se han cargado los nombres de las clases
	// desde el fichero de propiedades
	private static boolean propiedadesCargadas = false;

	// Propiedades
	private static java.util.Properties prop = new java.util.Properties();

	public static FactoriaRectangulos getInstance() {
		return factory;
	}
	
	public Grafico create(String nombrePropiedad, Linea linea1, Linea linea2, Linea linea3, Linea linea4) {
		try {			
			// La clase se obtiene leyendo del archivo properties
			Class<?> clase = Class.forName(getClase(nombrePropiedad));			
			Constructor<?> ctor = clase.getDeclaredConstructors()[0];		    
			
			// Crear una instancia
			return (Grafico) ctor.newInstance(linea1, linea2, linea3, linea4);
		} catch (ClassNotFoundException e) { // No existe la clase
			e.printStackTrace();
			return null;
		} catch (Exception e) { // No se puede instanciar la clase
			e.printStackTrace();
			return null;
		}
	}
	
	// Lee un archivo properties donde se indican las clases instanciables
	private static String getClase(String nombrePropiedad) {
		try {
			// Carga de propiedades desde archivo -solo la primera vez-
			if (!propiedadesCargadas) {
				InputStream is = FactoriaRectangulos.class
						.getResourceAsStream("tipobarra.properties");

				prop.load(is);
				propiedadesCargadas = true;
			}

			// Lectura de propiedad
			String nombreClase = prop.getProperty(nombrePropiedad, "");
			if (nombreClase.length() > 0)
				return nombreClase;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
