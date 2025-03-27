package comportamiento.iterator.candidatos.externo;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class EmpresaCandidatos implements Iterator<Candidato> {
	
	private String empresa;

	private Candidato siguienteCandidato;
	private Enumeration<Candidato> enumCandidatos;

	public EmpresaCandidatos(Candidatos candidatos, String empresa) {
		this.empresa = empresa;
		this.enumCandidatos = candidatos.getCandidatos();
	}

	@Override
	public boolean hasNext() {
		boolean encontrado = false;
		while (enumCandidatos.hasMoreElements()) {
			Candidato tempObj = enumCandidatos.nextElement();
			if (tempObj.getEmpresa().equals(empresa)) {
				encontrado = true;
				siguienteCandidato = tempObj;
				break;
			}
		}
		if (!encontrado) {
			siguienteCandidato = null;
		}
		return encontrado;
	}

	@Override
	public Candidato next() {
		if (siguienteCandidato == null) {
			throw new NoSuchElementException();
		} else {
			return siguienteCandidato;
		}
	}

	@Override
	public void remove() {	}

}
