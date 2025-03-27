package estructurales.decorator.tablas.refactor;

import javax.swing.table.TableModel;
import javax.swing.event.TableModelListener;

/*
 * TableModelDecorator implementa a TableModel y 
 * a TableModelListener. TableModelListener es una 
 * interfaz que define un metodo muy interesante: 
 * tableChanged(), el cual es llamado cuando cambia 
 * el modelo de la tabla. tableChanged() no está 
 * implementado en la clase abstracta, ya que se 
 * deja que las subclases lo implementen.
 */
public abstract class TableModelDecorator 
		implements TableModel, TableModelListener {

	// Objeto al que hay que decorar
	private TableModel realModel;
	
	/* 
	 * Este metodo es utilizado por las subclases para 
	 * acceder al modelo real. 
	 */
	protected TableModel getRealModel() {
		return realModel;
	}
	
	/*
	 * Constructor.
	 * -Recibimos el modelo real y lo asociamos a 
	 * nuestra referencia.
	 * -La clase se suscribe como oyente a los eventos 
	 * que se produzcan en al cambiar el modelo real.
	 */	
	public TableModelDecorator(TableModel model) {
		this.realModel = model;
		realModel.addTableModelListener(this);
	}

	/*
	 * Los siguientes nueve metodos estan definidos en 
	 * la interfaz TableModel. Todos son redirigidos 
	 * hacia el modelo real.
	 */

	/*
	 * El parametro recibido es this. Queremos que la
	 * clase actual se registre en los cambios que se
	 * produzcan en el modelo real.
	 */
	@Override
	public void addTableModelListener(TableModelListener l) {
		realModel.addTableModelListener(l);
	}

	/*
	 * Retorna la clase de la columna del modelo real 
	 * recibida por parametro. 
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return realModel.getColumnClass(columnIndex);
	}

	/*
	 * Retorna el numero de columnas del modelo real. 
	 */
	@Override
	public int getColumnCount() {
		return realModel.getColumnCount();
	}

	/*
	 * Retorna el nombre de la columna real recibida
	 * por parametro. 
	 */
	@Override
	public String getColumnName(int columnIndex) {
		return realModel.getColumnName(columnIndex);
	}

	/*
	 * Retorna el numero de filas del modelo real. 
	 */
	@Override
	public int getRowCount() {
		return realModel.getRowCount();
	}

	/*
	 * Retorna el valor de la celda del modelo real
	 * segun los valores de fila y columna pasados 
	 * por parametro. 
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return realModel.getValueAt(rowIndex, columnIndex);
	}

	/*
	 * Retorna un boolean indicando si la celda del modelo 
	 * real es o no editable. 
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return realModel.isCellEditable(rowIndex, columnIndex);
	}

	/*
	 * Desregistra como oyente al objeto recibido por parametro 
	 * de los eventos producidos al cambiar el modelo real.  
	 */
	@Override
	public void removeTableModelListener(TableModelListener l) {
		realModel.removeTableModelListener(l);
	}

	/*
	 * Establece el valor pasado por parametro en la celda del 
	 * modelo real segun la fila y columna, tambien pasados 
	 * por parametro. 
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		realModel.setValueAt(aValue, rowIndex, columnIndex);
	}
	
}
