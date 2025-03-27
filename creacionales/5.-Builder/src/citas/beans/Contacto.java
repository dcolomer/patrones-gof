package creacionales.builder.citas.beans;

public class Contacto {

	private static final long serialVersionUID = 1L;
	public static final String ESPACIO = " ";
	
	private String nombre;
	private String apellidos;
	private String categoria;
	private String organizacion;

	public Contacto(String nuevoNombre, String nuevoApellidos,
			String nuevaCategoria, String nuevaOrganizacion) 
	{
		nombre = nuevoNombre;
		apellidos = nuevoApellidos;
		categoria = nuevaCategoria;
		organizacion = nuevaOrganizacion;
	}

	public String getNombre() { return nombre; }
	public String getApellidos() { return apellidos; }
	public String getCategoria() { return categoria; }
	public String getOrganizacion() { return organizacion; }

	public void setNombre(String nuevoNombre) {
		nombre = nuevoNombre;
	}

	public void setApellidos(String nuevoApellidos) {
		apellidos = nuevoApellidos;
	}

	public void setCategoria(String nuevaCategoria) {
		categoria = nuevaCategoria;
	}

	public void setOrganizacion(String nuevaOrganicacion) {
		organizacion = nuevaOrganicacion;
	}

	@Override
	public String toString() {
		return nombre + ESPACIO + apellidos + 
		ESPACIO + " - Categoria: " + categoria + 
		ESPACIO + " - Organizacion: " + organizacion + "\n";
	}
}
