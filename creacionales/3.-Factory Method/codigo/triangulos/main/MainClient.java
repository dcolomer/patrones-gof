package creacionales.factorymethod.triangulos.main;

import creacionales.factorymethod.triangulos.TriangulosFactory;
import creacionales.factorymethod.triangulos.products.Triangulo;

public class MainClient {
	public static void main(String arg[]) {

		TriangulosFactory.crear(6.0d, 6.0d, 6.0d);
		TriangulosFactory.crear(4.5d, 6.0d, 4.5d);
		TriangulosFactory.crear(4.5d, 6.0d, 3.5d);

		// La factoria ha almacenado los triangulos creados
		// en una coleccion que ahora podemos recuperar
		if (!TriangulosFactory.getTriangulos().isEmpty()) {
			for (Triangulo t : TriangulosFactory.getTriangulos()) {
				System.out.println(t);
				System.out.println("Perimetro: " + t.perimetro());
			}
		} else {
			System.out.println("La lista esta vacia");
		}
	}
}
