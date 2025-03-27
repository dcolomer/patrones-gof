package estructurales.composite.diagramabarras;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/** "Leaf" **/

/*
 * Clase simple (terminal) que dibuja un 
 * texto en la pantalla. 
 * 
 * Se utiliza para mostrar el titulo del grafico 
 * y los valores para los ejes X e Y del mismo.
 */
public class Texto implements Grafico {

	private String texto;
	private boolean esUnTitulo;
	private int x, y;
	
	private Font font;
	
	/*
	 * Constructor para mostrar texto en 
	 * una posicion determinada.
	 */
	public Texto(String texto, int x, int y) {
		this.texto = texto;
		this.x = x;
		this.y = y;
	}
	
	/*
	 * Constructor para cuando se trata de un titulo.
	 * Un titulo debe mostrarse con letra grande, en la 
	 * parte superior del marco y centrado horizontalmente.
	 */
	public Texto(String texto, boolean esUntitulo) {
		this.texto = texto;
		this.esUnTitulo = esUntitulo;
	}
	
	/*
	 * Dibujar el texto, diferenciando si se trata 
	 * o no de un titulo.
	 */
	@Override
	public void dibujar(Graphics2D g2) {				
		if (esUnTitulo) {
			// Letra grande
			font = new Font("Arial", Font.BOLD, 20);
			
			// Obtener el tamaño del texto para la fuente actual en el contexto grafico g2
			FontMetrics fm   = g2.getFontMetrics(font);
			Rectangle2D rect = fm.getStringBounds(texto, g2);
					
			// Centrar el texto horizontamente
			int anchoTexto = (int)(rect.getWidth());							
			this.x = (Ventana.FRAME_WIDTH  - anchoTexto)  / 2;
			
			/*//Centrar el texto verticalmente
			int altoTexto = (int)(rect.getHeight());
			this.y = (MainClient.FRAME_HEIGHT - altoTexto) / 2  + fm.getAscent();*/			
			this.y = 60;
			
		} else { // Si no es un titulo, entonces la letra pequeña
			font = new Font("Arial", Font.BOLD, 10);
		}
				
		g2.setColor(Color.BLUE);
		g2.setFont(font);
		g2.drawString(texto, x, y); // Dibujar el texto		
	}

	/*
	 * La lista de graficos de un Texto tan solo 
	 * contiene un grafico: el mismo
	 */
	@Override
	public Grafico[] getGrafico() {		
		return new Grafico[] { this };
	}
	
	/*
	 * Metodo no soportado para una clase simple	 
	 */
	@Override
	public void add(Grafico figura) {
		throw new UnsupportedOperationException("Motodo no implementado en un objeto terminal");		
	}

	/*
	 * Metodo no soportado para una clase simple	 
	 */
	@Override
	public void remove(Grafico figura) {
		throw new UnsupportedOperationException("Motodo no implementado en un objeto terminal");		
	}

}
