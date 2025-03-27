package comportamiento.command.swingActions.ejemplo1_3;

import java.awt.*;
import javax.swing.*;

/*
 * Creamos dos instancias de la clase SimulacionVueloAction.
 * Estas acciones se ejecutan mediante un menú o un botón
 * de la barra de herramientas.

 */
public class TestSwingActionEnabledDisabled extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestSwingActionEnabledDisabled instancia = 
							new TestSwingActionEnabledDisabled();
					instancia.pack();
					instancia.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}	
		
	public TestSwingActionEnabledDisabled() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Creamos la barra de menú y el menú
		JMenuBar barraMenu = new JMenuBar();		
		JMenu menu = new JMenu("Simulador");
		barraMenu.add(menu);
		
		// Añadimos lo anterior al JFrame
		setJMenuBar(barraMenu);
		
		// Creamos una barra de herramientas
		JToolBar toolBar = new JToolBar();		
		JTextArea textArea = new JTextArea(10, 40);
		
		// Añadimos lo anterior al JFrame
		add(toolBar, BorderLayout.NORTH);
		add(textArea, BorderLayout.CENTER);

		/*
		 * Creamos las dos instancias de la Action
		 */
		SimulacionVueloAction subirAction = 
				new SimulacionVueloAction("Subiendo...", textArea);
		subirAction.putValue(Action.NAME, "Subir");
		subirAction.putValue(Action.SMALL_ICON, 
				new ImageIcon("subir.png"));		
		
		SimulacionVueloAction bajarAction = 
				new SimulacionVueloAction("Bajando...", textArea);
		bajarAction.putValue(Action.NAME, "Bajar");
		bajarAction.putValue(Action.SMALL_ICON, 
				new ImageIcon("bajar.png"));

		// Establecemos el opuesta de cada una
		subirAction.setOpuesto(bajarAction);
		bajarAction.setOpuesto(subirAction);
		
		// Decidimos que la Action activa sea la subir
		bajarAction.setEnabled(false);

		/*
		 * Añadimos las Action al menú, lo que implícitamente crea
		 * dos objetos JMenuItem
		 */
		menu.add(subirAction);
		menu.add(bajarAction);

		/*
		 * Añadimos las Action a la barra de herramientas, lo que 
		 * implícitamente crea dos objetos JButton
		 */
		toolBar.add(subirAction);
		toolBar.add(bajarAction);		
	}				
}
