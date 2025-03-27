package creacionales.prototype;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RectangularShape;

/*
 * En el patron adopta el papel 'Prototype'
 */
public interface Figura2D {
	
	public static final short ELIPSE = 1;
	public static final short RECTANGULO = 2;
	
	// Metodo que pinta una figura mediante
	// el contexto grafico Graphics2D
	public void dibujar(Graphics2D g2);

	// Retorna un objeto del tipo java.awt.geom.RectangularShape
	// que toda clase ConcretePrototype tiene como atributo
	public RectangularShape getFigura();

	// Metodo para la duplicacion de instancias
	public Figura2D clone();
	
	// metodo para establecer el color de una figura
	public void setColor(Color color);

}
