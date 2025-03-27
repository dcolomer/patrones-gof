package estructurales.decorator.tablas.refactor;

import javax.swing.table.TableModel;

/*
 * Las subclases de TableSortDecorator deben implementar el
 * metodo abstracto ordenar(), además de tableChanged().
 * 
 * tableChanged() es necesario porque TableModelDecorator
 * implementa la interfaz TableModelListener.
 */

public abstract class TableSortDecorator extends TableModelDecorator {

	public abstract void ordenar(int column);

	public TableSortDecorator(TableModel realModel) {
		super(realModel);
	}
}