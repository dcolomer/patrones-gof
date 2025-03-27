package estructurales.facade.subsistema;

public class Direccion {
	private String direccion;
	private String ciudad;
	private String provincia;
	private final String FICHERO_USUARIO = "c://temp//facade//Direccion.txt";
	private final boolean APPEND = true;
	private final boolean CARACTER_NUEVA_LINEA = true;

	public Direccion(String dir, String ciu, String prov) {
		direccion = dir;
		ciudad = ciu;
		provincia = prov;
	}

	public String getDireccion() { return direccion; }
	public String getCiudad() { return ciudad; }
	public String getProvincia() { return provincia; }
	
	/* 
	 * Hacemos una comprobación simple para no complicar 
	 * el ejemplo. La provincia tiene que tener dos o más 
	 * caracteres para ser valida.
	 */
	public boolean esValido() {		
		if (getProvincia().trim().length() < 2)
			return false;
		
		return true;
	}

	/*
	 * Guardar los datos de la direccion en un fichero de texto
	 */
	public boolean guardarDatos() {
		UtilidadesFichero fUtil = new UtilidadesFichero();
		String dataLine = getDireccion() + "," + getCiudad() + "," + getProvincia();
		return fUtil.writeToFile(FICHERO_USUARIO, 
				dataLine, APPEND, CARACTER_NUEVA_LINEA);
	}

}
