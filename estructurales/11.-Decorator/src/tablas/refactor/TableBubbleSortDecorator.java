package estructurales.decorator.tablas.refactor;

import javax.swing.table.TableModel;
import javax.swing.event.TableModelEvent;

/*
 * Esta clase decora el modelo para ofrecer 
 * una ordenacion de sus datos mediante una 
 * implementación del algoritmo de la burbuja
 */
public class TableBubbleSortDecorator 
	extends TableSortDecorator {

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
	 * Notad que se la pasa a su superclase
	 */
	public TableBubbleSortDecorator(TableModel model) {
		super(model);
		inicializarIndices();
	}

	/*
	 * Cuando el modelo cambia se sebe llamar al metodo que
	 * inicializa el array de indices. 
	 */
	@Override
	public void tableChanged(TableModelEvent e) {
		inicializarIndices();
	}

	/*
	 * Retorna el valor de la celda indicada por los 
	 * indices de los parametros. 
	 */
	@Override
	public Object getValueAt(int row, int column) {
		return getRealModel().getValueAt(indices[row], column);
	}

	/*
	 * Establece el valor recibido como primer parametro
	 * en la la celda indicada por el resto de parametros. 
	 */
	@Override
	public void setValueAt(Object aValue, int row, int column) {
		getRealModel().setValueAt(aValue, indices[row], column);
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
					swap(i, j);
				}
			}
		}
	}

	private void swap(int i, int j) {
		int tmp = indices[i];
		indices[i] = indices[j];
		indices[j] = tmp;
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
		TableModel realModel = getRealModel();
		Object valorFila_i = realModel.getValueAt(i, column);
		Object valorFila_j = realModel.getValueAt(j, column);
		int resultado = valorFila_j.toString()
				.compareTo(valorFila_i.toString());
		return (resultado < 0) ? -1 : ((resultado > 0) ? 1 : 0);
	}

	private void inicializarIndices() {
		indices = new int[getRowCount()];
		for (int i = 0; i < indices.length; ++i) {
			indices[i] = i;
		}
	}

}
