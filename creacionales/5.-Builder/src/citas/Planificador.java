package creacionales.builder.citas;

import java.util.Date;
import java.util.ArrayList;

import creacionales.builder.citas.beans.Cita;
import creacionales.builder.citas.beans.Contacto;

public class Planificador {
	
	public Cita crearCita(CitaBuilder builder, 	Date fechaInicio, Date fechaFin, 
			String descripcion,	String localizacion, ArrayList<Contacto> asistentes)
				throws FaltaInfoException {
						
		builder.buildCita();
		builder.buildFechas(fechaInicio, fechaFin);
		builder.buildDescripcion(descripcion);
		builder.buildAsistentes(asistentes);
		builder.buildLocalizacion(localizacion);
		
		return builder.getCita();
	}
}
