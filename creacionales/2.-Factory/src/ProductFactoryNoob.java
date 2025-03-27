package creacionales.factory;

import creacionales.factory.product.Product;
import creacionales.factory.product.ProductA;
import creacionales.factory.product.ProductB;

// Factoria implementada como Singleton
public class ProductFactoryNoob {
	private static ProductFactoryNoob factory =
		new ProductFactoryNoob();
	
	private ProductFactoryNoob() {
		
	}
	
	public static ProductFactoryNoob getInstance() {
		return factory;
	}
	
	public Product createProduct(String ProductID){
		if (ProductID.equals("A"))
			return new ProductA();
		if (ProductID.equals("B"))
			return new ProductB();
		// lo mismo para otros Ids
		//...
		// si el id no tiene ninguno de los valores esperados
		return null;
    }
    //...
}

