package comportamiento.observer.reports;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Observer que muestra en un JTextArea las ventas del mes
 * en curso para el departamento seleccionado por el usuario
 * en ReportManagerGUI (Subject conjuntamente con ReportManager).
 * 
 * Cuando en ReportManagerGUI cambia el departamento, se llama
 * al método refrescarInforme() de esta clase para que actualice
 * el informe.
 */
public class InformeMensualTextual extends JFrame implements Subscriptor {

	private static final long serialVersionUID = 1L;
		
	private Container contentPane;
	private JPanel ventasPanel;
	private JLabel lblVentas;
	private JTextArea textAreaVentas;

	// Sostener una referencia al wrapper del Subject
	private ReportManagerGUI reportManagerGUI;
	
	// Delegar tareas de bajo nivel en un objeto InformeMensualHelper
	private InformeMensualHelper informeMensualHelper;

	public InformeMensualTextual(ReportManagerGUI reportManagerGUI) throws Exception {
		super("Informe mensual de ventas");
		configurarGUI();				
		this.reportManagerGUI = reportManagerGUI;
		reportManagerGUI.getReportManager().registrar(this);
		informeMensualHelper = new InformeMensualHelper();
	}

	@Override
	public void refrescarInforme(Publicador subject) {		
		// Comprobar la identidad del subject que ha invocado al método
		if (subject == reportManagerGUI.getReportManager()) {
			// Obtener el estado del subject
			String departamento = reportManagerGUI.getDepartmento().trim();
			
			Vector<String> trnList = 
					informeMensualHelper.getVentasMesEnCurso(departamento);
			
			actualizarGUI(departamento, trnList);
		}
	}

	private void configurarGUI() {
		crearControles();
		crearPaneles();
		configurarVentana();		
	}
	
	private void actualizarGUI(String departamento, Vector<String> trnList) {
		lblVentas.setText("Ventas mes. Departamento: "
				+ departamento);
		String content = "";
		for (int i = 0; i < trnList.size(); i++) {
			content = content + trnList.get(i) + "\n";
		}
		textAreaVentas.setText(content);		
	}

	private void crearPaneles() {
		ventasPanel = new JPanel();
		ventasPanel.add(lblVentas);
		ventasPanel.add(textAreaVentas);
		contentPane = getContentPane();		
		setContentPane(contentPane);
		contentPane.add(ventasPanel, BorderLayout.CENTER);
	}

	private void crearControles() {
		textAreaVentas = new JTextArea(5, 40);
		textAreaVentas.setFont(new Font("Serif", Font.PLAIN, 14));
		textAreaVentas.setLineWrap(true);
		textAreaVentas.setWrapStyleWord(true);		
		lblVentas = new JLabel("Ventas del mes en curso");		
	}
	
	private void configurarVentana() {
		setLocation(550, 25);
		setSize(400, 300);
		setVisible(true);		
	}
}