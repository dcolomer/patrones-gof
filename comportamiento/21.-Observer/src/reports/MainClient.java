package comportamiento.observer.reports;

import java.awt.EventQueue;

/**
 * Clase cliente que lleva a cabo lo siguiente:
 * 
 * - Crea la ventana principal de la aplicación, 
 * desde la que el usuario puede seleccionar un 
 * departamento del combo, para ver los informes.
 * Esta ventana en un wrapper que envuelve al subject.
 *  
 * - Crea los observadores, esto es, un informe
 * de cada tipo.  
 */
public class MainClient {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					ReportManagerGUI reportManagerGUI = new ReportManagerGUI();
					new InformeMensualTextual(reportManagerGUI);
					new InformeAnualGraficoBarras(reportManagerGUI);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
