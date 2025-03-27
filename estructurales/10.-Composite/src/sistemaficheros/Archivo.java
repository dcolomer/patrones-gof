package estructurales.composite.sistemaficheros;

/** Leaf **/
public class Archivo extends Componente {
	
	public Archivo(String nombre) {
		super(nombre);
	}
	
	@Override
	public void mostrar(int profundidad) {			
		System.out.println(espacios(profundidad) + nombre);
		
		// DEBUG
		//System.out.println(espacios(profundidad) + nombre + "(nivel " + profundidad + ")");		
	}
		
}
