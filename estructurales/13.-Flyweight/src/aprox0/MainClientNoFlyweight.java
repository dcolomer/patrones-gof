package estructurales.flyweight.aprox0;

import java.util.ArrayList;
import java.util.List;


public class MainClientNoFlyweight {
	private static int EMPRESA = 0, NOMBRE = 1, CARGO = 2, REGION = 3;
	private static int DIRECCION = 4, CIUDAD = 5, PROVINCIA = 6, CODPOSTAL = 7;

	public static void main(String[] args) {
		long tiempoIni = System.nanoTime();	
		List<String> empleados = inicializar();
		
		for (int i = 0; i < empleados.size(); i++) {
			String empleado = empleados.get(i);			
			String partesEmpleado[] = empleado.split(",");
			
			String empresa = partesEmpleado[EMPRESA].trim();
			String nombre = partesEmpleado[NOMBRE].trim();
			String cargo = partesEmpleado[CARGO].trim();			
			String region = partesEmpleado[REGION].trim();
			String direccion = partesEmpleado[DIRECCION].trim();
			String ciudad = partesEmpleado[CIUDAD].trim();
			String provincia = partesEmpleado[PROVINCIA].trim();
			String codPostal = partesEmpleado[CODPOSTAL].trim();
						
			// Construimos la tarjeta de visita			
			TarjetaVisitaNoFlyweight tarjetaVisita = 
				new TarjetaVisitaNoFlyweight(empresa, nombre, cargo, 
						region, direccion, ciudad, provincia, codPostal);
			
			tarjetaVisita.print();			
		}
		
		long tiempoFin = System.nanoTime();
		System.out.println("Tiempo ejecucion: " + (tiempoFin - tiempoIni));
	}

	/*
	 * Por simplicidad, proporcionamos los valores directamente
	 * en lugar de obtenerlos de una base de datos o similar.
	 * El bucle tiene la intención de trabajar con más datos,
	 * para nuestro propósito no importa que se repitan.
	 */
	private static List<String> inicializar() {
		List<String> emp = new ArrayList<String>();
		for (int i=0; i<1000; i++) {
			emp.add("LuxMetal, Vicente Guash, Jefe de Ventas, Norte, Calle Cantabria 456, Bilbao, Vizcaya, 10000");
			emp.add("LuxMetal, Antonio Martinez, Jefe Compras, Sur, Calle de las sierpes 111, Alcala de Guadaira, Sevilla, 20000");
			emp.add("LuxMetal, Loreto Bermudez, Jefa RRHH, Norte, Calle Cantabria 456, Bilbao, Vizcaya, 10000");
			emp.add("LuxMetal, Maria Caraz, Controller, Este, Avenida del Mediterraneo s/n, Oropesa, Castellon, 30000");
			emp.add("LuxMetal, Sergio Fernandez, Jefe Informatica de sistemas, Este, Avenida del Mediterraneo s/n, Oropesa, Castellon, 30000");
			emp.add("LuxMetal, Luisa Garcia, Jefa de Contabilidad, Este, Avenida del Mediterraneo s/n, Oropesa, Castellon, 30000");
			emp.add("LuxMetal, Ramon Teruel, Jefe Calidad, Oeste, Carretera de Portugal, Jerez de los Caballeros, Badajoz, 40000");
			emp.add("LuxMetal, Claudia Gutierrez, Jefa Marketing, Oeste, Carretera de Portugal, Jerez de los Caballeros, Badajoz, 40000");
			emp.add("LuxMetal, Daniel Lopez, Secretario Gerencia, Oeste, Carretera de Portugal, Jerez de los Caballeros, Badajoz, 40000");
			emp.add("LuxMetal, Carmela Pons, Jefe Oficina Tecnica, Sur, Calle de las sierpes 111, Alcala de Guadaira, Sevilla, 20000");
			emp.add("LuxMetal, Angel Martinez, Director General, Sur, Calle de las sierpes 111, Alcala de Guadaira, Sevilla, 20000");
			emp.add("LuxMetal, Beatriz Sanchez, Jefa I+D+I, Sur, Calle de las sierpes 111, Alcala de Guadaira, Sevilla, 20000");
		}
		return emp;
	}
	
}
