package estructurales.composite.diagramabarras;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/*
 * Clase que representa la ventana donde se pinta
 * el diagrama de barras. Tambien se encarga de crear todos
 * los elementos graficos necesarios. Conduce todo el
 * proceso de creacion del diagrama.
 * 
 * Permite crear diagramas con barras (rectangulos) huecas 
 * o solidas. Para ello utiliza una factoria que lee la clase 
 * de rectangulo a utilizar desde un fichero de propiedades. 
 */
public class Ventana extends JFrame {

	/*********** DATOS *********************************/
	
	private static final long serialVersionUID = 1L;
	
	public static int FRAME_WIDTH = 475, FRAME_HEIGHT = 475;

	/*
	 * Arrays con los datos a mostrar en el diagrama.
	 * Los debe proporcionar una clase cliente	
	 */
	private String[] elementosX;			
	private float[] elementosY;
	private String[] elementosDeRefenciaY;
	
	/*
	 * Objecto compuesto que contendrá todos los elementos 
	 * graficos que conforman el diagrama de barras.
	 * 
	 * Desde el punto de vista de esta clase cliente, la 
	 * construccion del diagrama de barras consiste en 
	 * ir llamando al metodo add() del objeto 'diagrama' 
	 * cada vez que quiere agregar un elemento grafico 
	 * que tenga que mostrarse en el diagrama de barras.
	 */
	private DiagramaBarras diagrama = new DiagramaBarras();
	
	/*
	 * Coordenadas para los ejes X e Y. Los ocho
	 * puntos se pueden representar con cuatro
	 * valores, ya que comparten varios de ellos.
	 * y
	 * |
	 * |___x
	 */
	private static int COORD_X1_EJE_X = 50, COORD_Y_EJE_X = 425, 
		COORD_X2_EJE_X = 425, COORD_Y2_EJE_Y = 75;
	
	/*
	 * Lineas que representan los ejes X e Y del diagrama.
	 */
	private static Linea ejeX = 
		new Linea(COORD_X1_EJE_X, COORD_Y_EJE_X, 
				COORD_X2_EJE_X, COORD_Y_EJE_X);
	
	private static Linea ejeY = 
		new Linea(COORD_X1_EJE_X, COORD_Y_EJE_X, 
				COORD_X1_EJE_X, COORD_Y2_EJE_Y);
	
	// Longitud de cada eje (X e Y miden lo mismo)	
	private static int len_eje;
	
	// Numero de segmentos para el ejeX	 
	private int total_segmentos_horizontales;
	
	// Numero de segmentos para el ejeY	
	private int total_segmentos_verticales;

	// Ancho de cada segmento del ejeX	 
	private int ancho_segmento_horizontal;	
	
	// Ancho de cada segmento del ejeY	
	private int ancho_segmento_vertical;	
	
	/*********** METODOS *********************************/
	
	/*
	 * Constructor
	 */
	public Ventana(String[] elementosX_, 
			float[] elementosY_, String[] elementosDeRefenciaY_ ) 
	{
		super();
		
		// Volcado de los datos del diagrama procedentes de la clase cliente 
		elementosX = elementosX_;
		elementosY = elementosY_;
		elementosDeRefenciaY = elementosDeRefenciaY_;		
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {							
				iniciarPrograma();
			}
		});
	}
	
	/*
	 * Metodo invocado por la MVJ para pintar graficos.
	 * Al arrancar la aplicacion la MVJ llama una vez
	 * a este metodo. Nosotros somos responsables de
	 * llamarlo mediante repaint() cuando necesitemos
	 * reflejar algún cambio en el escenario. 
	 */
	@Override
	public void paint(Graphics g) {		
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		 
		/*
		 * A tener en cuenta que a pesar de que 
		 * tenemos figuras simples y compuestas,
		 * este metodos las trata a todas por igual,
		 * usando la interfaz Grafico
		 */
		diagrama.dibujar(g2);
	}
	
	/*
	 * Ejecucion de las rutinas principales
	 */
	private void iniciarPrograma() {
		inicializaciones();
		initGUI();
		crearTitulo("Notas examen Java");
		crearEjes();
		crearBarras();
		
		// Esta llamada produce que la MVJ llame a paint(), 
		// lo que produce que se recorran todos los hijos
		// del objeto compuesto 'diagrama' 
		repaint(); 		
	}	
	
	private void inicializaciones() {		
		/*
		 * Añadimos los ejes X e Y al diagrama de barras.
		 */
		diagrama.add(ejeX);		
		diagrama.add(ejeY);		
		
		/*
		 * Longitud de cada eje (X e Y miden lo mismo)
		 */
		len_eje = (int) (ejeX.getLinea().x2 - ejeX.getLinea().x1);
		
		/*
		 * Numero de segmentos para el ejeX
		 */
		total_segmentos_horizontales = elementosX.length;
		
		/*
		 * Numero de segmentos para el ejeY
		 */
		total_segmentos_verticales = 
			elementosDeRefenciaY.length;
		
		/*
		 * Ancho de cada segmento del ejeX
		 */
		ancho_segmento_horizontal = 
			len_eje / total_segmentos_horizontales;	
		
		/*
		 * Ancho de cada segmento del ejeY
		 */
		ancho_segmento_vertical = 
			len_eje / total_segmentos_verticales;
		
	}

	/*
	 * Establecer la interfaz de usuario
	 */
	private void initGUI() {				
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(false); // No dejamos redimensionar
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Centrar
		setTitle("Ejemplo patron Composite");
		setVisible(true);
	}	

	/*
	 * Crear el titulo y añadirlo al objeto 'diagrama'.
	 * El titulo aparecera con letra grande,
	 * centrado horizontalmente, en la parte superior.
	 */
	private void crearTitulo(String titulo) {
		boolean esUnTitulo = true;
		diagrama.add(new Texto(titulo, esUnTitulo));		
	}

	/*
	 * Crear los segmentos de los ejes X e Y y los 
	 * valores textuales asociados a cada uno. 
	 */
	private void crearEjes() {		
		crearSegmentosEjeX();
		crearValoresEjeX();		
		crearSegmentosEjeY();
		crearValoresEjeY();						
	}
	
	/*
	 * Crear los segmentos del eje X y añadirlos al objeto 'diagrama'
	 */
	private void crearSegmentosEjeX() {		
		int posX_base = (int)ejeX.getLinea().x1;
		int ancho_acumulado = posX_base + ancho_segmento_horizontal;
		
		for (int i=0; i<total_segmentos_horizontales; i++) {				
			diagrama.add(
					new Linea(ancho_acumulado, (int)ejeX.getLinea().y1, 
							ancho_acumulado, (int)ejeX.getLinea().y1 + 3)
			);			
			ancho_acumulado += ancho_segmento_horizontal;
		}						
	}
	
	/*
	 * Crear los valores del eje X y añadirlos al objeto 'diagrama'
	 */
	private void crearValoresEjeX() {		
		int posX_base = (int)ejeX.getLinea().x1;
		int ancho_acumulado = posX_base + ancho_segmento_horizontal;
		
		for (int i=0; i<elementosX.length; i++) {
			/*
			 * Para evitar que se solapen horizontalmente los textos,
			 * alternamos su coordenada vertical.
			 */
			int posYtexto = (int)ejeX.getLinea().y1;			
			posYtexto = (i % 2 == 0) ? posYtexto + 15 : posYtexto + 30;
				
			diagrama.add(
					new Texto(
							String.valueOf(elementosX[i]),
							ancho_acumulado - ancho_segmento_horizontal, 
							posYtexto
							)
					);
			ancho_acumulado += ancho_segmento_horizontal;
		}						
	}
	
	/*
	 * Crear los segmentos del eje Y y añadirlos al objeto 'diagrama'
	 */	
	private void crearSegmentosEjeY() {		
		int posY_base = (int)ejeY.getLinea().y1;
		int alto_acumulado = posY_base - ancho_segmento_vertical;
		
		for (int i=0; i<total_segmentos_verticales - 1; i++) {						
			diagrama.add(
					new Linea((int)ejeY.getLinea().x1, alto_acumulado,
							(int)ejeY.getLinea().x1-3, alto_acumulado)
			);			
			alto_acumulado -= ancho_segmento_vertical;
		}				
	}
			
	/*
	 * Crear los valores del eje Y y añadirlos al objeto 'diagrama'
	 */
	private void crearValoresEjeY() {		
		int posY_base = (int)ejeY.getLinea().y1;
		int alto_acumulado = posY_base - ancho_segmento_vertical;
		
		for (int i=0; i<total_segmentos_verticales; i++) {		
			// Mostrar los valores de referencia en cada segmento del eje Y
			diagrama.add(
					new Texto(
							String.valueOf(elementosDeRefenciaY[i]), 
							(int)ejeY.getLinea().x1-25, // el -25 es para evitar que quede pegado al eje
							alto_acumulado + ancho_segmento_vertical
							)
					);
			alto_acumulado -= ancho_segmento_vertical;
		}				
	}
	
	/*
	 * Crear los rectangulos que representan las barras
	 * y añadirlos al objeto 'diagrama'.
	 */
	private void crearBarras() {			
		int x1 = (int)ejeX.getLinea().x1;
		int y1 = (int)ejeY.getLinea().y1;
	
		// Se crean tantas barras como elementos haya para el eje X
		for (int i=0; i<elementosX.length; i++) {
			
			// Calculo de la Y que le pertenece al elemento X en curso
			int y2 = y1 - ((int) elementosY[i] * ancho_segmento_vertical);
	 
			/*
			 * Uso de la factoria para la obtencion de la clase concreta
			 * a utilizar para represtar las barras. Tenemos de dos tipos:
			 * RectanguloHueco y RectanguloRelleno, ambas tienen el mismo
			 * constructor, por lo que los parametros son los mismos.
			 * 
			 * El primer parametro, tipobarra, es el nombre de la propiedad
			 * que la factoria necesita conocer para leer del fichero de
			 * propiedades donde se especifica el nombre de la clase a instanciar.
			 */
			Grafico barra = FactoriaRectangulos.getInstance().create(
					"tipobarra",			
					new Linea(x1, y1, x1, y2),
					new Linea(x1, y2, x1 + ancho_segmento_horizontal, y2),
					new Linea(x1 + ancho_segmento_horizontal, y2, x1 + ancho_segmento_horizontal, y1),
					new Linea(x1 + ancho_segmento_horizontal, y1, x1, y1));
			diagrama.add(barra);
			x1 += ancho_segmento_horizontal;
		}
	}
}
