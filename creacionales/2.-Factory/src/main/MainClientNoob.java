package creacionales.factory.main;

import creacionales.factory.ProductFactoryNoob;
import creacionales.factory.product.Product;

public class MainClientNoob {

	public static void main(String[] args) {

		// Crear un ProductA
		System.out.println("Creando un objeto ProductA");
		Product product = (Product) ProductFactoryNoob
			.getInstance().createProduct("A");
		System.out.println("Tipo creado: "+product.getClass());
		
		// Crear un ProductB
		System.out.println("Creando un objeto ProductB");
		product = (Product) ProductFactoryNoob
			.getInstance().createProduct("B");
		System.out.println("Tipo creado: "+product.getClass());
	}

}
