package comportamiento.CoR.solicitud_compra;

public class DirectorProvincial extends AbstractGestorPeticiones {
	
	private static double LIMITE = 25000;

	public DirectorProvincial(String nombre) {
		super(nombre);
	}

	@Override
	public boolean autorizar(Peticion solicitud) {
		double importe = solicitud.getImporte();

		if (importe <= LIMITE) {
			String msgEstado = "Director Provincial '" + getNomGestor()
					+ "' ha autorizado la solicitud de compra.";
			solicitud.setMsgEstado(msgEstado);
			return true;
		} else {
			// Reenviar la solicitud al siguiente gestor de peticiones
			GestorPeticiones siguienteGestor = getSiguienteGestor();
			if (siguienteGestor != null) {
				return siguienteGestor.autorizar(solicitud);
			} else {
				return false;
			}
		}
	}

}
