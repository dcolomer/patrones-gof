package creacionales.abstractfactory;

/*
 * Tiene visibilidad de paquete, ya que tan sólo debe  
 * acceder a ella la factoría concreta correspondiente
 */
class ProductB1 extends AbstractProductB {
	public ProductB1(String string) {
		System.out.println("Creado ProductB1");
	}

	public void metodoR() {
		System.out.println("ProductB1: Ejecutado metodoR()");
	}

	public void metodoS() {
		System.out.println("ProductB1: Ejecutado metodoS()");
	}
}
