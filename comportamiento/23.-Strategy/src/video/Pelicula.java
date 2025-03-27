package comportamiento.strategy.video;

public class Pelicula extends ObjetoDominio {
	
	private static final String CATEGORIA = "peliculas";

	private Precio precio;

	// Evitar instanciacion exterior
	private Pelicula(String nombre) {
		super(nombre);
	}

	/*
	 * Metodos de factoria
	 */
	public static Pelicula crearEstreno(String nombre) {
		Pelicula pel = new Pelicula(nombre);
		pel.setEstreno();
		return pel;
	}

	public static Pelicula crearNormal(String nombre) {
		Pelicula pel = new Pelicula(nombre);
		pel.setNormal();
		return pel;
	}

	public static Pelicula crearDesfasada(String nombre) {
		Pelicula pel = new Pelicula(nombre);
		pel.setAntigua();
		return pel;
	}

	// *******************************************************
	
	double calcularImportePrestamo(int diasPrestamo) {
		return precio.calcularImportePrestamo(diasPrestamo);
	}

	int getPuntos(int diasPrestamo) {
		return precio.getPuntos(diasPrestamo);
	}

	public void setNormal() {
		precio = Precio.getNormal();
	}

	public void setEstreno() {
		precio = Precio.getEstreno();
	}

	public void setAntigua() {
		precio = Precio.getAntigua();
	}

	public void persist() {
		Registro.add(CATEGORIA, this);
	}

	public static Pelicula get(String nombre) {
		return (Pelicula) Registro.get(CATEGORIA, nombre);
	}

	@Override
	public String toString() {
		return "Pelicula [nombre=" + nombre + ", precio=" + precio + "]";
	}
}
