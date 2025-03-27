package creacionales.abstractfactory;

import java.io.IOException;
import java.io.InputStream;


public abstract class AbstractFactory implements IFactoria {
	
	private static IFactoria instancia;
	
	public static synchronized IFactoria getInstancia() {		
		if (instancia == null) {
			try {
				// La familia se obtiene leyendo del archivo properties
				Class<?> clase = Class.forName(getFamilia("familia"));
				// Crear una instancia
				instancia = (IFactoria) clase.newInstance();
				System.out.println("Familia utilizada: "+instancia.getClass().getSimpleName());
			} catch (ClassNotFoundException e) { // No existe la clase
				e.printStackTrace();			
			} catch (Exception e) { // No se puede instanciar la clase
				e.printStackTrace();			
			}	
		}
					
		return instancia;			
	}
			
	// true indica que ya se han cargado la familia
	// desde el fichero de propiedades
	private static boolean propiedadesCargadas = false;

	// Propiedades
	private static java.util.Properties prop = new java.util.Properties();
	
	public abstract AbstractProductA createProductA();

	public abstract AbstractProductB createProductB();	
	
	// Lee un archivo properties donde se indican la familia que se quiere utilizar
	private static String getFamilia(String nombrePropiedad) {
		try {
			// Carga de propiedades desde archivo -solo la primera vez-
			if (!propiedadesCargadas) {
				InputStream is = IFactoria.class
						.getResourceAsStream("familia.properties");

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
