package creacionales.factorymethod.simple;

import creacionales.factorymethod.simple.product.ConcreteProductB;
import creacionales.factorymethod.simple.product.Product;

// Definimos un creador concreto
public class ConcreteCreatorB extends Creator {
	@Override
	protected Product factoryMethod() {
		return new ConcreteProductB();
	}
}
