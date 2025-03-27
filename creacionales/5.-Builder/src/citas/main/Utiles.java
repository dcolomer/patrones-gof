package creacionales.builder.citas.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import creacionales.builder.citas.FaltaInfoException;
import creacionales.builder.citas.beans.Cita;
import creacionales.builder.citas.beans.Contacto;

/**
 * Clase con metodos estaticos para dar soporte
 * a las dos clases cliente 
 */
class Utiles {
	static void mostrarResultados(Cita cita) {
		print("Cita creada correctamente.");
		print("Informacion sobre la cita:");
		print(cita);
		print("\n");				
	}

	static Date crearFecha(int anyo, int mes, int dia, 
			int hora, int minuto) 
	{
		Calendar fechaCreator = Calendar.getInstance();
		fechaCreator.set(anyo, mes, dia, hora, minuto);
		return fechaCreator.getTime();
	}

	static ArrayList<Contacto> crearAsistentes(int totalAsistentes) 
	{
		ArrayList<Contacto> grupo = new ArrayList<Contacto>();
		
		for (int i = 0; i < totalAsistentes; i++) {
			grupo.add(new Contacto(getNombre(), getApellidos(),
					"Empleado", "EmpresaX, S.A."));
		}
		return grupo;
	}

	static String getNombre() {
		String nombre = "";
		int num = new Random().nextInt(5);		
		switch (num) {
			case 0: nombre = "Luisa";
				break;
			case 1:	nombre = "Jaime";
				break;
			case 2: nombre = "Eduardo";
				break;
			case 3: nombre = "Jose Manuel";
				break;
			case 4:	nombre = "Martina";
				break;
			case 5:	nombre = "Raul";
				break;
		}
		return nombre;
	}
	
	static String getApellidos() {
		String apellidos = "";
		int num = new Random().nextInt(5);		
		switch (num) {
			case 0: apellidos = "Garcia Perez";
				break;
			case 1:	apellidos = "Sanchez Lopez";
				break;
			case 2: apellidos = "Casals Pi";
				break;
			case 3: apellidos = "Anglada Ferras";
				break;
			case 4:	apellidos = "Martinez Fernandez";
				break;
			case 5:	apellidos = "Rodriguez Hernan";
				break;
		}
		return apellidos;
	}

	static void printExcepciones(FaltaInfoException exc) {
		int codigoEstado = exc.getFaltaInfo();
		final String sufijo = " para completar esta cita.";

		print("Imposible crear cita: se necesita informacion adicional");
		if ((codigoEstado & FaltaInfoException.FECHA_INI_REQUERIDA) > 0) {
			print(" Se requiere una fecha de inicio"+sufijo);
		}
		if ((codigoEstado & FaltaInfoException.FECHA_FIN_REQUERIDA) > 0) {
			print(" Se requiere una fecha de finalizacion"+sufijo);
		}
		if ((codigoEstado & FaltaInfoException.DESCRIPCION_REQUERIDA) > 0) {
			print(" Se requiere una descripcion"+sufijo);
		}
		if ((codigoEstado & FaltaInfoException.ASISTENTES_REQUERIDOS) > 0) {
			print(" Se requiere al menos un asistente"+sufijo);
		}
		if ((codigoEstado & FaltaInfoException.LOCALIZACION_REQUERIDA) > 0) {
			print(" Se requiere una localizacion"+sufijo);
		}
		print("\n");
	}

	static void print(Object obj) {
		System.out.println(obj);
	}
}
