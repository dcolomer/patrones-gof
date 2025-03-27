package comportamiento.observer.reports;

import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Ventana principal de la aplicación, desde la que 
 * el usuario puede seleccionar un departamento del 
 * combo, para ver los informes.
 * Esta ventana en un wrapper que envuelve al subject,
 * es decir, al objeto ReportManager, que es el objeto
 * observable. No obstante, hay que tener en cuenta que
 * es ReportManagerGUI la que genera los cambios de
 * estado, cuando el usuario selecciona un departamente
 * y pulsa el botón Aceptar.
 */
public class ReportManagerGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final String Fichero = "ventas.dat";
	
	private String departmento;
	private ReportManager reportManager;
	
	private JPanel contentPane;
	private JComboBox<String> cmbDepartmentos;
	private JButton btnAceptar, btnSalir;
	private JLabel lblDepartmentos;
	
	public ReportManagerGUI() {
		super("Gestor de informes de ventas");
		configurarGUI();
		reportManager = new ReportManager();
	}
	
	private void configurarGUI() {		
		preConfigurarPanel();				
		configurarEtiqueta();
		configurarCombos();
		configurarBotones();
		addControlesToPanel();
		configurarVentana();		
	}

	public String getDepartmento() {
		return departmento;
	}
	
	public void setDepartmento(String dept) {
		departmento = dept;
	}
	
	public ReportManager getReportManager() {
		return reportManager;
	}

	private void preConfigurarPanel() {
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	}
	
	private void configurarEtiqueta() {
		lblDepartmentos = new JLabel("Seleccionar un departamento:");
		lblDepartmentos.setBounds(30, 47, 215, 15);		
	}
	
	private void configurarCombos() {				
		cmbDepartmentos = new JComboBox<String>();		
		cmbDepartmentos.setBounds(263, 42, 150, 24);		
		cmbDepartmentos.addItem("Consultoria");
		cmbDepartmentos.addItem("Electronica");
		cmbDepartmentos.addItem("Electricidad");
	}
	
	private void configurarBotones() {
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new AceptarListener());
		btnAceptar.setBounds(133, 98, 94, 25);
		btnAceptar.setMnemonic(KeyEvent.VK_S);
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new SalirListener());
		btnSalir.setBounds(254, 98, 99, 25);
		btnSalir.setMnemonic(KeyEvent.VK_X);
	}

	private void addControlesToPanel() {		
		contentPane.add(lblDepartmentos);
		contentPane.add(cmbDepartmentos);
		contentPane.add(btnAceptar);
		contentPane.add(btnSalir);		
	}
	
	private void configurarVentana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(475, 200);
		setLocation(50, 50);
		setVisible(true);		
	}
	
	private class AceptarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			String dept = (String) cmbDepartmentos.getSelectedItem();				
			setDepartmento(dept); // Cambiar el estado de la clase
			reportManager.notificarSubscriptores(); // Notificar a los observadores
		}
	}
	
	private class SalirListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			System.exit(0);
		}
	}
}

