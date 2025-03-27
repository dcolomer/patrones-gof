package estructurales.composite.diagramabarras;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

/** "Leaf" **/

/*
 * Clase simple (terminal) que dibuja una linea 
 * en las coordenadas especificadas. 
 */
public class Linea implements Grafico {

	// Objecto subyacente que realmente se pinta
	private Line2D.Double linea;

	public Line2D.Double getLinea() {
		return linea;
	}

	/*
	 * Crear una linea segun las coordenadas 
	 * recibidas en los parametros 
	 */
	public Linea(int x1, int y1, int x2, int y2) {
		this.linea = new Line2D.Double(x1, y1, x2, y2);		
	}

	/*
	 * Dibujar la linea
	 */
	public void dibujar(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.draw(linea);
	}
	
	/*
	 * La lista de graficos de una linea tan solo 
	 * contiene un grafico: ella misma
	 */
	@Override
	public Grafico[] getGrafico() {		
		return new Grafico[] { this };
	}

	/*
	 * Metodo no soportado para una clase simple	 
	 */
	@Override
	public void add(Grafico grafico) {
		throw new UnsupportedOperationException("Motodo no implementado en un objeto terminal");		
	}

	/*
	 * Metodo no soportado para una clase simple	 
	 */
	@Override
	public void remove(Grafico grafico) {
		throw new UnsupportedOperationException("Motodo no implementado en un objeto terminal");		
	}

}
