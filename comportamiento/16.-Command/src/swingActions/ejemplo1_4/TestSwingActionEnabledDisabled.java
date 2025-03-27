package comportamiento.command.swingActions.ejemplo1_4;

import java.awt.*;
import javax.swing.*;

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
				
		SubirAction subirAction = 
				new SubirAction(new SimulacionVueloCommand("Subiendo...", textArea));
		
		BajarAction bajarAction = 
				new BajarAction(new SimulacionVueloCommand("Bajando...", textArea));
		
		SalirAction salirAction =
				new SalirAction(new SalirCommand());
		
		// Establecemos el opuesto de cada una
		subirAction.setAccionOpuesta(bajarAction);
		bajarAction.setAccionOpuesta(subirAction);		
		
		// Decidimos que la Action activa sea la de subir
		bajarAction.setEnabled(false);

		/*
		 * Añadimos las Action al menú, lo que implícitamente crea
		 * dos objetos JMenuItem
		 */
		menu.add(subirAction);
		menu.add(bajarAction);
		menu.add(salirAction);

		/*
		 * Añadimos las Action a la barra de herramientas, lo que 
		 * implícitamente crea dos objetos JButton
		 */
		toolBar.add(subirAction);
		toolBar.add(bajarAction);
		toolBar.add(salirAction);	
		
		ActionManager.getInstance().update();
	}				
}
