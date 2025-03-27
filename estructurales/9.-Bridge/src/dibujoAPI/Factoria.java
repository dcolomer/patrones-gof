package estructurales.bridge.dibujoAPI;

import java.io.IOException;
import java.io.InputStream;

// Factoria implementada como Singleton
public class Factoria {

	private static Factoria factory = new Factoria();

	// true indica que ya se han cargado los nombres de las clases
	// desde el fichero de propiedades
	private static boolean propiedadesCargadas = false;

	// Propiedades
	private static java.util.Properties prop = new java.util.Properties();

	public static Factoria getInstance() {
		return factory;
	}
	
	public DibujoAPI create(String nombrePropiedad) {
		try {
			// La clase se obtiene leyendo del archivo properties
			Class<?> clase = Class.forName(getClase(nombrePropiedad));
			// Crear una instancia
			return (DibujoAPI) clase.newInstance();
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
				InputStream is = Factoria.class
						.getResourceAsStream("plataforma.properties");

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
