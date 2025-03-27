package creacionales.factory.main;

import creacionales.factory.ProductFactoryPropFile;
import creacionales.factory.product.Product;

public class MainClientPropFile {	

	public static void main(String args[]) {
		// Crear un ProductA
		System.out.println("Creando un objeto ProductA");
		Product product = (Product) ProductFactoryPropFile
			.getInstance().createProduct("A");
		System.out.println("Tipo creado: "+product.getClass());
		System.out.println(product.getDescripcion());
		
		// Crear un ProductB
		System.out.println("Creando un objeto ProductB");
		product = (Product) ProductFactoryPropFile
			.getInstance().createProduct("B");
		System.out.println("Tipo creado: "+product.getClass());
		System.out.println(product.getDescripcion());		
	}
}

