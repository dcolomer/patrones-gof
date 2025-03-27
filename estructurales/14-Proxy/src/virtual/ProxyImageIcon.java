package estructurales.proxy.virtual;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/*
 * Proxy.
 * Esta clase sustituye a un objeto ImageIcon que puede tener tres
 * imágenes: 'ausente', 'cargando' y la imagen de destino (la real)
 */
public class ProxyImageIcon extends ImageIcon implements Runnable {
	private static final long serialVersionUID = 1L;

	static final ImageIcon AUSENTE = new ImageIcon(
			ClassLoader.getSystemResource("estructurales/proxy/virtual/recursos/ausente.png"));

	static final ImageIcon CARGANDO = new ImageIcon(
			ClassLoader.getSystemResource("estructurales/proxy/virtual/recursos/cargando.png"));

	protected String REAL;
	protected JFrame mainGUI;

	/*
	 * Constructor.
	 * Carga la imagen 'ausente' y almacena el nombre del fichero
	 * de la imagen real para cuando sea necesario cargarla	 
	 */
	public ProxyImageIcon(String nomFicheroImagenReal) {
		// Establecer la imagen que debe mostrar el 
		// RealSubject de forma predeterminada
		super(AUSENTE.getImage());
		// Almacenar el nombre del fichero de la imagen real
		this.REAL = nomFicheroImagenReal;
	}

	/*
	 * Cargar la imagen real.
	 * 
	 * @param mainGUI: es el JFrame que se debe repintar al 
	 * cambiar de imagen, tanto de 'ausente' a 'cargando,
	 * como de 'cargando' a 'REAL'
	 */
	public void load(JFrame mainGUI) {
		this.mainGUI = mainGUI;
		// Establecer la imagen que debe mostrar el RealSubject  
		// cuando el usuario ha pulsado el botón de cargar imagen
		setImage(CARGANDO.getImage());
		mainGUI.repaint(); // forzamos el repintado de la ventana principal
		
		// Arrancamos en un nuevo hilo la carga de la imagen real
		new Thread(this).start();
	}

	/*
	 * Cargar en un hilo separado la imagen real.
	 */
	@Override
	public void run() {
		setImage(
				new ImageIcon(
						ClassLoader.getSystemResource(REAL)
				).getImage());
		mainGUI.pack();
	}
}