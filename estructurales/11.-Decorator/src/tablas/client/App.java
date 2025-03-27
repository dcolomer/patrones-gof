package estructurales.decorator.tablas.client;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * Esta clase es de apoyo para la clase MainClient.
 * Basicamente se encarga de:
 * -Gestionar el fichero de propiedades, que contiene
 * los literales para la aplicaci�n.
 * -Dibujar la interfaz gr�fica.
 * 
 * Notad que tiene acceso de paquete
 */
class App extends WindowAdapter {
	
	static private JPanel barraEstado = new JPanel();
	static private JLabel lblEstado = new JLabel(" ");
	
	// Fichero de recursos
	static private ResourceBundle recursos;
	
	// Nombre del paquete de la clase
	static private String nomPaquete = 
		App.class.getPackage().getName(); 
	
	// Ubicacion para cargar fichero de recursos 
	static private String nomBaseFicheroRecursos = 
		nomPaquete + ".recursos";
	
	/*
	 * Inicializador est�tico responsable de cargar el fichero
	 * de propieades que contiene los literales de la aplicaci�n.
	 * 
	 * El fichero que se selecciona es el que se corresponde con
	 * la configuraci�n regional del ordenador donde se ejecuta
	 * la aplicaci�n.
	 * 
	 * Los ficheros de propiedades (uno por cada idioma) deben
	 * encontrarse en el mismo paquete que la clase App, es
	 * decir en 'estructurales.decorator.tablas.client' y deben
	 * llamar 'recursos_XX_YY', donde XX es el c�digo de idioma
	 * e YY la posible variaci�n del lenguaje. Por ejemplo,
	 * "espa�ol Espa�a" ser�a es_ES.	 
	 */
	static {
		// Configuracion regional en Espa�ol		
		recursos = ResourceBundle
			.getBundle(nomBaseFicheroRecursos, Locale.getDefault());
		/*
		 * Si queremos simular que ejecutamos la aplicacion con una
		 * configuracion anglosajona, tan solo tenemos que utilizar
		 * las siguiente sentencia y comentar la anterior.
		 */
		/*recursos = ResourceBundle
			.getBundle(nomBaseFicheroRecursos, Locale.ENGLISH);*/
	}
	
	// Constructor privado. De esta manera evitamos que la clase 
	// se puede instanciar
	private App() {
		
	}

	/*
	 * Metodo que acondiciona gr�ficamente a MainClient (el JFrame).
	 * Le a�ade una barra de estado, bordes, etc.
	 */
	static void launch(final JFrame f, String title, 
			final int x, final int y, final int w, int h) 
	{
		barraEstado.setBorder(BorderFactory.createEtchedBorder());
		barraEstado.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		barraEstado.add(lblEstado);
		lblEstado.setHorizontalAlignment(JLabel.LEFT);
		f.setTitle(getValor(title));
		f.setBounds(x, y, w, h);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	static JPanel getStatusArea() { return barraEstado;	}

	static void showStatus(String s) {
		lblEstado.setText(s);
	}

	/*
	 * Este m�todo retorna el valor le�do del fichero de propiedades 
	 * que corresponde a la clave recibida por par�metro.
	 */
	static String getValor(String clave) {
		if (recursos != null) {
			return recursos.getString(clave);
		}
		return null;
	}
}