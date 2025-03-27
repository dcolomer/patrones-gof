package comportamiento.strategy.video;


public class BluRay extends SoporteFisico {
		
	private static final String CATEGORIA = "blu-rays";
	
	public BluRay(String nombre, String numSerie, Pelicula pelicula) {
		super(nombre, numSerie, pelicula);
	}
	
	@Override
	public void persist() {
		Registro.add(CATEGORIA, this);
	}

	public static BluRay get(String nombre) {
		return (BluRay) Registro.get(CATEGORIA, nombre);
	}

	@Override
	public String toString() {
		return "BluRay [nombre=" + nombre + ", numSerie=" + numSerie + ", pelicula=" + pelicula
				+ "]";
	}
	
}