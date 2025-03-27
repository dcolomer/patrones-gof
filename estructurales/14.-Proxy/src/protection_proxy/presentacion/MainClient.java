package estructurales.proxy.protection_proxy.presentacion;

import estructurales.proxy.protection_proxy.modelo.Meses;
import estructurales.proxy.protection_proxy.modelo.Nomina;
import estructurales.proxy.protection_proxy.servicios.ProxyServicioNominas;
import estructurales.proxy.protection_proxy.servicios.ServicioNominas;
import estructurales.proxy.protection_proxy.servicios.ValidacionException;

public class MainClient {

	public static void main(String[] args) {				
		/*
		 * La siguiente línea no compila porque 
		 * ServicioNominasImpl no es una clase pública, sólo 
		 * es accesible indirectamente a traves del proxy.
		 */
		//ServicioNominas servicio = new ServicioNominasImpl();
		
		/*
		 * Lo primero que hay que hacer es obtener una instancia
		 * del proxy. Su constructor requiere de unas credenciales
		 * y puede lanzar una ValidacionException.
		 */				
		ServicioNominas servicio;
		try {
			servicio = new ProxyServicioNominas("admin", "12345");
			Nomina nomina = servicio.getNomina(1000, Meses.Abril, 160);
			System.out.println(nomina);
			
			nomina = servicio.getNomina(1001, Meses.Abril, 115);
			System.out.println(nomina);
			
			nomina = servicio.getNomina(1002, Meses.Abril, 195);
			System.out.println(nomina);
		} catch (ValidacionException e) {			
			e.printStackTrace();
		}				
	}
}
