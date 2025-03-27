package estructurales.composite.diagramabarras;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/** "Composite" **/

/*
 * DiagramaBarras es una clase compuesta por
 * rectangulos, lineas y texto que se debe 
 * mostrar graficamente.
 */
public class DiagramaBarras implements Grafico {
	
	/*
	 * Lista de graficos 
	 */
	List<Grafico> graficos = new ArrayList<Grafico>();

	/*
	 * En el caso de una grafico compuesto no 
	 * podemos dibujar directamente, sino que
	 * hay que delegar en los graficos simples
	 * que lo forman.
	 * 
	 * Hay que tener en cuenta, que el metodo
	 * actua recursivamente hasta encontrar
	 * una clase simple que se pueda dibujar.
	 */
	@Override
	public void dibujar(Graphics2D g2) {
		for(Grafico grafico: graficos){			
			// Delegar en los objetos hijos
			grafico.dibujar(g2);			
		}	
	}
	
	/*
	 * La lista de graficos de un diagrama de barras
	 * se puede componer de cualquier numero de 
	 * rectangulos, lineas y texto grafico.
	 */
	@Override
	public Grafico[] getGrafico() {
		return graficos.toArray(new Grafico[] {});
	}

	/* 
	 * Agregar un nuevo objeto a la lista de hijos
	 */
	@Override
	public void add(Grafico figura) {
		graficos.add(figura);		
	}

	/*
	 * Eliminar de la lista de hijos el objeto especificado	 
	 */
	@Override
	public void remove(Grafico figura) {
		graficos.remove(figura);		
	}
			
}
