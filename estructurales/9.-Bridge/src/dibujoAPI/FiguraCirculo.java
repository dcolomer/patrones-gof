package estructurales.bridge.dibujoAPI;

/** "Refined Abstraction" */
public class FiguraCirculo extends Figura {
	private int x, y, radio;
	
	public FiguraCirculo(int x, int y, int radio) {
		this.x = x;
		this.y = y;
		this.radio = radio;		
	}

	/*
	 * Metodos que se delegan en el "Implementor".
	 * drawCircle es especifico de la implementacion.
	 */	
	public void dibujar() {
		dibujoAPI.dibujarCirculo(x, y, radio);
	}
	public void esperar() {
		dibujoAPI.esperar();
	}

	/*
	 * Metodos que pertenece a la "Abstraction"
	 * resizeByPercentage es especifico de la abstraccion.
	 */
	public void redimensionar(double pct) {
		radio *= pct;
	}
	
}
