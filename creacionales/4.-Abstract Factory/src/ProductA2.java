package creacionales.abstractfactory;

/*
 * Tiene visibilidad de paquete, ya que tan s�lo debe  
 * acceder a ella la factor�a concreta correspondiente
 */
class ProductA2 extends AbstractProductA {
	public ProductA2(String string) {
		System.out.println("Creado ProductA2");
	}

	public void metodoP() {
		System.out.println("ProductA2: Ejecutado metodoP()");
	}

	public void metodoQ() {
		System.out.println("ProductA2: Ejecutado metodoQ()");
	}
}
