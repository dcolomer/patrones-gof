package comportamiento.strategy.video;

public class ObjetoDominio {
	
	protected String nombre = "sin nombre";
	
	public ObjetoDominio(String nombre)	{
		this.nombre = nombre;
	}

	public String getNombre()	{
		return nombre;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
}
