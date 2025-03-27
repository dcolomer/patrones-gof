package creacionales.prototype;

import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.Graphics2D;
import java.awt.Color;

/*
 * En el patron adopta el papel 'ConcretePrototype'
 */
class Rectangulo implements Figura2D {
		
	private Rectangle2D.Double rectangulo;	
	private Color color;
	
	Rectangulo(int x, int y, int ancho, int alto, Color color) {					
		this.rectangulo = new Rectangle2D.Double(x, y, ancho, alto);
		this.color = color;
	}
	
	/*
	 * Constructor de copia
	 */
	Rectangulo(Rectangulo r) {
		this.rectangulo = (Rectangle2D.Double) r.getFigura().clone();
		this.rectangulo.x = r.getFigura().getX();
		this.rectangulo.y = r.getFigura().getY();
		this.rectangulo.width = r.getFigura().getWidth();
		this.rectangulo.height = r.getFigura().getHeight();		
		color = r.color;		
	}
		
	public void dibujar(Graphics2D g2) {		
		g2.setColor(color);
		g2.draw(rectangulo);
	}
	
	@Override
	public RectangularShape getFigura() {
		return rectangulo;
	}
	
	@Override
	public Figura2D clone() {
		return new Rectangulo(this);
	}

	@Override
	public void setColor(Color color) {
		this.color = color;		
	}
}
	
