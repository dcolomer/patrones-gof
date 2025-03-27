package estructurales.bridge.dibujoAPI;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/** "ConcreteImplementor" 2/2 */
class DibujoSWT implements DibujoAPI {

	/*
	 * Contenedores para almacenar todas las 
	 * figuras que envíe el código cliente
	 */
	private List<Integer> x, y, radio;
			
	/*
	 * Objetos de SWT
	 */
	private Display display;
	private Shell shell;
	private Font font;
		        
	/*
	 * Constructor
	 */
	public DibujoSWT() {
		inicializar();
		crearGUI();		
	}
			
	/*
	 * Este es el metodo que se ejecuta cuando el usario 
	 * invoca al metodo dibujar() de la RefinedAbstraction.
	 * Se almacenan los parametros en los array correspondientes
	 * para cuando llegue el momento de pintar las figuras.	 
	 */
	@Override
	public void dibujarCirculo(int x, int y, int radio) {				
		this.x.add(x);
		this.y.add(y);
		this.radio.add(radio);
		
		System.out.printf(
				"DibujoPlataformaSWT. Circulo en %d:%d con radio %d\n", x, y,
				radio);			
	}

	/*
	 * El usuario llama a esperar() para indicar que ya 
	 * ha terminado de dibujar. esperar() invoca a 
	 * shell.redraw(), lo que produce que el listener de
	 * pintado llame a dibujarEnPantalla().  
	 */
	@Override
	public void esperar() {	
		shell.redraw();
		// El codigo siguiente mantiene la GUI en standby, 
		// pendiente de cualquier evento que se produzca.
		System.out.println("Dibujo finalizado.");
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}		
		display.dispose();		
	}
	
	/*	METODOS PRIVADOS */
	
	/*
	 * Crear los objetos
	 */
	private void inicializar() {
		display = new Display();
		shell = new Shell(display);
		font = new Font(display,"Arial",8,SWT.NORMAL);
		
		x = new ArrayList<Integer>();
		y = new ArrayList<Integer>();
		radio = new ArrayList<Integer>();
	}
	
	/*
	 * Crear la GUI y declarar el listener de pintado
	 */
	private void crearGUI() {
		shell.setText("API de SWT");		
		shell.setLocation(20, 20);
		shell.setSize(FRAME_WIDTH, FRAME_HEIGHT);		
		shell.open();
		// Listener para proceder cuando se pinte en la pantalla
		shell.addPaintListener(new PaintListener() {			
			public void paintControl(PaintEvent event) {			
				GC gc = event.gc;
				dibujarEnPantalla(gc);
				gc.dispose();
			}				
		});		
	}
	
	/*	 
	 * Este metodo recorre todas las figuras enviadas por
	 * el usuario y las pinta (muestra). 
	 */
	private void dibujarEnPantalla(GC gc) {
		gc.setAntialias(SWT.ON);
        gc.setFont(font);
        // Para saber el número de figuras se ha tomado 'x'
        // pero hubiera funcionado también con 'y' y con 'radio'.
        for (int i=0; i<x.size(); i++) {
        	gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
	        gc.setBackground(display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
	        gc.drawText("("+x.get(i)+","+y.get(i)+") r:" 
	        		+ radio.get(i), x.get(i)-15, y.get(i)-15);
			
			gc.setBackground(display.getSystemColor(SWT.COLOR_BLUE)); 
	        gc.fillOval(x.get(i), y.get(i), radio.get(i), radio.get(i)); 
        }        
	}
}
