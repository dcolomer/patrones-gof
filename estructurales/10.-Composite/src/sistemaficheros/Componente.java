package estructurales.composite.sistemaficheros;

/** Component **/
public abstract class Componente {

	// Nombre del directorio o del archivo
	protected String nombre;

	/*
	 * Las subclases proporcionaran un nombre para la clase base
	 */
	public Componente(String nombre) {
		this.nombre = nombre;
	}

	/*
	 * Operacion abstracta que cada subclase de implementar
	 */
	abstract public void mostrar(int profundidad);
	
	/*
	 * Implementación por defecto. Solo la soporta la clase Directorio
	 */
	public void agregar(Componente c) {
		throw new RuntimeException("Operacion no soportada por el componente.");
	}

	/*
	 * Implementación por defecto. Solo la soporta la clase Directorio
	 */
	public void eliminar(Componente c) {
		throw new RuntimeException("Operacion no soportada por el componente.");
	}
			
	/*
	 * Codigo conveniente para las subclases
	 */
	protected static final String ESPACIOS = 
		"                                    ";
	protected String espacios(int nivel) {
		return ESPACIOS.substring(0, nivel*3); 
	}
}
