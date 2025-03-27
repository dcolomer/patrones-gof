package creacionales.factory;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import creacionales.factory.product.Product;

// Factoria implementada como Singleton
public class ProductFactoryReflex {
	private static ProductFactoryReflex factory = 
		new ProductFactoryReflex();
	
	// Mapa para correspondencia ProductID-Clase
	private HashMap<String, Class<?>> 
		m_RegisteredProducts = new HashMap<String, Class<?>>();

	public void 
		registerProduct (String productID, Class<?> productClass) {
			m_RegisteredProducts.put(productID, productClass);
	}

	public static ProductFactoryReflex getInstance() {
		return factory;
	}
	
	// Método para cuando se quiere ejecutar el 
	// constructor sin parámetros del Producto 
	public Product createProduct(String productID) {
		Class<?> productClass = 
			(Class<?>) m_RegisteredProducts.get(productID);
		
		try {						
			return (Product) productClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
	}
	
	// Metodo capaz de ejecutar cualquier constructor del Producto
	// Notad que el segundo argumento es un varargs, lo cual nos permite
	// buscar entre la lista de constructores de Product
	public Product createProduct(String productID, Object... argsConstructor) {
		Class<?> productClass = 
			(Class<?>) m_RegisteredProducts.get(productID);
		Constructor<?> productConstructor = null;
		
		try {
			
			boolean continuar = false;
			
			// Obtener un array con todos los constructores de la clase
			Constructor<?>[] ctors = productClass.getDeclaredConstructors();
			Constructor<?> ctor = null;
			
			// Si algun constructor coincide con el numero de parametros
			// de parametrosConstructor entonces, a priori, hay coincidencia
			// y se podra instanciar el constructor
			for (int i = 0; i < ctors.length; i++) {
				ctor = ctors[i];			
				if (argsConstructor.length == ctor.getGenericParameterTypes().length) {
					continuar = true;
					break; // hay coincidencia en el numero de parametros
				}
			}
			
			if (continuar) {
				// Recorrer los argumentos enviados para el constructor y comprobar 
				// que son del mismo tipo que el constructor candidato 
				for (int i = 0; i < argsConstructor.length; i++ ) {
					String tipoArgumentoCons = argsConstructor[i].getClass().getName();
					String tipoParamCons = ctor.getParameterTypes()[i].getName();
					if (!tipoArgumentoCons.equals(tipoParamCons)) {
						throw new RuntimeException("No hay ningun constructor adecuado para esos argumentos");						
					}
				}
				
				// Ha habido coincidencia completa entre los argumentos enviados 
				// y uno de constructor de la clase, por tanto se puede invocar						
				
				productConstructor = productClass
					.getDeclaredConstructor(ctor.getParameterTypes());
				return (Product) productConstructor
					.newInstance(argsConstructor);
				
			}
			
			throw new RuntimeException("No hay ningun constructor adecuado para esos argumentos");			
						
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
	}
}

