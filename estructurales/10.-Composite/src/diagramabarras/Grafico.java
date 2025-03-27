package estructurales.composite.diagramabarras;

import java.awt.Graphics2D;

/** Interfaz para el "Component" **/
public interface Grafico {

	/*
	 * Dibujar una figura en la pantalla.  
	 */
	public void dibujar(Graphics2D g2);

	/*
	 * Este metodo se implementara de diferente manera
	 * en una clases simple que en una clases compuesta.
	 * 
	 * En una clase simple consiste en devolver el grafico
	 * que representa la propia clase.
	 * 
	 * Mientras que en una clase compuesta consiste en 
	 * devolver una lista con los graficos que la forman.
	 * Por ejemplo, un rectangulo se compone de 
	 * cuatro objetos de tipo linea.
	 */
	public Grafico[] getGrafico();
	
	/*
	 * Este metodo no se implementara en las clases simples,
	 * ya que no tienen hijos que añadir
	 */
	public void add(Grafico grafico);
	
	/*
	 * Este metodo no se implementara en las clases simples,
	 * ya que no tienen hijos que eliminar
	 */
	public void remove(Grafico grafico);

}
