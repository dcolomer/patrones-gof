package comportamiento.observer.reports;

import java.awt.Color;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Clase se soporte para el observador que se encarga de 
 * dibujar el gráfico de barras. Esta clase implementa los 
 * detalles de bajo nivel para dibujar el grafico, mientras 
 * que el observador correspondiente, se encarga de asuntos
 * más generales.
 */
public class GraficoBarrasHelper {

	private String departamento = "";

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
	public void dibujarTituloMeses(Graphics g) {
		g.drawString(departamento + " Ventas acumuladas", 150, 50);
		String[] meses = { "Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul",
				"Ago", "Sep", "Oct", "Nov", "Dic" };
		int x = 50, y = 115;
		for (int j = 0; j < meses.length; j++) {
			g.setColor(Color.blue);
			g.drawString(meses[j], x, y);
			y = y + 30;
		}
	}
	
	public void dibujarBarras(Graphics g) {
		int x = 100, y = 100;
		int w = 50, h = 20;
		int[] totals = getTotalAnioAcumulado(departamento);
		
		// Mes actual
		int mes = Utilidades.getMesEnCurso();

		for (int i = 0; i < mes; i++) {
			g.setColor(Color.blue);
			if (totals[i] > 0) {
				w = (int) (totals[i] / 50);
				g.fillRect(x, y, w, h);
				g.drawString(totals[i] + "€", x + w + 5, y + 15);
			}
			y = y + 30;
		}		
	}
	
	private int[] getTotalAnioAcumulado(String departamento) {
		int[] totales = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < 12; i++) {
			totales[i] = getTotalVentasMes(i + 1, departamento);
		}
		return totales;
	}

	private int getTotalVentasMes(int mes, String departamento) {		
		Vector<String> filas = 
				Utilidades.fileToVector(ReportManagerGUI.Fichero);
		int total = 0;
		String textoBusqueda = departamento + "," + mes + ",";

		/*
		 * Recorrer todas las filas en busca de aquellas que coincidan 
		 * con el mes y departamento especificados en los parámetros
		 */
		for (int i = 0; i < filas.size(); i++) {
			String fila = filas.get(i);
			if (match(fila, textoBusqueda)) {
				StringTokenizer st = new StringTokenizer(fila, ",");
				st.nextToken();// ignorar departmento
				st.nextToken();// ignorar el mes
				st.nextToken();// ignorar fecha
				st.nextToken();// ignorar items
				String importe = st.nextToken();
				total = total + new Integer(importe).intValue();
			}
		}
		return total;
	}
	
	private boolean match(String fila, String textoBusqueda) {
		return fila.indexOf(textoBusqueda) > -1;		
	}
}
