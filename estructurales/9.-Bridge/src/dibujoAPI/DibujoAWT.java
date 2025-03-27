package estructurales.bridge.dibujoAPI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;

/** "ConcreteImplementor" 1/2 */
class DibujoAWT implements DibujoAPI {
		
	private int x, y, radio;

	/*
	 * Puntero estático al objeto actual: necesario para poder acceder a
	 * las variables miembro desde el método de callback del JFrame, que 
	 * debe estatico, ya que se comparte por todas las cicunferencias.
	 */
	private static DibujoAWT objEnCurso;
	
	private static JFrame marco;
	private static Font font;
	
	static {
		marco = new JFrame() {			
			private static final long serialVersionUID = 1L;			
			@Override
			public void paint(Graphics g) {
				int x = objEnCurso.x;
				int y = objEnCurso.y;
				int radio = objEnCurso.radio;
				
				g.setColor(Color.BLUE);
				g.fillOval(x, y, radio, radio);
				
				g.setFont(font);
				g.setColor(Color.BLACK);
				g.drawString("("+x+","+y+") r:" + radio, x-15, y-15);				
			}
		}; 
		marco.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		marco.setTitle("API de AWT");
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marco.setLocationRelativeTo(null);
		marco.setVisible(true);
		font = new Font("Arial",Font.PLAIN, 10);
	}
	 
	@Override
	public void dibujarCirculo(int x, int y, int radio) {
		objEnCurso = this;
		this.x = x;
		this.y = y;
		this.radio = radio;		
		// Actualizamos sin limpiar el fondo, asi evitamos 
		// que se borren los circulos anteriores
		marco.update(marco.getGraphics());		
		System.out.printf("DibujoPlataformaAWT. Circulo en %d:%d con radio %d\n",
				x, y, radio);			
	}	

	@Override
	public void esperar() {
		System.out.println("Dibujo finalizado.");		
	}
	
}
