package estructurales.proxy.protection_proxy.dao;

import java.util.HashMap;
import java.util.Set;

import estructurales.proxy.protection_proxy.modelo.Meses;
import estructurales.proxy.protection_proxy.modelo.Nomina;

/*
 * Esta clase toma el rol de base de datos de nominas,
 * además de proporcionar métodos para recuperar y 
 * almacenar nóminas.
 */
public class NominaDao {

	private static HashMap<Integer, HashMap<Meses, Nomina>> nominas;
	
	static {
		nominas = new HashMap<Integer, HashMap<Meses, Nomina>>();
		
		Set<Integer> codigosEmp = EmpleadoDao.getEmpleados().keySet();
		for (int codEmp : codigosEmp) {
			nominas.put(codEmp, inicializarNominasEmpleado());
		}
	}
	/*
	 * Constructor privado para evitar que se creen instancias de esta clase
	 */
	private NominaDao() {
		
	}
	
	public static Nomina getNomina(int codEmp, Meses mes) {
		return nominas.get(codEmp).get(mes);
	}
	
	public static void guardarNomina(int codEmp, Meses mes, Nomina nomina) {
		nominas.get(codEmp).put(mes, nomina);
		listarNominas();
	}
	
	private static void listarNominas() {
		System.out.println("----------------------------------------------");
		System.out.println("NOMINAS EXISTENTES ACTUALMENTE");
		System.out.println("----------------------------------------------");
		Set<Integer> codEmps = nominas.keySet();
		
		for (Integer codEmp : codEmps) {
			for (Meses mes : Meses.values()) {
				Nomina nomina = nominas.get(codEmp).get(mes);
				if (nomina != null)
					System.out.println(nomina);
			}
		}							
	}

	private static HashMap<Meses, Nomina> inicializarNominasEmpleado() {
		HashMap<Meses, Nomina> nominasEmpleado = 
			new	HashMap<Meses, Nomina>();
		
		for (Meses mes : Meses.values()) {
			nominasEmpleado.put(mes, null);
		}

		return nominasEmpleado;
	}
	
}
