package estructurales.flyweight.aprox1.client;

import java.util.ArrayList;
import java.util.List;

import estructurales.flyweight.aprox1.Flyweight;
import estructurales.flyweight.aprox1.FlyweightFactory;
import estructurales.flyweight.aprox1.Region;
import estructurales.flyweight.aprox1.TarjetaVisita;

public class MainClient {
	private static int NOMBRE = 0, CARGO = 1, REGION = 2;
	private static Region region;	
	
	public static void main(String[] args) throws Exception {
		long tiempoIni = System.nanoTime();	
		List<String> empleados = inicializar();
		FlyweightFactory factory = FlyweightFactory.getInstance();

		for (int i = 0; i < empleados.size(); i++) {
			String empleado = empleados.get(i);
			String partesEmpleado[] = empleado.split(",");
			
			String nombreEmpleado = partesEmpleado[NOMBRE].trim();
			String cargoEmpleado = partesEmpleado[CARGO].trim();
			
			String regionEmpleado = partesEmpleado[REGION].trim();
			region = Region.valueOf(regionEmpleado);
			
			Flyweight flyweight = factory.getFlyweight(region);
			
			// Asociamos el flyweight con la tarjeta de visita			
			TarjetaVisita tarjetaVisita = 
				new TarjetaVisita(nombreEmpleado, cargoEmpleado, flyweight);
			
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
			emp.add("Vicente Guash, Jefe de Ventas, Norte");
			emp.add("Antonio Martinez, Jefe Compras, Sur");
			emp.add("Loreto Bermudez, Jefa RRHH, Norte");
			emp.add("Maria Caraz, Controller, Este");
			emp.add("Sergio Fernandez, Jefe Informatica de sistemas, Este");
			emp.add("Luisa Garcia, Jefa de Contabilidad, Este");
			emp.add("Ramon Teruel, Jefe Calidad, Oeste");
			emp.add("Claudia Gutierrez, Jefa Marketing, Oeste");
			emp.add("Daniel Lopez, Secretario Gerencia, Oeste");
			emp.add("Carmela Pons, Jefe Oficina Tecnica, Sur");
			emp.add("Angel Martinez, Director General, Sur");
			emp.add("Beatriz Sanchez, Jefa I+D+I, Sur");
		}
		return emp;
	}
}
