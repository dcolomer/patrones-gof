package comportamiento.strategy.video;

public class Alquiler extends ObjetoDominio {

	private static final String CATEGORIA = "alquileres";
	
	private SoporteFisico soporteFisico;
	private int diasPrestamo;
		
	public Alquiler(String nombre, SoporteFisico soporteFisico, int diasPrestamo) {
		super(nombre);
		this.soporteFisico = soporteFisico;
		this.diasPrestamo = diasPrestamo;
	}

	public int getPuntos() {
        return soporteFisico.getPelicula().getPuntos(diasPrestamo);
    }
	
	public double calcularImportePrestamo() {
        return soporteFisico.getPelicula().calcularImportePrestamo(diasPrestamo);
    }
	
	public int getDiasPrestamo() {
		return diasPrestamo;
	}

	public SoporteFisico getSoporteFisico() {
		return soporteFisico;
	}
	
	public void persist() {
		Registro.add(CATEGORIA, this);
	}

	public static Alquiler get(String nombre) {
		return (Alquiler) Registro.get(CATEGORIA, nombre);
	}

	@Override
	public String toString() {
		return "Alquiler [nombre=" + nombre + ", diasPrestamo=" + diasPrestamo + ", soporteFisico="
				+ soporteFisico + "]";
	}
	
}
