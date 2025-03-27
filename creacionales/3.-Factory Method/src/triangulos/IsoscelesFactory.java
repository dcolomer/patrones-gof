package creacionales.factorymethod.triangulos;

//Visibilidad de paquete
import creacionales.factorymethod.triangulos.products.Isosceles;
import creacionales.factorymethod.triangulos.products.Triangulo;

public class IsoscelesFactory extends TriangulosFactory {

	@Override
	public Triangulo factoryMethod(double lado1, double lado2, double lado3) {
		return new Isosceles(lado1, lado2, lado3);
	}

}