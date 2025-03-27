package estructurales.decorator.tablas;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/*
 * Decorador que envuelve el modelo real y le añade 
 * la capacidad de ordenarlo.
 */
public class TableSortDecorator 
	implements TableModel, TableModelListener {

	// Modelo real que vamos a decorar	
	private TableModel realModel;
	 
	/*
	 * Este metodo es utilizado por las subclases para 
	 * acceder al modelo real
	 */
	protected TableModel getRealModel() {
		return realModel;
	}
	
	/*
	 * Array de enteros que permite al decorador controlar
	 * la ordenación del modelo real sin alterarlo.
	 * Cada posición del array se corresponde con una fila 
	 * de la tabla.	
	 */
	private int indices[];
	
	/*
	 * Constructor.
	 * Recibe el modelo real como parametro.
	 */
	public TableSortDecorator(TableModel model) {
		this.realModel = model;
		/*
		 * La propia clase TableSortDecorator se registra 
		 * como oyente de eventos que se produzcan sobre 
		 * el modelo real.
		 */
		realModel.addTableModelListener(this);
		//
		inicializarIndices();
	}
		
	/* *************************************************
	 * El siguientes metodo esta definidos en la interfaz 
	 * TableModelListener.
	 */
				
	/*
	 * Cuando el modelo cambia se sebe llamar al metodo que
	 * inicializa el array de indices. 
	 */
	@Override
	public void tableChanged(TableModelEvent e) {
		inicializarIndices();
	}
	
	/* ***************************************************
	 * Los siguientes nueve metodos estan definidos en la 
	 * interfaz TableModel. Todos invocan a los metodos 
	 * del modelo real.
	 */
	
	/*
	 * 1º. Retorna el objeto Class de la columna del modelo
	 * real, según el indice recibido por parametro.
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return realModel.getColumnClass(columnIndex);
	}

	/*
	 * 2º. Retorna el numero total de columnas del modelo real.	 
	 */
	@Override
	public int getColumnCount() {
		return realModel.getColumnCount();
	}

	/*
	 * 3º. Retorna el nombre de la columna del modelo
	 * real, según el indice recibido por parametro.	 
	 */
	@Override
	public String getColumnName(int columnIndex) {
		return realModel.getColumnName(columnIndex);
	}

	/*
	 * 4º. Retorna el total de filas del modelo real.
	 */
	@Override
	public int getRowCount() {
		return realModel.getRowCount();
	}

	/*
	 * 5º. Retorna un booleano para indicar si la celda 
	 * indicada por los indices de los parametros es o no editable.  
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		return realModel.isCellEditable(row, column);
	}

	/*
	 * 6º. Retorna el valor de la celda indicada por los 
	 * indices de los parametros. 
	 */
	@Override
	public Object getValueAt(int row, int column) {
		return realModel.getValueAt(indices[row], column);
	}

	/*
	 * 7º. Establece el valor recibido como primer parametro
	 * en la la celda indicada por el resto de parametros. 
	 */
	@Override
	public void setValueAt(Object aValue, int row, int column) {
		realModel.setValueAt(aValue, indices[row], column);
	}

	/*
	 * 8º. Añade el objeto recibido por parametro a la lista 
	 * de oyentes de eventos de modificacion en el modelo real.
	 */
	@Override
	public void addTableModelListener(TableModelListener l) {
		realModel.addTableModelListener(l);
	}	
	
	/*
	 * 9. Quita el objeto recibido por parametro de la lista 
	 * de oyentes de eventos de modificacion en el modelo real.
	 */
	@Override
	public void removeTableModelListener(TableModelListener l) {
		realModel.removeTableModelListener(l);
	}
	
	
	
	/* ********************************************************
	 * Los siguientes metodos sirven para ordenar mediante el 
	 * algoritmo de la burbuja. 
	 * *********************************************************/
	 
	/* 
	 * Este método es invocado desde el codigo cliente al hacer 
	 * clic sobre alguna columna de la tabla.
	 * 
	 * Parametros:
	 * 		column: la columna a ordear (la fruta o el precio)
	 * 
	 * Utiliza dos bucles para recorrer las celdas de la columna
	 * correspondiente y comparar sus valores. Cuando encuentra
	 * que debe ordenar, llama al método swap para intercambiar
	 * las posiciones del array indices.
	 */
	public void ordenar(int column) {
		int rowCount = getRowCount();
		for (int i = 0; i < rowCount; i++) {
			for (int j = i + 1; j < rowCount; j++) {				
				if (compare(indices[i], indices[j], column) < 0) {					
					swap(i, j); // Hay que ordenar
				}
			}
		}
	}

	/*
	 * Para una columna dada, compara los valores de dos filas 
	 * del modelo real.
	 * Parametros:
	 * 		i: una fila
	 * 		j: otra fila
	 * 		column: la columna (fruta o precio)
	 * El método compareTo() devolverá:
	 * 		-1 si valorFila_i < valorFila_j
	 *    	 0 si valorFila_i = valorFila_j
	 *    	 1 si valorFila_i > valorFila_j
	 */
	private int compare(int i, int j, int column) {
		TableModel realModel = this.realModel;
		Object valorFila_i = realModel.getValueAt(i, column);
		Object valorFila_j = realModel.getValueAt(j, column);
		int resultado = valorFila_j.toString().compareTo(valorFila_i.toString());
		return (resultado < 0) ? -1 : ((resultado > 0) ? 1 : 0);
	}
	
	/*
	 * Intercambia las posiciones del array
	 */
	private void swap(int i, int j) {
		int tmp = indices[i];
		indices[i] = indices[j];
		indices[j] = tmp;
	}

	/*
	 * Rellenar el array de indices, utilizando como 
	 * valor la misma posicion del indice.
	 */
	private void inicializarIndices() {
		indices = new int[getRowCount()];
		for (int i = 0; i < indices.length; ++i) {
			indices[i] = i;
		}
	}
	
}
