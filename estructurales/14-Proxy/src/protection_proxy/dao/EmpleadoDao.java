package estructurales.proxy.protection_proxy.dao;

import java.util.HashMap;
import java.util.Map;

import estructurales.proxy.protection_proxy.modelo.Empleado;

/*
 * Esta clase toma el rol de base de datos de empleados,
 * aunque por otro lado ofrece una interfaz para recuperar
 * un empleado a partir de su identificador o recuperar
 * todos los empleados existentes.
 */
public class EmpleadoDao {
			
	private static Map<Integer, Empleado> empleados;
	
	static {	
		empleados = new HashMap<Integer, Empleado>();
		empleados.put(1000, new Empleado(1000, "Xavier Garcia Fernandez", 25000f));
		empleados.put(1001, new Empleado(1001, "Raquel Mateo Gonzalez", 27000f));
		empleados.put(1002, new Empleado(1002, "Luis Ferrer Vizcaino", 23000f));		
		empleados.put(1003, new Empleado(1003, "Vanesa Garriga Teruel", 25000f));
	}
	
	/*
	 * Constructor privado para evitar que se creen instancias de esta clase
	 */
	private EmpleadoDao() {
		
	}
	
	public static Empleado getEmpleado(int codEmp) {
		return empleados.get(codEmp);
	}

	public static Map<Integer, Empleado> getEmpleados() {
		return empleados;
	}
	
}
