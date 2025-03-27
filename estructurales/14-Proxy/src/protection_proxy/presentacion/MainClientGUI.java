package estructurales.proxy.protection_proxy.presentacion;

import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.*;

import java.util.*;

import javax.swing.JFrame;

import estructurales.proxy.protection_proxy.dao.EmpleadoDao;
import estructurales.proxy.protection_proxy.modelo.Empleado;
import estructurales.proxy.protection_proxy.modelo.Meses;
import estructurales.proxy.protection_proxy.modelo.Nomina;
import estructurales.proxy.protection_proxy.servicios.ServicioNominas;

public class MainClientGUI {
	
	private ConfiguradorGrafico cg; // JFrame que soporta toda la GUI

	private int codEmpSeleccionado;
	private Meses mesSeleccionado;
	private ServicioNominas servicioNominas;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				MainClientGUI frame = new MainClientGUI();
				frame.cg.setVisible(true);
			}
		});
	}
	
	// Constructor	
	public MainClientGUI() {	
		Login login = validarUsuario();
		adquirirServicioNominas(login);		
		if (servicioAdquirido()) {
			inicializarGUI();			
		} else {
			throw new RuntimeException("No se ha podido adquirir el servicio protegido.");			
		}
	}
	
	/*
	 * Muestra una pantalla de login para que el usuario
	 * se autentifique.
	 */
	private Login validarUsuario() {
		JFrame ventanaPadre = null;
		Login login = new Login(ventanaPadre , "Acceso al sistema",
				ModalityType.APPLICATION_MODAL); 
		login.setVisible(true);
		return login; 					
	}

	/* 
	 * Si la utenticacion ha sido correcta se obtiene el 
	 * servicio de nominas que proporciona el proxy.
	 */
	private void adquirirServicioNominas(Login login) {		
		servicioNominas = login.getServicio();		
	}

	private boolean servicioAdquirido() {		
		return servicioNominas != null;
	}
	
	private void inicializarGUI() {		
		dibujarGUI();				
		limpiarYmostrarDatosEmpleado();	
	}
	
	/*
	 * Con el propósito de no cargar a la clase de código rutinario,
	 * delegamos en otra clase la confeccion de la interfaz grafica
	 */
	private void dibujarGUI() {
		cg = new ConfiguradorGrafico(this);	
		cg.crearGUI();
	}
	
	private void limpiarYmostrarDatosEmpleado() {
		limpiarFormulario();
		mostrarDatosEmpleado();
	}
		
	private void limpiarFormulario() {
		cg.txtNomEmpleado.setText("");
		cg.txtHorasTrabajadas.setText(getStringHorasMensualesOficiales());
		cg.txtSBAnual.setText("");
		cg.txtSBMensual.setText("");
		cg.txtSNMensual.setText("");
		cg.txtObservaciones.setText("");
	}
	
	/*
	 * En funcion del empleado seleccionado actualmente en el
	 * combo, mostrar el nombre y el sueldo bruto del empleado.
	 */
	private void mostrarDatosEmpleado() {		
		int codEmp = getIntegerCodEmpFromCombo();
		Empleado empleado = EmpleadoDao.getEmpleado(codEmp);
		cg.txtNomEmpleado.setText(empleado.getNombre());		
		cg.txtSBAnual.setText(getStringSueldoBrutoEmpleado(empleado));
		cg.txtHorasTrabajadas.requestFocusInWindow();
	}
	
	private int getIntegerCodEmpFromCombo() {
		String elementoSeleccionado = 
			cg.cmbEmpleado.getSelectedItem().toString();		
		return Integer.valueOf(elementoSeleccionado);
	}
	
	private String getStringSueldoBrutoEmpleado(Empleado empleado) {		
		return String.valueOf(empleado.getSueldoBruto());
	}
	
	String getStringHorasMensualesOficiales() {		
		return String.valueOf(ServicioNominas.horasMensualesOficiales);
	}	
	
	private int getIntegerHorasTrabajadas() {		
		return Integer.valueOf(cg.txtHorasTrabajadas.getText().toString());
	}
	
	private String getStringBrutoMensualNomina(Nomina nomina) {		
		return String.valueOf(nomina.getBruto());
	}

	private String getStringNetoMensualNomina(Nomina nomina) {		
		return String.valueOf(nomina.getNeto());
	}
	
	/*
	 * Cuando el usuario selecciona un nuevo empleado:
	 * 1) Lo establecemos como el empleado actual para 
	 *    el calculo de la nomina.
	 * 2) Mostramos sus datos en el resto de campos 
	 *    del formulario.
	 */
	void cmbEmpleadoHaCambiado(ItemEvent e) {
		codEmpSeleccionado = Integer.valueOf(e.getItem().toString());
		mostrarDatosEmpleado();		
	}
	
	/*
	 * Cuando el usuario selecciona un nuevo mes lo
	 * establecemos com el mes actual para el calculo
	 * de la nomina.
	 */
	void cmbMesesHaCambiado(ItemEvent e) {
		mesSeleccionado = 
			Meses.valueOf(e.getItem().toString());		
	}
	
	/*
	 * Rellena el combo de empleados.
	 * 1) Obtiene los empleados de EmpleadoDao
	 * 2) Los ordena.
	 * 3) Devuelve un String[] que contiene el codigo 
	 *    de cada empleado.
	 */
	String[] getEmpleados() {
		final String[] EMPTY_EMPLEADOS = new String[0];

		java.util.List<Empleado> empleados = new ArrayList<Empleado>(
				EmpleadoDao.getEmpleados().values());

		Collections.sort(empleados);

		if (!empleados.isEmpty()) {
			Collection<String> codEmpleados = new ArrayList<String>(
					empleados.size());
			for (Empleado empleado : empleados) {
				codEmpleados.add(String.valueOf(empleado.getCodigo()));
			}
			return codEmpleados.toArray(EMPTY_EMPLEADOS);
		}

		return EMPTY_EMPLEADOS;
	}
	
	void btnVerNominaPulsado(ActionEvent e) {
		if (codEmpSeleccionado == 0) {
			codEmpSeleccionado = Integer.valueOf(cg.cmbEmpleadoModel
					.getSelectedItem().toString());
		}
		if (mesSeleccionado == null) {
			mesSeleccionado = Meses.valueOf(cg.cmbMes.getSelectedItem()
					.toString());
		}
		getNomina();
	}
	
	/*
	 * Obtiene la nomina para el cliente/mes seleccionado
	 */
	private void getNomina() {
		if (servicioAdquirido()) {
			Nomina nomina = servicioNominas.getNomina(codEmpSeleccionado,
					mesSeleccionado, getIntegerHorasTrabajadas());

			cg.txtSBMensual.setText(getStringBrutoMensualNomina(nomina));						
			cg.txtSNMensual.setText(getStringNetoMensualNomina(nomina));			
			cg.txtObservaciones.setText(nomina.getObservaciones());
		} else {
			throw new RuntimeException("No se ha podido adquirir el servicio protegido.");
		}
	}
}