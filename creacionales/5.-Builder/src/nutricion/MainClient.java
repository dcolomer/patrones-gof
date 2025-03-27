package creacionales.builder.nutricion;

import creacionales.builder.nutricion.Product.Builder;

public class MainClient {

	public static void main(String[] args) {

		/*
		 * Primer ejemplo -largo-
		 * 
		 */
		
		// Obtenemos una instancia de Builder. Para conseguirla
		// tenemos que pasar los parametros obligatorios para
		// la creación del producto
		Builder builder = Product.Builder.crearBuilder(240, 8, 100);
		
		// Ahora establecemos los parametros optativos
		builder.sodio(35).grasas(27).carbohidratos(39);
		
		// Finalmente creamos el producto
		Product cola = builder.build();
				
		System.out.println("cola" + cola);
		
		/*
		 * Segundo ejemplo -corto-
		 * 
		 */		
		Product zumoNaranja = Product.Builder.crearBuilder(306, 9, 5)
			.sodio(35).potasio(27).build();
				
		System.out.println("zumo de naranja" + zumoNaranja);						
	}
}
