package estructurales.bridge.persistencia.client;

import java.io.Serializable;

public class Alumno implements Serializable, Comparable<Alumno> {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nombre;
	
	public Alumno(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	public int getId() { return id;	}
	public String getNombre() {	return nombre;	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Alumno otroAlumno) {
		int otroId = otroAlumno.getId();
		String otroNombre = otroAlumno.getNombre();
		
		if (id == otroId)
			return 0; // iguales
		 
		if (!nombre.equalsIgnoreCase(otroNombre))
			return nombre.compareTo(otroNombre); 
		else
			return 0; // iguales
	}
	
	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + "]";
	}
}
