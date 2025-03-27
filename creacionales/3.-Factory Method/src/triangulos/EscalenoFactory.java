package creacionales.factorymethod.triangulos;

import creacionales.factorymethod.triangulos.products.Escaleno;
import creacionales.factorymethod.triangulos.products.Triangulo;

//Visibilidad de paquete
public class EscalenoFactory extends TriangulosFactory {

	@Override
	public Triangulo factoryMethod(double lado1, double lado2, double lado3) {
		return new Escaleno(lado1, lado2, lado3);		
	}
	
}