package estructurales.proxy.protection_proxy.presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import estructurales.proxy.protection_proxy.servicios.ProxyServicioNominas;
import estructurales.proxy.protection_proxy.servicios.ServicioNominas;
import estructurales.proxy.protection_proxy.servicios.ValidacionException;

/*
 * Clase que muestra una cuadro de dialogo para que el 
 * usuario introduzca sus credenciales. En caso de no
 * validarse correctamente no se permite el acceso a la 
 * aplicacion y finaliza. 
 */
public class Login extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final int LIMITE_ERRORES_VALIDACION = 3;
	
	protected JFrame propietario;
	
	private JLabel lblUsuario, lblContrasena, lblIconoLlaves;
	protected JTextField txtUsuario;
	protected JPasswordField txtContrasena;
	protected JButton btnAcceder, btnSalir;
	protected int contador_errores;
	
	private ServicioNominas servicio;
		
	/*
	 * Constructor.
	 * @propietario: la ventana que contiene al cuadro de dialogo
	 * @modalidad: modal/no modal (normalmente modal) 
	 */
	public Login(JFrame propietario, String tit, Dialog.ModalityType modalidad) {
		// Invocamos a super, indicando adicionalmente
		// el titulo para la ventana
		super(propietario, tit, modalidad);		
		this.propietario = propietario;		
		this.setResizable(false);		
		this.setSize(337, 175);
			
		this.setLocationRelativeTo(null);
		
		create(); // Creacion de los controles		
	}

	/*
	 * Creacion de la GUI
	 */
	private void create() {
		
		ConfiguradorGrafico.setLookAndFeel();
		
		// Listener de la ventana. Si el usuario
		// la cierra, finaliza la aplicacion
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				salir();
			}
		});

		// No usamos ningun layout manager
		setLayout(null);										
		 
		lblUsuario = new JLabel();
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setForeground(new Color(0, 0, 255));
		lblUsuario.setText("usuario:");
		lblUsuario.setBounds(110, 9, 106, 18);
		add(lblUsuario);
		
		lblContrasena = new JLabel();
		lblContrasena.setHorizontalAlignment(SwingConstants.LEFT);
		lblContrasena.setForeground(new Color(0, 0, 255));
		lblContrasena.setText("contrase\u00F1a:");
		lblContrasena.setBounds(110, 54, 97, 18);
		add(lblContrasena);
				
		// Cargamos la imagen del icono manualmente
		lblIconoLlaves = new JLabel(crearImageIcon(this.getClass(), "llaves.png"));
		lblIconoLlaves.setBounds(6, 24, 86, 81);		
		add(lblIconoLlaves);		
		
		txtUsuario = new JTextField();
		txtUsuario.setForeground(new Color(0, 0, 255));
		txtUsuario.setSelectedTextColor(new Color(0, 0, 255));
		txtUsuario.setToolTipText("Introducir un nombre de usuario valido");
		txtUsuario.setBounds(110, 27, 183, 22);		
		add(txtUsuario);
		
		txtContrasena = new JPasswordField();
		txtContrasena.setForeground(new Color(0, 0, 255));
		txtContrasena.setToolTipText("Introducir la contrase\u00F1a");
		txtContrasena.setBounds(110, 71, 183, 22);
		txtContrasena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAccederPulsado(e);
			}
		});
		add(txtContrasena);
		
		btnAcceder = new JButton();		
		btnAcceder.setBounds(110, 105, 85, 27);
		btnAcceder.setText("Acceder");
		btnAcceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAccederPulsado(e);
			}

		});
		add(btnAcceder);
		
		btnSalir = new JButton();
		btnSalir.setBounds(206, 105, 85, 27);
		btnSalir.setText("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				salir();
			}
		});		
		add(btnSalir);
	}


	/*
	 * Manejador para la pulsacion del boton 'Acceder'.	 	 
	 */
	protected void btnAccederPulsado(ActionEvent e) {
		
		String username = txtUsuario.getText();
		String password = new String(txtContrasena.getPassword());

		/*
		 * Se comprueba que ni el nombre de usuario ni la
		 * contrasena esten en blanco. En caso contrario se
		 * muestra una ventana informando de este hecho.
		 */
		if (username.equals("") || password.equals("")) {
			String msg="Debe proporcionar un nombre de usuario y una contrase\u00F1a.";
			btnAcceder.setEnabled(false);
			JLabel errorFields = new JLabel(
					"<HTML><FONT COLOR = Blue>"+msg+"</FONT></HTML>");
			JOptionPane.showMessageDialog(null, errorFields);			
			
			btnAcceder.setEnabled(true);
			txtUsuario.requestFocusInWindow();
			
		} else { // Ambos campos tienen valor			
			try {								
				// Intentamos acceder al proxy
				servicio = new ProxyServicioNominas(username, password);
				
				if (propietario != null) {
					String tit = propietario.getTitle() + " - usuario: " + username;
					propietario.setTitle(tit);
				}
				dispose();
				
			} catch (ValidacionException e1) {
				String msg = "Las credenciales son incorrectas.";				
				btnAcceder.setEnabled(false);
				contador_errores++;
				
				if (contador_errores == LIMITE_ERRORES_VALIDACION) {
					String msg2 = msg + 
						"<br/>Ha superado el numero de intentos maximo. <br/>La aplicacion no puede continuar.";
					JLabel errorFields = new JLabel(
					"<HTML><FONT COLOR = Red>" + msg2 + "</FONT></HTML>");
					JOptionPane.showMessageDialog(null, errorFields);					
					salir();
				} else {					
					JLabel errorFields = new JLabel(
						"<HTML><FONT COLOR = Blue>" + msg + "</FONT></HTML>");					
					JOptionPane.showMessageDialog(null, errorFields);					
				}
				btnAcceder.setEnabled(true);
				txtContrasena.setText("");
				txtUsuario.setSelectionStart(0);
				txtUsuario.setSelectionEnd(txtUsuario.getText().length());
				txtUsuario.requestFocusInWindow();
			}  catch (Exception e2) {
				e2.printStackTrace();
				salir();
			}
		}		
	}
	
	protected void salir() {
		System.exit(0);		
	}
	
	/*
	 * @param clase: this.getClass()
	 * @param path: El paquete relativo a la clase
	 * 
	 * Retorna un objeto ImageIcon a partir de una ruta, 
	 * o null si el path no es correcto.
	 */
	private static ImageIcon crearImageIcon(Class<?> clase, String path) {
		java.net.URL imgURL = clase.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			throw new RuntimeException("Error al crear la imagen");
		}		
	}

	public ServicioNominas getServicio() {		
		return servicio;
	}
}