package creacionales.abstractfactory;

/*
 * Tiene visibilidad de paquete, ya que tan sólo debe  
 * acceder a ella la factoría concreta correspondiente
 */
class ProductA1 extends AbstractProductA {
	public ProductA1(String string) {
		System.out.println("Creado ProductA1");
	}

	public void metodoP() {
		System.out.println("ProductA1: Ejecutado metodoP()");
	}

	public void metodoQ() {
		System.out.println("ProductA1: Ejecutado metodoQ()");
	}
}
