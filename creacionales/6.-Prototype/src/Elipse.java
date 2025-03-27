package creacionales.prototype;

import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
import java.awt.Graphics2D;
import java.awt.Color;

/*
 * En el patron adopta el papel 'ConcretePrototype'
 */
class Elipse implements Figura2D {
	
	Ellipse2D.Double elipse;
	private Color color;

	Elipse(int x, int y, int ancho, int alto, Color color) {					
		elipse = new Ellipse2D.Double(x, y, ancho, alto);
		this.color = color;
	}
	
	/*
	 * Constructor de copia
	 */
	Elipse(Elipse e) {
		elipse = (Ellipse2D.Double) e.getFigura().clone();
		elipse.x = e.getFigura().getX();
		elipse.y = e.getFigura().getY();
		elipse.width = e.getFigura().getWidth();
		elipse.height = e.getFigura().getHeight();
		color = e.color;
	}

	public void dibujar(Graphics2D g2) {		
		g2.setColor(color);
		g2.draw(elipse);
	}	
	
	@Override
	public RectangularShape getFigura() {
		return elipse;
	}	
	
	@Override
	public Figura2D clone() {
		return new Elipse(this);
	}

	@Override
	public void setColor(Color color) {
		this.color = color;		
	}
	
}
	
	