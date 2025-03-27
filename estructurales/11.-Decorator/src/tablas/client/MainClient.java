package estructurales.decorator.tablas.client;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

import estructurales.decorator.tablas.refactor.TableBubbleSortDecorator;
import estructurales.decorator.tablas.refactor.TableFilterDecorator;
import estructurales.decorator.tablas.refactor.SecurityFilter;
//import estructurales.decorator.tablas.TableSortDecorator;
import estructurales.decorator.tablas.refactor.TableSortDecorator;

public class MainClient extends JFrame {

	private static final long serialVersionUID = 1L;
			
	/*
	 * Definición de la tabla.
	 * Se obtienen los datos de la tabla desde un archivo 
	 * de propiedades. La clase App es una clase de ayuda 
	 * para MainClient.
	 */
	final String[] cabeceras = { 
			App.getValor("cabeceraProducto"), 
			App.getValor("cabeceraPrecio") 
	};
	
	JTable table = new JTable(new Object[][] { 
		{ App.getValor("pro001"), App.getValor("pre001") }, 
		{ App.getValor("pro002"), App.getValor("pre002") }, 
		{ App.getValor("pro003"), App.getValor("pre003") }, 
		{ App.getValor("pro004"), App.getValor("pre004") }, 
		{ App.getValor("pro005"), App.getValor("pre005") }, 
		{ App.getValor("pro006"), App.getValor("pre006") }, 
		{ App.getValor("pro007"), App.getValor("pre007") }, 
		{ App.getValor("pro008"), App.getValor("pre008") }, 
		{ App.getValor("pro009"), App.getValor("pre009") }, 
		{ App.getValor("pro010"), App.getValor("pre010") }, 
		{ App.getValor("pro011"), App.getValor("pre011") }, 
		{ App.getValor("pro012"), App.getValor("pre012") }, }, 
		cabeceras);
	
	public static void main(String args[]) {
		App.launch(new MainClient(), "titulo", 300, 300, 450, 250);
	}

	/*
	 * Constructor. 
	 */
	public MainClient() {
		// Crea el decorador que decorará el modelo real de la tabla.
		// Notad cómo se construye a partir del modelo real (cómo lo envuelve).
		
		final TableSortDecorator decorador = new TableBubbleSortDecorator(table.getModel());
		/*final TableSortDecorator decorador = 
			new TableSortDecorator(table.getModel());*/

		/*
		 * Establecer el decorador como el modelo de la tabla. Esto 
		 * es posible porque el decorador implementa la interfaz TableModel.		 
		 */
		table.setModel(decorador);
		
		/*
		 * Creamos el decorador de filtrado.
		 * Notad que se crea encapsulando al decorador de ordenación.
		 */
		TableFilterDecorator decoradorFiltroPrecio = 
			new SecurityFilter(decorador);
		
		/*
		 * Informamos al decorador de filtrado qué filas debe ocultar.
		 * Recorremos el modelo real, obtenemos el valor de la columna precio.
		 * Como el valor contiene el símbolo del euro al final, hay que quitarlo
		 * y convertirlo a float. A continuación, hay que compararlo con 2.0, que
		 * es el precio a partir del cual una fila debe quedar ocultada.
		 */
		final int COL_PRECIO = 1;
		for (int i=0; i<table.getModel().getRowCount(); i++) {
			String numero_cadena = table.getModel().getValueAt(i, COL_PRECIO).toString();
			String numero  = numero_cadena.substring(0, numero_cadena.length()-1);
			float valor_celda = Float.parseFloat(numero);
			if (valor_celda >= 2.0)
				decoradorFiltroPrecio.filtrar(i, false);
		}	

		// Establecemos el decorador de filtrado como el modelo para la tabla
		table.setModel(decoradorFiltroPrecio);
		
		// Hacer que la tabla quede dentro de un panel con scroll.
		getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

		// Añadir un area de estado a la ventana.
		getContentPane().add(App.getStatusArea(), BorderLayout.SOUTH);

		App.showStatus(App.getValor("msgInicial"));

		// Obtener una referencia a la cabecera de la tabla
		JTableHeader cabeceraTabla = (JTableHeader) table.getTableHeader();

		// Captar los click de raton sobre la cabecera de la tabla 
		// y llamar al metodo de ordenacion del decorador.
		cabeceraTabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TableColumnModel tcm = table.getColumnModel();
				
				/*
				 * Ahora atencion: Los indices de las columnas de la vista no se 
				 * tienen porque corresponder con las columnas del modelo, por lo 
				 * que hay que realizar un mapeo. El motivo de porque que no podrían
				 * corresponderse es variado: podrian mostrarse en la vista en orden 
				 * inverso, ocultas, etc.
				 */
				
				// Obtener el indice de la columna clicada
				int col_en_vista = tcm.getColumnIndexAtX(e.getX());
				
				// Convertir ese indice en el que equivalente del modelo
				int col_en_modelo = table.convertColumnIndexToModel(col_en_vista);

				// Ordenar por la columna pulsada
				decorador.ordenar(col_en_modelo);

				// Actualizar el area de estado
				App.showStatus(App.getValor("msgOrden")+ " " + 
						cabeceras[col_en_modelo]);
			}
		});
	}
	
}


