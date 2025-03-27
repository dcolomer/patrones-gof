package creacionales.builder.citas.main;

import java.util.Date;

import creacionales.builder.citas.CitaBuilder;
import creacionales.builder.citas.EncuentroBuilder;
import creacionales.builder.citas.FaltaInfoException;
import creacionales.builder.citas.Planificador;
import creacionales.builder.citas.beans.Cita;

//importacion estatica de los métodos de la clase Utiles
import static creacionales.builder.citas.main.Utiles.*;

public class MainClientEncuentro {
	
	public static void main(String[] arguments) {
		Cita cita = null;

		print("Ejemplo del patron Builder\n");		
		print("Aplicacion para crear objetos Citas del sistema PIM.\n");
		
		print("Creando un planificador [clase Director]");
		Planificador planificador = new Planificador();		

		print("Creando un EncuentroBuilder");
		CitaBuilder encuentroBuilder = new EncuentroBuilder();
		
		try {
			print("Creando una nueva cita mediante un EncuentroBuilder");
			print("(notad que se lanzara la excepcion porque un encuentroBuilder");
			print("(requiere una fecha de finalizacion");
			
			Date fechaCitaIni = crearFecha(2066, 9, 22, 12, 30);
			Date fechaCitaFin = null; // POR ESTO SE LANZA LA EXCEPCION
			String descripcion = "Presentacion Oracle 28g";
			String localizacion =  "Hotel Castellana, Madrid";
			
			cita = planificador.crearCita(encuentroBuilder, fechaCitaIni, fechaCitaFin, 
					descripcion, localizacion, crearAsistentes(4));
			
			mostrarResultados(cita);
		} catch (FaltaInfoException exc) {
			printExcepciones(exc);
		}

		print("Creando una nueva cita mediante un EncuentroBuilder");
		print("(esta vez se proporciona una fecha de finalizacion)");
		try {			
			Date fechaCitaIni = crearFecha(2077, 4, 22, 12, 30);
			Date fechaCitaFin = crearFecha(2077, 4, 23, 18, 00);
			String descripcion = "Feria dispositivos moviles";
			String localizacion = "Salon Central, Girona";
			
			cita = planificador.crearCita(encuentroBuilder, fechaCitaIni, fechaCitaFin, 
					descripcion, localizacion, crearAsistentes(2));
			
			mostrarResultados(cita);
		} catch (FaltaInfoException exc) {
			printExcepciones(exc);
		}
	}	
}
