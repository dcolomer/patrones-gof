package creacionales.builder.citas.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Cita {
	private Date fechaInicio;
	private Date fechaFin;
	private String descripcion;
	private ArrayList<Contacto> asistentes = new ArrayList<Contacto>();
	private String localizacion;
	public static final String EOL_STRING = System
			.getProperty("line.separator");

	public Date getFechaInicio() { return fechaInicio; }	
	public Date getFechaFin() {	return fechaFin; }
	public String getDescripcion() { return descripcion; }
	public ArrayList<Contacto> getAsistentes() { return asistentes;	}
	public String getLocalizacion() { return localizacion; }

	public String getFechaInicioBonita() {
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return sdf.format(fechaInicio); 
	}
	
	public String getFechaFinBonita() {
		if (fechaFin != null) {
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			return sdf.format(fechaFin);
		} else {
			return "N/A";	
		}
	}
	
	public void setDescripcion(String nuevaDescripcion) {
		descripcion = nuevaDescripcion;
	}

	public void setLocalizacion(String nuevaLocalizacion) {
		localizacion = nuevaLocalizacion;
	}

	public void setFechaInicio(Date nuevaFechaInicio) {
		fechaInicio = nuevaFechaInicio;
	}

	public void setFechaFin(Date nuevaFechaFin) {
		fechaFin = nuevaFechaFin;
	}

	public void setAsistentes(ArrayList<Contacto> nuevoAsistentes) {
		if (nuevoAsistentes != null) {
			asistentes = nuevoAsistentes;
		}
	}

	public void addAsistentes(Contacto asistente) {
		if (!asistentes.contains(asistente)) {
			asistentes.add(asistente);
		}
	}

	public void removeAsistente(Contacto asistente) {
		asistentes.remove(asistente);
	}

	@Override
	public String toString() {
		return " Descripcion: " + descripcion + EOL_STRING 
			+ " Fecha inicio: "	+ getFechaInicioBonita() 
			+ EOL_STRING + " Fecha fin: " + getFechaFinBonita() 
			+ EOL_STRING + " Localizacion: " + localizacion 
			+ EOL_STRING + " Asistentes: " + asistentes;
	}
}
