package comportamiento.CoR.solicitud_compra;

public interface GestorPeticiones {
	
	/** Intentar aprobar una solicitud */
	public boolean autorizar(Peticion solicitud);

	/** Obtener una referencia al siguiente manejador */
	public GestorPeticiones getSiguienteGestor();
	
	/** Establecer quien será el siguiente manejador de la cadena.
	 * Notad que retorna el tipo GestorPeticiones, lo cual permite 
	 * que en el código cliente la creación de la cadena de gestores
	 * se realice en una sola línea.
	 */
	public GestorPeticiones setSiguienteGestor(GestorPeticiones gestor);
	
	/** Obtener el nombre (que no la referencia) del manejador actual */
	public String getNomGestor();

}