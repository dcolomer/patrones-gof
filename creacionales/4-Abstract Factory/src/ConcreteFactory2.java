package creacionales.abstractfactory;

/*
 * Tiene visibilidad de paquete, ya que tan sólo debe  
 * acceder a ella la factoría abstracta.
 */
class ConcreteFactory2 extends AbstractFactory{
	public AbstractProductA createProductA(){
		return new ProductA2("ProductA2");
	}
	public AbstractProductB createProductB(){
		return new ProductB2("ProductB2");
	}
}