package comportamiento.iterator.candidatos.externo;

import java.util.Iterator;

public class MainClient {

	public static void main(String[] args) {
		Candidatos candidatos = new Candidatos();
		Iterator<Candidato> empresaCandidatos =
				candidatos.getEmpresaCandidatos("SoftCut s.a");
		String candidatosSeleccionados = "Nombre --- Empresa --- Localidad"
				+ "\n" + "--------------------------------------";

		while (empresaCandidatos.hasNext()) {
			Candidato c = empresaCandidatos.next();
			candidatosSeleccionados = candidatosSeleccionados + "\n" + c.getNombre()
					+ " - " + c.getEmpresa() + " - "
					+ c.getLocalidad();			
		}
		System.out.println(candidatosSeleccionados);
	}

}
