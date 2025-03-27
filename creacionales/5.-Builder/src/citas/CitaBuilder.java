package creacionales.builder.citas;

import java.util.Date;
import java.util.ArrayList;

import creacionales.builder.citas.beans.Cita;
import creacionales.builder.citas.beans.Contacto;

public class CitaBuilder {

	public static final int FECHA_INI_REQUERIDA = 1;
	public static final int FECHA_FIN_REQUERIDA = 2;
	public static final int DESCRIPCION_REQUERIDA = 4;
	public static final int ASISTENTES_REQUERIDOS = 8;
	public static final int LOCALIZACION_REQUERIDA = 16;

	protected Cita cita;
	protected int elementosRequeridos;

	public void buildCita() {
		cita = new Cita();
	}

	public void buildFechas(Date fechaInicio, Date fechaFin) {
		Date fechaActual = new Date();
		if ((fechaInicio != null) && (fechaInicio.after(fechaActual))) {
			cita.setFechaInicio(fechaInicio);
		}
		if ((fechaFin != null) && (fechaFin.after(fechaInicio))) {
			cita.setFechaFin(fechaFin);
		}
	}

	public void buildDescripcion(String nuevaDescripcion) {
		cita.setDescripcion(nuevaDescripcion);
	}

	public void buildAsistentes(ArrayList<Contacto> asistentes) {
		if ((asistentes != null) && (!asistentes.isEmpty())){
			cita.setAsistentes(asistentes);
		}
	}

	public void buildLocalizacion(String nuevaLocalizacion) {
		if (nuevaLocalizacion != null) {
			cita.setLocalizacion(nuevaLocalizacion);
		}
	}

	public Cita getCita() throws FaltaInfoException {
		elementosRequeridos = 0;

		if (cita.getFechaInicio() == null) {
			elementosRequeridos += FECHA_INI_REQUERIDA;
		}

		if (cita.getLocalizacion() == null) {
			elementosRequeridos += LOCALIZACION_REQUERIDA;
		}

		if (cita.getAsistentes().isEmpty()) {
			elementosRequeridos += ASISTENTES_REQUERIDOS;
		}

		if (elementosRequeridos > 0) {
			throw new FaltaInfoException(elementosRequeridos);
		}
		
		return cita;
	}

	public int getElementosRequeridos() { 
		return elementosRequeridos; 
	}
}
