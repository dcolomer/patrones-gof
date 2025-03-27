package comportamiento.observer.reports;

import java.awt.*;
import javax.swing.*;

/**
 * Observer que muestra un gráfico de barras de las ventas acumuladas
 * del año, hasta el mes actual, donde cada barra es un mes, para el 
 * departamento seleccionado por el usuario en ReportManagerGUI 
 * (Subject conjuntamente con ReportManager).
 * 
 * Cuando en ReportManagerGUI cambia el departamento, se llama al método
 * refrescarInforme() de esta clase para que actualice el informe. 
 */
public class InformeAnualGraficoBarras extends JFrame implements Subscriptor {
	
	private static final long serialVersionUID = 1L;
	private String departamento = "";
	private boolean fromRefresh;
	
	// Sostener una referencia al wrapper del Subject
	private ReportManagerGUI reportManagerGUI;
	
	// Delegar tareas de bajo nivel en un objeto GraficoBarrasHelper
	private GraficoBarrasHelper graficoBarrasHelper;

	public InformeAnualGraficoBarras(ReportManagerGUI reportManagerGUI)
			throws Exception {
		super("Informe anual de ventas acumuladas");		
		configurarVentana();		
		this.reportManagerGUI = reportManagerGUI;
		reportManagerGUI.getReportManager().registrar(this);
		graficoBarrasHelper = new GraficoBarrasHelper();
	}

	@Override
	public void refrescarInforme(Publicador subject) {
		// Comprobar la identidad del subject que ha invocado al método
		if (subject == reportManagerGUI.getReportManager()) {
			/// Obtener el estado del subject
			departamento = reportManagerGUI.getDepartmento().trim();
			graficoBarrasHelper.setDepartamento(departamento);
			reiniciarGrafico();			
			// Establecer flag procedencia refresco para evitar que el 
			// método paint recalcule innecesariamente el gráfico de barras
			fromRefresh = true;
			repaint(); // petición para que la MVJ llame a paint()
		}
	}

	@Override
	public void paint(Graphics g) {
		graficoBarrasHelper.dibujarTituloMeses(g);
		/*
		 * Optimizado. Sólo se recalcula cuando la llamada a paint() procede 
		 * de refrescarInforme(), es decir, de un cambio de departamento. 
		 */
		if (fromRefresh) {
			graficoBarrasHelper.dibujarBarras(g);
			fromRefresh = false;
		}
	}
	
	// Borrar dibujo actual
	private void reiniciarGrafico() {
		Graphics g = getGraphics();
		Dimension d = getSize();
		Color c = getBackground();
		g.setColor(c);
		g.fillRect(0, 0, d.width, d.height);
		repaint(); // petición para que la MVJ llame a paint()
	}

	private void configurarVentana() {
		setSize(500, 500);
		setLocation(550, 350);
		setVisible(true);
	}
}
