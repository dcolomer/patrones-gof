package creacionales.factorymethod.simple;

import creacionales.factorymethod.simple.product.Product;

// Definimos la clase abstracta constructora
public abstract class Creator {
	// Operaci�n comun a todas las subclases de Creator
	// Podriamos guardar cada producto en una lista, por ejemplo.
	public Product aMethod() {
		Product product = factoryMethod();
		return product;
	}
	
	// Definimos el m�todo abstracto - metodo factoria
	protected abstract Product factoryMethod();
}
