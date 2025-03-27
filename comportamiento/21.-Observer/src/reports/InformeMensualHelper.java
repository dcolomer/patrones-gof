package comportamiento.observer.reports;

import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Clase se soporte para el observador que se encarga de 
 * dibujar el JTextArea con las ventas del mes en curso.
 * Esta clase implementa los detalles de bajo nivel para 
 * obtener las ventas, mientras que el observador 
 * correspondiente, se encarga de asuntos más generales.
 */
public class InformeMensualHelper {

	public Vector<String> getVentasMesEnCurso(String departamento) {
		
		Vector<String> ventasTotales = 
				Utilidades.fileToVector(ReportManagerGUI.Fichero);

		Vector<String> ventasMes = new Vector<String>();
		
		// Mes actual
		int mes = Utilidades.getMesEnCurso();

		// Texto a buscar
		String textoBusqueda = departamento + "," + mes + ",";
		
		/*
		 * Recorrer todas las filas en busca de aquellas que coincidan con el 
		 * mes en curso y el departamento seleccionado por el usuario
		 */
		int j = 0;
		for (int i = 0; i < ventasTotales.size(); i++) {
			String fila = ventasTotales.get(i);
			if (match(fila, textoBusqueda)) {
				StringTokenizer st = new StringTokenizer(fila, ",");
				st.nextToken();
				fila = "                        " + 
						++j + ". " + 
						st.nextToken() + "/" + st.nextToken() + 
						"---" + st.nextToken() + " Items" + "..."
						+ st.nextToken() + " €";				
				ventasMes.add(fila);
			}
		}
		return ventasMes;
	}
	
	private boolean match(String fila, String textoBusqueda) {
		return fila.indexOf(textoBusqueda) > -1;		
	}
}
