package creacionales.abstractfactory;

/*
 * Tiene visibilidad de paquete, ya que tan s�lo debe  
 * acceder a ella la factor�a abstracta.
 */
class ConcreteFactory2 extends AbstractFactory{
	public AbstractProductA createProductA(){
		return new ProductA2("ProductA2");
	}
	public AbstractProductB createProductB(){
		return new ProductB2("ProductB2");
	}
}