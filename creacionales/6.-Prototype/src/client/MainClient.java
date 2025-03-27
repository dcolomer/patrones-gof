package creacionales.prototype.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Point2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import creacionales.prototype.Figura2D;
import creacionales.prototype.PrototypeManager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

/*
 * Editor Grafico muy simple que ilustra el funcionamiento del 
 * patron Prototype. Adopta el papel 'Client' en el patrón.
 * 
 * Contiene una barra de herramientas que permite añadir al
 * editor elipses y rectangulos. La unica operacion que 
 * podemos hacer con estas figuras es moverlas.
 * 
 * Notad que esta clase no trabaja con ninguna clase concreta,
 * sólo con el tipo interfaz Figura2D.
 */
public class MainClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int FRAME_WIDTH = 450, FRAME_HEIGHT = 450;
		
	private Figura2D figuraSeleccionada, figuraEnTransito;
	private Point2D inicioDrag;
	private JButton btnRectangulo, btnElipse;
		
	// Lista para almacenar las figuras creadas por el usuario
	private static List<Figura2D> figuras = 
		new LinkedList<Figura2D>();
		
	public static List<Figura2D> getFiguras() {
		return figuras;
	}
	
	/*
	 * Metodo main
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainClient inst = new MainClient();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	/*
	 * Constructor
	 */
	public MainClient() {
		super();
		initGUI();
		inicializarListeners(); // Manejadores de eventos de raton
	}	

	private void initGUI() {
		/*
		 * Creamos la barra de herramientas y la posicionamos en el 
		 * formulario. Se crean los manejadores de eventos de boton
		 * para crear figuras 
		 */		 
		crearBarraHerramientas();
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*
	 * Metodo invocado por la MVJ para pintar graficos.
	 * Al arrancar la aplicacion la MVJ llama una vez
	 * a este metodo. Nosotros somos responsables de
	 * llamarlo mediante repaint() cuando necesitemos
	 * reflejar algún cambio en el escenario. 
	 */
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		
		// Si hay figuras que pintar...que se pinten
		if (!figuras.isEmpty()) {			
			for (Figura2D figura : figuras) {
				if (figura != null) {					
					figura.dibujar(g2);
				}
			}			
		}
	}

	/*
	 * Crear los botones de la barra de herramientas
	 * y configurar el comportamiento de los botones.
	 * 
	 * Notad que se llama a la factoria PrototypeManager
	 * para obtener una instancia de cada ConcretePrototype.
	 * Estas instancias quedan cacheadas en un HashMap. Las
	 * utilizamos como moldes para crear duplicados. De esta
	 * manera si, por ejemplo, un usurio añade 3 elipses cada
	 * una de las figuras será un objeto independiente (con
	 * su propia direccion de memoria) del objeto real 
	 * almancenado en el HashMap.
	 */
	private void crearBarraHerramientas() {
		JPanel barra = new JPanel();
		// Boton para crear elipses
		btnElipse = new JButton("Elipse");
		btnElipse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				Figura2D elipse = PrototypeManager
					.getInstancia().getPrototipo(Figura2D.ELIPSE);				
				figuras.add(elipse); // añadir la figura a la lista
				repaint(); // mostrar los cambios al usuario					
			}				
		});		
	
		// Boton para crear Rectangulos
		btnRectangulo = new JButton("Rectangulo");
		btnRectangulo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Figura2D rectangulo = PrototypeManager
					.getInstancia().getPrototipo(Figura2D.RECTANGULO);				
				figuras.add(rectangulo);
				repaint();
			}				
		});
			
		barra.add(btnElipse);
		barra.add(btnRectangulo);
		add(barra);
	}
	
	/*
	 * Capturamos los eventos: 
	 * pulsar/soltar botón ratón
	 * arrastrar ratón con botón pulsado (drag)
	 */
	private void inicializarListeners() {
		addMouseListener(new MouseAdapter() {
			// Cuando el usuario pulsa el boton del raton
			@Override
			public void mousePressed(MouseEvent e) {
				// obtenemos la coordenada pulsada
				inicioDrag = new Point(e.getX(), e.getY());
				// revisamos si en esa coordenada se encuentra
				// alguna de nuestras figuras
				for (Figura2D figura : figuras) {
					if (figura != null) {
						// Cuando hay coincidencia, pintamos la 
						// figura de rojo
						if (figura.getFigura().contains(inicioDrag)) {
							figuraSeleccionada = figura;
							figura.setColor(Color.RED);
							repaint(); // para que el usuario vea el cambio
							break;
						}
					}
				}																
			} 
			
			// Cuando el usuario suelta el boton del raton
			@Override
			public void mouseReleased(MouseEvent e) {
				// Si se estaba arrastrando alguna figura
				if (figuraEnTransito != null) {				
					// la pintamos del color original
					figuraEnTransito.setColor(Color.BLUE);					
					/*
					 * Quitamos de la lista la figura original, pues ya no es
					 * necesario mostrarle al usuario la posicion de la figura
					 * antes de soltarla en una nueva posicion
					 */
					figuras.remove(figuraSeleccionada);
					// Todo a null para cuando haya que comenzar otro drag & drop
					figuraSeleccionada = null;
					figuraEnTransito = null;
					inicioDrag = null;
					repaint(); // para que el usuario vea el cambio					
				}
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {	
			// Cuando se está produciendo el drag
			@Override
			public void mouseDragged(MouseEvent e) {
				// Si hay seleccionada alguna figura 
				if (figuraSeleccionada != null) {
					/*
					 * Si aun no ha comenzado el arrastre tenemos que
					 * obtener un duplicado de la figura y añadirla
					 * a la lista para que paint() la muestre
					 */
					if (figuraEnTransito == null) {					
						
						/*
						 * Aqui no sabemos el tipo de figura que queremos.
						 * Por tanto, llamamos al método sobrecargado de 
						 * PrototypeManager que acepta un subtipo de
						 * Figura2D como argumento.
						 */																		
						figuraEnTransito = PrototypeManager
							.getInstancia().getPrototipo(figuraSeleccionada);
						
						figuras.add(figuraEnTransito);						
					}
					// Adaptar el duplicado: cambiar sus coordendas
					// según la posición actual del raton
					figuraEnTransito.getFigura().setFrame(
							e.getX(), 
							e.getY(), 
							figuraEnTransito.getFigura().getWidth(), 
							figuraEnTransito.getFigura().getHeight());					
					repaint(); // para que el usuario vea el cambio
				}
			}
		});
	}					
}