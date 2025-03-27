package estructurales.composite.diagramabarras;

import java.awt.Graphics2D;

/** "Composite" **/

/*
 * Rectangulo es una clase compuesta por
 * 4 objetos de tipo Linea
 */
public class Rectangulo implements Grafico {

	// Lista de lineas que forman el rectangulo
	private Grafico[] lados = new Grafico[4];

	public Rectangulo(Linea linea1, Linea linea2, 
			Linea linea3, Linea linea4) 
	{
		lados[0] = linea1;
		lados[1] = linea2;
		lados[2] = linea3;
		lados[3] = linea4;
	}

	/*
	 * En el caso de un grafico compuesto no 
	 * podemos dibujar directamente, sino que
	 * hay que delegar en los graficos simples
	 * que lo componen. En el caso del rectangulo 
	 * se delega en las lineas, que son sus lados.
	 */
	@Override
	public void dibujar(Graphics2D g2) {
		for (Grafico lado : lados) {
			// Delegar en los objetos hijos
			lado.dibujar(g2);
		}
	}

	/*
	 * La lista de graficos de un rectangulo
	 * son las cuatro lineas que lo forman.
	 */
	@Override
	public Grafico[] getGrafico() {
		return lados;
	}
	
	/*
	 * A pesar de que un rectangulo es un objeto compuesto, 
	 * no tiene sentido añadir nuevos hijos (nuevos lados),
	 * ya que el rectangulo se construye correctamente mediante
	 * su constructor y despues no se puede modificar.
	 * En otro tipo de objetos compuestos sí tiene sentido
	 * agregar objetos arbitrariamente, una vez creados. 
	 */
	@Override
	public void add(Grafico grafico) {
		throw new UnsupportedOperationException("Motodo no implementado en un Rectangulo");		
	}

	/*
	 * Idem metodo add() pero para el caso de la eliminacion
	 * de objetos hijo.	 
	 */
	@Override
	public void remove(Grafico grafico) {
		throw new UnsupportedOperationException("Motodo no implementado en un Rectangulo");		
	}

}
