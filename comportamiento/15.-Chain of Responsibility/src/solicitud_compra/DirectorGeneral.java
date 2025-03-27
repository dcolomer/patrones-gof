package comportamiento.CoR.solicitud_compra;

public class DirectorGeneral extends AbstractGestorPeticiones {

	private static double LIMITE = 1000000;

	public DirectorGeneral(String nombre) {
		super(nombre);
	}

	@Override
	public boolean autorizar(Peticion solicitud) {
		double importe = solicitud.getImporte();

		if (importe <= LIMITE) {
			String msgEstado = "Director General '" + getNomGestor()
					+ "' ha autorizado la solicitud de compra.";
			solicitud.setMsgEstado(msgEstado);
			return true;
		} else {
			// Reenviar la solicitud al siguiente gestor de peticiones
			GestorPeticiones siguienteGestor = getSiguienteGestor();
			if (siguienteGestor != null) { // no entrará en el if, ya que es el último de la cadena
				return siguienteGestor.autorizar(solicitud);
			} else {
				String msgEstado = "***Peticion no aprobada.";
				solicitud.setMsgEstado(msgEstado);
				return false;
			}
		}
	}

}