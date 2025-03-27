package estructurales.proxy.protection_proxy.servicios;

import estructurales.proxy.protection_proxy.modelo.Meses;
import estructurales.proxy.protection_proxy.modelo.Nomina;

/** Subject */
public interface ServicioNominas {
	
	public static int porcentRet = 20;
	public static int horasMensualesOficiales = 40*4;
	
    public Nomina getNomina(int codEmp, Meses mes, int horasTrabajadas); 
}