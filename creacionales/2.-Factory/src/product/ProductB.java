package creacionales.factory.product;

import java.util.Date;

import creacionales.factory.ProductFactoryReflex;

public class ProductB implements Product {
	private String descripcion;
	private Date fechaCreacion;
		
	static {
		ProductFactoryReflex.getInstance()
			.registerProduct("B", creacionales.factory.product.ProductB.class);
	}

	public ProductB () {
		this.descripcion = "sin descripcion";
	}
	
	public ProductB (String descripcion) {
		this.descripcion = descripcion;
	}
	
	public ProductB (String descripcion, Date fechaCreacion) {
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;
	}
	
	@Override
	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public String toString() {
		return "ProductB [descripcion=" + descripcion + ", fechaCreacion="
				+ fechaCreacion + "]";
	}
		
}
