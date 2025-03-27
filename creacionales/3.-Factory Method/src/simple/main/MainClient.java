package creacionales.factorymethod.simple.main;

import creacionales.factorymethod.simple.ConcreteCreatorA;
import creacionales.factorymethod.simple.ConcreteCreatorB;
import creacionales.factorymethod.simple.Creator;
import creacionales.factorymethod.simple.product.Product;

public class MainClient {
	public static void main( String arg[] ) {
		Creator creator = new ConcreteCreatorA();
		Product product = creator.aMethod();
		product.operacion();
		
		creator = new ConcreteCreatorB();
		product = creator.aMethod();
		product.operacion();
	}
}
