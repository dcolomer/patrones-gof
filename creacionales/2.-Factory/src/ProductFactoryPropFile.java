package creacionales.factory;

import java.io.IOException;
import java.io.InputStream;

import creacionales.factory.product.Product;

// Factoria implementada como Singleton
public class ProductFactoryPropFile {

	private static ProductFactoryPropFile factory = new ProductFactoryPropFile();

	// true indica que ya se han cargado los nombres de las clases
	// desde el fichero de propiedades
	private static boolean propiedadesCargadas = false;

	// Propiedades
	private static java.util.Properties prop = new java.util.Properties();

	public static ProductFactoryPropFile getInstance() {
		return factory;
	}
	
	public Product createProduct(String productID) {
		try {
			// La clase se obtiene leyendo del archivo properties
			Class<?> clase = Class.forName(getClase(productID));
			// Crear una instancia
			return (Product) clase.newInstance();
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
				InputStream is = ProductFactoryPropFile.class
						.getResourceAsStream("mapaProducts.properties");

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
