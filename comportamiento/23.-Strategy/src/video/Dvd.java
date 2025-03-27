package comportamiento.strategy.video;


public class Dvd extends SoporteFisico {
	
	private static final String CATEGORIA = "dvds";
	
	public Dvd(String nombre, String numSerie, Pelicula pelicula) {
		super(nombre, numSerie, pelicula);
	}
	
	@Override
	public void persist() {
		Registro.add(CATEGORIA, this);
	}

	public static Dvd get(String nombre) {
		return (Dvd) Registro.get(CATEGORIA, nombre);
	}

	@Override
	public String toString() {
		return "Dvd [nombre=" + nombre + ", numSerie=" + numSerie + ", pelicula=" + pelicula
				+ "]";
	}
	
}