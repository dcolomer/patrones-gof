package creacionales.factorymethod.simple;

import creacionales.factorymethod.simple.product.ConcreteProductA;
import creacionales.factorymethod.simple.product.Product;

// Definimos un creador concreto
public class ConcreteCreatorA extends Creator {
	@Override
	protected Product factoryMethod() {
		return new ConcreteProductA();
	}
}
