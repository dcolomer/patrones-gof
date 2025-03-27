package comportamiento.iterator.candidatos.interno;

public class Candidato {
	private String nombre;
	private String empresa;
	private String localidad;

	public Candidato(String nombre, String empresa, String localidad) {
		this.nombre = nombre;
		this.empresa = empresa;
		this.localidad = localidad;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEmpresa() {
		return empresa;
	}
	
	public String getLocalidad() {
		return localidad;
	}
}
