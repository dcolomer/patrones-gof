package estructurales.facade.subsistema;

public class Usuario {
	private String nombre;
	private String apellidos;
	private final String FICHERO_USUARIO = "c://temp//facade//Usuario.txt";
	private final boolean APPEND = true;
	private final boolean CARACTER_NUEVA_LINEA = true;
	
	public Usuario(String nom, String apel) {
		nombre = nom;
		apellidos = apel;
	}

	public String getNombre() { return nombre; }
	public String getApellidos() { return apellidos; }
	
	/* 
	 * Hacemos una comprobación simple para no complicar 
	 * el ejemplo. El apellido tiene que tener dos o más 
	 * caracteres para ser valido.
	 */
	public boolean esValido() {
		if (getApellidos().trim().length() < 2)
			return false;
		
		return true;
	}

	/*
	 * Guardar los datos del usuario en un fichero de texto
	 */
	public boolean guardarDatos() {
		UtilidadesFichero fUtil = new UtilidadesFichero();
		String lineaDatos = getApellidos() + ", " + getNombre();
		return fUtil.writeToFile(FICHERO_USUARIO, 
				lineaDatos, APPEND, CARACTER_NUEVA_LINEA);
	}

}