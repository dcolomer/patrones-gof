package estructurales.composite.diagramabarras;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

/** Es un mix entre un "Leaf" y un "Composite **/
public class RectanguloSolido extends Rectangulo {
	
	private int x, y, ancho, alto;
	private static Color colorPrevio;
	
	public RectanguloSolido(Linea linea1, Linea linea2, 
			Linea linea3, Linea linea4) 
	{
		super(linea1, linea2, linea3, linea4);
		
		/*
		 * Obtener los datos necesarios para pintar un rectangulo.
		 */
		x = (int) linea1.getLinea().x1;
		y = (int) linea1.getLinea().y2;
		ancho = (int) (linea2.getLinea().x2-x);
		alto =  (int)linea1.getLinea().y1-y;		
	}
	
	@Override
	public void dibujar(Graphics2D g2) {		
		Color color = getColorAleatorio();
		// Evitar que dos barras contiguas tengan el mismo color
		if (colorPrevio != null) {
			do {
				color = getColorAleatorio(); 
			} while ( color == colorPrevio); 									
		}	
			
		g2.setColor(color);
		g2.fillRect(x, y, ancho, alto); // Pintar barra
		super.dibujar(g2); // Pintar los bordes
		colorPrevio = color;		
	}
	
	// Devuelve un color al azar
	private Color getColorAleatorio() {		
		return Color.getHSBColor(new Random().nextFloat(), 1.0F, 1.0F );
	}
}
