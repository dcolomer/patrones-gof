package creacionales.abstractfactory;

/*
 * Tiene visibilidad de paquete, ya que tan s�lo debe  
 * acceder a ella la factor�a concreta correspondiente
 */
class ProductB2 extends AbstractProductB {
	public ProductB2(String string) {
		System.out.println("Creado ProductB2");
	}

	public void metodoR() {
		System.out.println("ProductB2: Ejecutado metodoR()");
	}

	public void metodoS() {
		System.out.println("ProductB2: Ejecutado metodoS()");
	}
}
