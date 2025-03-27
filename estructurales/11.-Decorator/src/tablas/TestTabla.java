package estructurales.decorator.tablas;

import javax.swing.*;
import javax.swing.table.*;

public class TestTabla extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String args[]) {
		TestTabla frame = new TestTabla();
		frame.setTitle("Tablas y Modelos");
		frame.setBounds(300, 300, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}

	public TestTabla() {
		// Creamos un objeto Modelo
		TableModel modelo = new Modelo();
		
		// Creamos una tabla y proporcionamos su modelo
		JTable tabla = new JTable(modelo);
		
		// Creamos un marco para la tabla capaz de permitir scroll
		JScrollPane marco = new JScrollPane(tabla);
		
		// Añadimos el marco al JFrame
		getContentPane().add(marco);
	}

	/*
	 * Clase privada
	 */
	private static class Modelo extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		final int rows = 100, cols = 10;

		@Override
		public int getRowCount() {
			return rows;
		}

		@Override
		public int getColumnCount() {
			return cols;
		}

		@Override
		public Object getValueAt(int row, int col) {
			return "(" + row + "," + col + ")";
		}
	}
}
