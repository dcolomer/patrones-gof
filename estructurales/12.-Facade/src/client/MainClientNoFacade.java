package estructurales.facade.client;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import estructurales.facade.subsistema.Direccion;
import estructurales.facade.subsistema.Tarjeta;
import estructurales.facade.subsistema.TarjetaTipo;
import estructurales.facade.subsistema.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainClientNoFacade {

	private JFrame marco;	
	private JTextField txtNombre, txtApellidos;	
	private JTextField txtDireccion, txtCiudad, txtProvincia;	
	private JComboBox cmbTarjeta;
	private JTextField txtNumeroTarjeta, txtFechaCaducidad;	
	private JLabel lblMensaje;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainClientNoFacade window = new MainClientNoFacade();
					window.marco.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainClientNoFacade() {
		initialize();
	}

	private void initialize() {
		marco = new JFrame();
		marco.setTitle("Formulario");
		marco.setBounds(100, 100, 441, 438);
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marco.getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("Rellene el siguiente formulario");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(Color.BLUE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitulo.setBounds(10, 11, 422, 20);
		marco.getContentPane().add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(21, 56, 46, 14);
		marco.getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(135, 53, 188, 25);
		marco.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblApellidos.setBounds(21, 91, 76, 14);
		marco.getContentPane().add(lblApellidos);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(135, 84, 273, 25);
		marco.getContentPane().add(txtApellidos);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDireccin.setBounds(21, 126, 76, 14);
		marco.getContentPane().add(lblDireccin);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(135, 121, 273, 25);
		marco.getContentPane().add(txtDireccion);
		
		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCiudad.setBounds(21, 162, 76, 14);
		marco.getContentPane().add(lblCiudad);
		
		txtCiudad = new JTextField();
		txtCiudad.setColumns(10);
		txtCiudad.setBounds(135, 156, 188, 25);
		marco.getContentPane().add(txtCiudad);
		
		JLabel lblTarjeta = new JLabel("Tarjeta");
		lblTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTarjeta.setBounds(21, 233, 76, 14);
		marco.getContentPane().add(lblTarjeta);
		
		cmbTarjeta = new JComboBox();
		cmbTarjeta.addItem(TarjetaTipo.VISA);
		cmbTarjeta.addItem(TarjetaTipo.MASTER);
		cmbTarjeta.addItem(TarjetaTipo.DISCOVER);
		cmbTarjeta.setBounds(135, 225, 98, 25);
		marco.getContentPane().add(cmbTarjeta);
		
		JLabel lblNumeroTarjeta = new JLabel("N\u00FAmero tarjeta");
		lblNumeroTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumeroTarjeta.setBounds(21, 267, 114, 14);
		marco.getContentPane().add(lblNumeroTarjeta);
		
		txtNumeroTarjeta = new JTextField();
		txtNumeroTarjeta.setColumns(10);
		txtNumeroTarjeta.setBounds(135, 260, 188, 25);
		marco.getContentPane().add(txtNumeroTarjeta);
		
		JLabel lblFechaCaducidad = new JLabel("Fecha caducidad");
		lblFechaCaducidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaCaducidad.setBounds(21, 300, 114, 14);
		marco.getContentPane().add(lblFechaCaducidad);
		
		txtFechaCaducidad = new JTextField();
		txtFechaCaducidad.setColumns(10);
		txtFechaCaducidad.setBounds(135, 295, 76, 25);
		marco.getContentPane().add(txtFechaCaducidad);						
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblProvincia.setBounds(21, 200, 76, 14);
		marco.getContentPane().add(lblProvincia);
		
		txtProvincia = new JTextField();
		txtProvincia.setColumns(10);
		txtProvincia.setBounds(135, 193, 188, 25);
		marco.getContentPane().add(txtProvincia);
		
		lblMensaje = new JLabel("");
		lblMensaje.setBounds(0, 379, 432, 25);
		marco.getContentPane().add(lblMensaje);
		
		JButton btnValidarYGuardar = new JButton("Validar y Guardar");
		btnValidarYGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarYguardar();
			}
		});
		btnValidarYGuardar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnValidarYGuardar.setBounds(110, 336, 141, 31);
		marco.getContentPane().add(btnValidarYGuardar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSalir.setBounds(270, 336, 70, 31);
		marco.getContentPane().add(btnSalir);
	}
	
	private void validarYguardar() {
		// Obtener los valores de los controles
		String nombre = txtNombre.getText().trim();
		String apellidos = txtApellidos.getText().trim();
		String direc = txtDireccion.getText().trim();
		String ciudad = txtCiudad.getText().trim();
		String provincia = txtProvincia.getText().trim();
		TarjetaTipo tipoTarjeta = 
			(TarjetaTipo) cmbTarjeta.getSelectedItem();
		String numTarjeta = txtNumeroTarjeta.getText().trim();
		String fechaCadTarjeta = txtFechaCaducidad.getText().trim();

		// Proceso de validacion
		boolean formValidado = false;

		Usuario usuario = 
			new Usuario(nombre, apellidos);
		
		Direccion direccion = 
			new Direccion(direc, ciudad, provincia);
		
		Tarjeta tarjeta = 
			new Tarjeta(tipoTarjeta, numTarjeta, fechaCadTarjeta);

		formValidado = usuario.esValido() && direccion.esValido()
				&& tarjeta.esValido();

		if (formValidado) {
			usuario.guardarDatos();
			direccion.guardarDatos();
			tarjeta.guardarDatos();
		}

		// Create a facade instance
		/*
		 * CustomerFacade facade = new CustomerFacade();
		 * facade.setFName(firstName); facade.setLName(lastName);
		 * facade.setAddress(address); facade.setCity(city);
		 * facade.setState(state); facade.setCardType(cardType);
		 * facade.setCardNumber(cardNumber);
		 * facade.setCardExpDate(cardExpDate);
		 */

		// Client is not required to access subsystem components.
		// boolean result = facade.saveCustomerData();

		String mensaje;
		if (formValidado) {
			mensaje = " Formulario correcto: los datos se han guardado en disco. ";
		} else {
			mensaje = " Formulario INCORRECTO: los datos no se han guardado. ";
		}

		lblMensaje.setText(mensaje);
		
	}
}
