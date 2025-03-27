package comportamiento.strategy.video;

public abstract class SoporteFisico extends ObjetoDominio {

	protected String numSerie;
	protected Pelicula pelicula;
	
	public SoporteFisico(String nombre, String numSerie, Pelicula pelicula) {
		super(nombre);
		this.numSerie = numSerie;
		this.pelicula = pelicula;
	}
	
	public String getNumSerie() {
		return numSerie;
	}
	
	public Pelicula getPelicula() {
		return pelicula;
	}
		
	public abstract void persist();

}