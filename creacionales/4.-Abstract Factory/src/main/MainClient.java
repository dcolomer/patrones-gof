package creacionales.abstractfactory.main;

import creacionales.abstractfactory.AbstractFactory;
import creacionales.abstractfactory.AbstractProductA;
import creacionales.abstractfactory.AbstractProductB;

/**
 * La familia con la que se trabaja se lee del fichero de  
 * propiedades 'familia.properties' 
 */
public class MainClient {

	public static void main(String[] args) {		
		AbstractProductA productA;
		
		productA = AbstractFactory.getInstancia().createProductA();		
		productA.metodoP();
		productA.metodoQ();
				
		AbstractProductB productB;
				
		productB = AbstractFactory.getInstancia().createProductB();
		productB.metodoR();
		productB.metodoS();		
	}

}
