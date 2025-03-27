package estructurales.decorator.tablas.refactor;

import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;

public class SecurityFilter extends TableFilterDecorator {

	// Array donde cada posici�n indica si
	private boolean[] arrayFilasOcultas;
	private int numFilasVisibles;

	public SecurityFilter(TableModel model) {
		super(model);
		inicializar();
	}

	/*
	 * El c�digo cliente llama a este m�todo cuando necesita que se oculte o se
	 * vuela a mostrar alguna fila.
	 */
	@Override
	public void filtrar(int fila, boolean estado) {
		arrayFilasOcultas[fila] = !estado;
		if (estado)
			numFilasVisibles++;
		else
			numFilasVisibles--;
	}

	/*
	 * M�todo que se ejecuta al construir el objeto y cuando cambia el modelo
	 */
	private void inicializar() {

		// En un princip�o, todas las filas son visibles
		numFilasVisibles = getRealModel().getRowCount();

		// Pueden haber tantas filas ocultas como filas existan
		arrayFilasOcultas = new boolean[numFilasVisibles];
	}

	/*
	 * Retornar el n�mero de filas visibles
	 */
	@Override
	public int getRowCount() {
		return numFilasVisibles;
	}

	/*
	 * Devolver la correspondiente fila no oculta
	 */
	@Override
	public Object getValueAt(int row, int col) {
		// BUCLE INFINITO
		for (int realRow = 0; true; realRow++) {
			if (!arrayFilasOcultas[realRow]) {
				if (row == 0) {
					return getRealModel().getValueAt(realRow, col);
				}
				row--;
			}
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		inicializar();
	}

}
