package comportamiento.iterator.candidatos.interno;

public class MainClient {

	public static void main(String[] args) {
		Candidatos candidatos = new Candidatos();
		String candidatosSeleccionados = "Nombre --- Empresa --- Localidad"
				+ "\n" + "--------------------------------------";

		while (candidatos.hasNext()) {
			Candidato c = (Candidato) candidatos.next();
			candidatosSeleccionados = candidatosSeleccionados + "\n" + c.getNombre()
					+ " - " + c.getEmpresa() + " - "
					+ c.getLocalidad();			
		}
		System.out.println(candidatosSeleccionados);
	}

}
