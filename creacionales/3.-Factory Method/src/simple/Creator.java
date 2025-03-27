package creacionales.factorymethod.simple;

import creacionales.factorymethod.simple.product.Product;

// Definimos la clase abstracta constructora
public abstract class Creator {
	// Operación comun a todas las subclases de Creator
	// Podriamos guardar cada producto en una lista, por ejemplo.
	public Product aMethod() {
		Product product = factoryMethod();
		return product;
	}
	
	// Definimos el método abstracto - metodo factoria
	protected abstract Product factoryMethod();
}
