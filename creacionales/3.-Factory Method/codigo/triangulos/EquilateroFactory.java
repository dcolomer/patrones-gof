package creacionales.factorymethod.triangulos;

import creacionales.factorymethod.triangulos.products.Equilatero;
import creacionales.factorymethod.triangulos.products.Triangulo;

// Visibilidad de paquete
class EquilateroFactory extends TriangulosFactory {

	@Override
	public Triangulo factoryMethod(double lado1, double lado2, double lado3) {
		return new Equilatero(lado1, lado2, lado3);		
	}

}