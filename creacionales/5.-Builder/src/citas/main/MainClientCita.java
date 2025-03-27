package creacionales.builder.citas.main;

import java.util.Date;

import creacionales.builder.citas.CitaBuilder;
import creacionales.builder.citas.FaltaInfoException;
import creacionales.builder.citas.Planificador;
import creacionales.builder.citas.beans.Cita;

// importacion estatica de los métodos de la clase Utiles
import static creacionales.builder.citas.main.Utiles.*;

public class MainClientCita {
	
	public static void main(String[] arguments) {
		Cita cita = null;

		print("Ejemplo del patron Builder\n");		
		print("Aplicacion para crear objetos Citas del sistema PIM.\n");
		
		print("Creando un planificador [clase Director]");
		Planificador planificador = new Planificador();

		System.out.println("Creando un CitaBuilder [clase Builder]\n");		
		CitaBuilder citaBuilder = new CitaBuilder();
		
		try {
			print("Creando una nueva Cita mediante un CitaBuilder");
			
			Date fechaCitaIni = crearFecha(2066, 9, 22, 12, 30);
			Date fechaCitaFin = null;
			String descripcion = "Presentacion Java 7";
			String localizacion = "Hotel New-Tech, Barcelona";
			
			// CONSTRUCCION DE LA CITA
			
			cita = planificador.crearCita(citaBuilder, fechaCitaIni, fechaCitaFin, 
						descripcion, localizacion, crearAsistentes(4));
			
			mostrarResultados(cita);
		} catch (FaltaInfoException exc) {
			printExcepciones(exc);
		}

	}			
}
