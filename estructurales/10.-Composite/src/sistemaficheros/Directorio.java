package estructurales.composite.sistemaficheros;

import java.util.ArrayList;
import java.util.List;

/** Composite **/
public class Directorio extends Componente {		

	// Contenedor para almacenar a los elementos hijos
	private List<Componente> componentes = 
		new ArrayList<Componente>();

	public Directorio(String name) {
		super(name);
	}	
			
	/*
	 * Imprime el nombre del directorio y recorre recursivamente 
	 * sus hijos para que estos se muestren. 
	 */
	@Override
	public void mostrar(int profundidad) {		
		System.out.println(espacios(profundidad) + "+" + nombre);
		
		// DEBUG
		//System.out.println(espacios(profundidad) + "+" + nombre + " (nivel " + profundidad + ")");		
		
		for (Componente componente : componentes) {			            
            componente.mostrar(profundidad + 1);            
		}
	}
	
	/* ****** Operaciones para manejar la coleccion de componentes */
	@Override
	public void agregar(Componente componente) {
		componentes.add(componente);
	}

	@Override
	public void eliminar(Componente componente) {
		componentes.remove(componente);
	}
				
}