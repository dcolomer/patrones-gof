package creacionales.factory.main;

import java.util.Calendar;

import creacionales.factory.ProductFactoryReflex;
import creacionales.factory.product.Product;

public class MainClientReflex {
	static {
		try {
			Class.forName("creacionales.factory.product.ProductA");
			Class.forName("creacionales.factory.product.ProductB");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		// Crear un ProductA
		System.out.println("Creando un objeto ProductA");
		Product product = (Product) ProductFactoryReflex
			.getInstance().createProduct("A");
		System.out.println("Tipo creado: "+product.getClass());
		System.out.println(product.getDescripcion());
		
		// Crear un ProductB
		System.out.println("Creando un objeto ProductB");
		product = (Product) ProductFactoryReflex
			.getInstance().createProduct("B","producto nuevo");
		System.out.println("Tipo creado: "+product.getClass());
		System.out.println(product.getDescripcion());

		
		// Crear otro ProductB
		System.out.println("Creando otro objeto ProductB");
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 25);
		cal.set(Calendar.MONTH, 8); // 0=enero - 8=septiembre
		cal.set(Calendar.YEAR, 2011);
		
		product = (Product) ProductFactoryReflex
			.getInstance().createProduct("B","producto de oferta", cal.getTime());
		System.out.println("Tipo creado: "+product.getClass());		
		System.out.println(product);
	}
}

