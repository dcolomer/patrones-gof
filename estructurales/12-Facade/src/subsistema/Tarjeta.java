package estructurales.facade.subsistema;

public class Tarjeta {
	private TarjetaTipo tipo;
	private String numero;
	private String fechaCaducidad;
	private final String FICHERO_TARJETA = "c://temp//facade//Tarjeta.txt";
	private final boolean APPEND = true;
	private final boolean CARACTER_NUEVA_LINEA = true;
	
	public Tarjeta(TarjetaTipo tip, String num, String fechaCad) {
		tipo = tip;
		numero = num;
		fechaCaducidad = fechaCad;
	}

	public TarjetaTipo getTipo() { return tipo; }
	public String getNumero() {	return numero; }
	public String getFechaCaducidad() { return fechaCaducidad; }
	
	/* 
	 * Hacemos una comprobación simple para no complicar 
	 * el ejemplo. Cada tipo de tajeta debe tener una
	 * longitud de caracteres específica.
	 */
	public boolean esValido() {		
		if (getTipo().equals(TarjetaTipo.VISA)) {
			return (getNumero().trim().length() == TarjetaTipo.LENGHT_VISA);
		}
		if (getTipo().equals(TarjetaTipo.DISCOVER)) {
			return (getNumero().trim().length() == TarjetaTipo.LENGHT_DISCOVER);
		}
		if (getTipo().equals(TarjetaTipo.MASTER)) {
			return (getNumero().trim().length() == TarjetaTipo.LENGHT_MASTER);
		}

		return false;
	}

	public boolean guardarDatos() {
		UtilidadesFichero fUtil = new UtilidadesFichero();
		String dataLine = getTipo() + "," + getNumero() + ","
				+ getFechaCaducidad();
		return fUtil.writeToFile(FICHERO_TARJETA, 
				dataLine, APPEND, CARACTER_NUEVA_LINEA);
	}

}