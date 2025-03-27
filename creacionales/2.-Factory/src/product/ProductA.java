package creacionales.factory.product;

import creacionales.factory.ProductFactoryReflex;

public class ProductA implements Product {
	private String descripcion;
		
	static {
		ProductFactoryReflex.getInstance()
			.registerProduct("A",
			creacionales.factory.product.ProductA.class);
	}

	public ProductA () {
		this.descripcion = "sin descripcion";
	}
	
	public ProductA (String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String getDescripcion() {
		return descripcion;
	}
}
