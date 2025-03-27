package estructurales.decorator.tablas.refactor;

import javax.swing.table.TableModel;

/*
 * Las subclases de TableFilterDecorator deben implementar el
 * metodo abstracto filtrar(), además de tableChanged().
 * 
 * tableChanged() es necesario porque TableModelDecorator
 * implementa la interfaz TableModelListener.
 */
public abstract class TableFilterDecorator 
					extends TableModelDecorator {
	
	public abstract void filtrar(int fila, boolean estado);
	
	public TableFilterDecorator(TableModel model) {
		super(model);
		
	}

}
