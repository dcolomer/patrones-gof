package estructurales.proxy.protection_proxy.servicios;

import java.util.HashMap;
import java.util.Map;

import estructurales.proxy.protection_proxy.modelo.Meses;
import estructurales.proxy.protection_proxy.modelo.Nomina;

public class ProxyServicioNominas implements ServicioNominas {

    private ServicioNominas servicio;
	private boolean validado;
    private static Map<String, String> credenciales;

    /*
     * Creamos algunas credenciales de usurios del departamento 
     * de nominas y las guarfamos en un Map.
     * Se tendrían que obtener de una BD o de un LDAP.
     */
    static {		
    	credenciales = new HashMap<String, String>();		
		credenciales.put("admin", "12345");
		credenciales.put("test", "test");
	}
    
    /*
     * Constructor
     */
    public ProxyServicioNominas(String presuntoLogin, 
    		String presuntoPassword) throws ValidacionException 
    {    	
    	System.out.println("Construyendo un objeto ProxyServicioNominas...");
    	System.out.println("Validando usuario...");
    	
    	comprobarCredenciales(presuntoLogin, presuntoPassword);    	    	
    }
    
    /*
     * Constrastar las credenciales suministradas con las 
     * almacenadas en el HashMap.
     */
    private void comprobarCredenciales(String presuntoLogin,
			String presuntoPassword) throws ValidacionException 
    {	    	
    	String passwordRecuperado = 
        		credenciales.get(presuntoLogin);
        	
    	if (passwordRecuperado != null) {
    		if (passwordRecuperado.equals(presuntoPassword)) {
    			validado = true;
    			System.out.println("...credenciales correctas.");
    		} else {
    			System.out.println("...credenciales incorrectas.");
    			throw new ValidacionException("El password especificado no es correcto.");
    		}
    	} else {
    		System.out.println("...credenciales incorrectas.");
    		throw new ValidacionException("El login especificado no existe.");
    	}
	}

	@Override
    public Nomina getNomina(int codEmp, Meses mes, int horasTrabajadas) {
    	if (validado) {
    		if (servicio == null) {
    			servicio = new ServicioNominasImpl();
    		}
    		return servicio.getNomina(codEmp, mes, horasTrabajadas);
    	} else {
    		throw new RuntimeException("Acceso denegado. El usuario no se ha validado.");
    	}
    }

}
