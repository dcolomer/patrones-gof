package creacionales.abstractfactory;

/*
 * Tiene visibilidad de paquete, ya que tan s�lo debe  
 * acceder a ella la factor�a abstracta.
 */
class ConcreteFactory1 extends AbstractFactory {
	public AbstractProductA createProductA(){
		return new ProductA1("ProductA1");
	}
	public AbstractProductB createProductB(){
		return new ProductB1("ProductB1");
	}
}
