package comportamiento.CoR.solicitud_compra;

public abstract class AbstractGestorPeticiones implements GestorPeticiones {
	
	private GestorPeticiones siguienteGestor;
	private String nomGestor;

	public AbstractGestorPeticiones(String nombre) {
		nomGestor = nombre;
	}	

	@Override
	public abstract boolean autorizar(Peticion solicitud);

	@Override
	public GestorPeticiones getSiguienteGestor() {
		return siguienteGestor;
	}

	/*
	 * Al retornar GestorPeticiones facilitamos la concatenacion de
	 * los Gestores
	 */
	@Override
	public GestorPeticiones setSiguienteGestor(GestorPeticiones gestor) {
		siguienteGestor = gestor;
		return this;
	}
	
	@Override
	public String getNomGestor() {
		return nomGestor;
	}
}
