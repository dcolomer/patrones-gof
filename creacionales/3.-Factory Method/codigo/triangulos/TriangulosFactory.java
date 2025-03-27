package creacionales.factorymethod.triangulos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import creacionales.factorymethod.triangulos.products.Triangulo;

public abstract class TriangulosFactory {		
	
	private static Collection<Triangulo> triangulos = new ArrayList<Triangulo>();
	
	protected abstract Triangulo factoryMethod(double lado1, double lado2, double lado3);
	
	public static Triangulo crear(double lado1, double lado2, double lado3) {
		
		Triangulo triangulo = null;
		
		if (lado1 == lado2 && lado1 == lado3) {
			triangulo = new EquilateroFactory().factoryMethod(lado1, lado2, lado3);
		} else if ((lado1 == lado2) && (lado2 != lado3) || (lado1 == lado3)
				&& (lado1 != lado2)) {
			triangulo = new IsoscelesFactory().factoryMethod(lado1, lado2, lado3);
		} else if (lado1 != lado2 && lado2 != lado3 && lado3 != lado1) {
			triangulo = new EscalenoFactory().factoryMethod(lado1, lado2, lado3);
		}
			
		triangulos.add(triangulo);
			
		return triangulo;		
	}
	
	public static Collection<Triangulo> getTriangulos() {
		if (triangulos.isEmpty()) {
			return Collections.emptyList();
		}	
		return triangulos;		
	}		
}