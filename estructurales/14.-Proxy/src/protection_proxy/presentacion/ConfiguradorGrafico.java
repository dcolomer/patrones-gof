package estructurales.proxy.protection_proxy.presentacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import estructurales.proxy.protection_proxy.modelo.Meses;
import estructurales.proxy.protection_proxy.servicios.ServicioNominas;

public class ConfiguradorGrafico extends JFrame {
	
	private static final long serialVersionUID = 1L;

	JPanel panelBase;
	
	DefaultComboBoxModel cmbEmpleadoModel;
	JComboBox cmbEmpleado, cmbMes;

	JLabel lblEmpleado, lblMes, lblHorasTrabajadas;
	JLabel lblSBAnual, lblRetencion, lblSBMensual, lblSNMensual;
	JLabel lblObservaciones, lblSimboloPorcenRet;

	JButton btnVerNomina;
	JSeparator separator;	

	// ojo, no son editables
	JTextField txtSBAnual, txtRetencion, txtSBMensual;
	JTextField txtNomEmpleado, txtSNMensual, txtObservaciones;

	// Unico campo editable, es una especializacion de JTextField
	NumericTextField txtHorasTrabajadas;
	
	private MainClientGUI gui;
	
	public ConfiguradorGrafico(MainClientGUI gui) {
		this.gui = gui;		
	}
	
	public ConfiguradorGrafico getConfiguradorGrafico() {
		return this;
	}

	public void crearGUI() {
		dibujarGeneralidades();
		dibujarPanelBase();
		dibujarSeparador();
		dibujarEtiquetas();
		dibujarCajasTexto();
		dibujarCombos();
		dibujarBotones();
		crearManejadores();
	}
	
	private void dibujarGeneralidades() {
		setLookAndFeel();
		setResizable(false);
		setTitle("Aplicaci\u00F3n N\u00F3minas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 300);
		setLocationRelativeTo(null);
	}
	
	static void setLookAndFeel() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void dibujarPanelBase() {
		panelBase = new JPanel();
		panelBase.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelBase);
		panelBase.setLayout(null);
	}
	
	private void dibujarSeparador() {
		separator = new JSeparator();
		separator.setBounds(10, 66, 478, 2);
		panelBase.add(separator);
	}

	private void dibujarBotones() {
		btnVerNomina = new JButton("Ver n\u00F3mina");		
		btnVerNomina.setBounds(195, 230, 104, 25);
		panelBase.add(btnVerNomina);
	}
	
	private void dibujarCombos() {
		
		// Obtener del dataSource todos los codigo de empleado
		cmbEmpleadoModel = 
			new DefaultComboBoxModel(gui.getEmpleados());
		
		cmbEmpleado = new JComboBox();		
		cmbEmpleado.setModel(cmbEmpleadoModel);
		cmbEmpleado.setBounds(10, 30, 72, 25);
		panelBase.add(cmbEmpleado);

		cmbMes = new JComboBox(Meses.values());		
		cmbMes.setBounds(338, 30, 79, 25);
		panelBase.add(cmbMes);
	}
	
	private void dibujarEtiquetas() {
		lblEmpleado = new JLabel("Empleado");
		lblEmpleado.setBounds(10, 11, 90, 14);
		panelBase.add(lblEmpleado);

		lblMes = new JLabel("Mes");
		lblMes.setBounds(342, 11, 46, 14);
		panelBase.add(lblMes);

		lblSBMensual = new JLabel("Sueldo bruto mensual");
		lblSBMensual.setBounds(166, 74, 133, 14);
		panelBase.add(lblSBMensual);

		lblHorasTrabajadas = new JLabel("Horas trabajadas");
		lblHorasTrabajadas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHorasTrabajadas.setBounds(384, 11, 104, 14);
		panelBase.add(lblHorasTrabajadas);

		lblRetencion = new JLabel("Retenci\u00F3n");
		lblRetencion.setBounds(302, 74, 72, 14);
		panelBase.add(lblRetencion);

		lblSNMensual = new JLabel("Sueldo neto mensual");
		lblSNMensual.setBounds(374, 74, 124, 14);
		panelBase.add(lblSNMensual);

		lblSimboloPorcenRet = new JLabel("%");
		lblSimboloPorcenRet.setBounds(355, 97, 24, 14);
		panelBase.add(lblSimboloPorcenRet);

		lblSBAnual = new JLabel("Sueldo bruto anual");
		lblSBAnual.setBounds(10, 74, 133, 14);
		panelBase.add(lblSBAnual);

		lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(10, 121, 117, 14);
		panelBase.add(lblObservaciones);
	}
	
	private void dibujarCajasTexto() {
		txtNomEmpleado = new JTextField("----");
		txtNomEmpleado.setBounds(92, 30, 236, 25);
		txtNomEmpleado.setEditable(false);
		txtNomEmpleado.setBackground(Color.yellow);
		panelBase.add(txtNomEmpleado);

		txtHorasTrabajadas = new NumericTextField();
		txtHorasTrabajadas.setText(gui.getStringHorasMensualesOficiales());
		txtHorasTrabajadas.setHorizontalAlignment(SwingConstants.RIGHT);
		txtHorasTrabajadas.setBounds(442, 30, 46, 25);
		panelBase.add(txtHorasTrabajadas);

		txtSBMensual = new JTextField();
		txtSBMensual.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSBMensual.setBounds(166, 92, 115, 25);
		txtSBMensual.setEditable(false);
		panelBase.add(txtSBMensual);
		txtSBMensual.setColumns(10);

		txtRetencion = new JTextField();
		txtRetencion.setHorizontalAlignment(SwingConstants.RIGHT);
		txtRetencion.setColumns(10);
		txtRetencion.setBounds(302, 92, 52, 25);
		txtRetencion.setEditable(false);
		txtRetencion.setText(String.valueOf(ServicioNominas.porcentRet));
		panelBase.add(txtRetencion);

		txtSNMensual = new JTextField();
		txtSNMensual.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSNMensual.setColumns(10);
		txtSNMensual.setBounds(384, 92, 104, 25);
		txtSNMensual.setEditable(false);
		panelBase.add(txtSNMensual);

		txtObservaciones = new JTextField();
		txtObservaciones.setBounds(10, 139, 478, 80);
		panelBase.add(txtObservaciones);
		txtObservaciones.setEditable(false);
		txtObservaciones.setColumns(10);

		txtSBAnual = new JTextField();
		txtSBAnual.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSBAnual.setEditable(false);
		txtSBAnual.setColumns(10);
		txtSBAnual.setBounds(10, 92, 115, 25);
		panelBase.add(txtSBAnual);
	}

	public void crearManejadores() {
		// Controlar evento pulsacion boton ver nominas
		btnVerNomina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.btnVerNominaPulsado(e);
			}
		});
		
		// Controlar evento seleccion codigo empleado en combo empleados
		cmbEmpleado.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				gui.cmbEmpleadoHaCambiado(e);
			}			
		});
		
		// Controlar evento seleccion mes en combo meses
		cmbMes.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				gui.cmbMesesHaCambiado(e);
			}
		});		
	}
}
