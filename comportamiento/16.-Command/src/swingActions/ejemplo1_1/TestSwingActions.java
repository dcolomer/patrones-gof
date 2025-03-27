package comportamiento.command.swingActions.ejemplo1_1;

import java.awt.EventQueue;

import javax.swing.*;

public class TestSwingActions extends JFrame {
	private static final long serialVersionUID = 1L;

	public static void main(String args[]) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					TestSwingActions instancia = new TestSwingActions();
					instancia.setTitle("Swing Actions");
					instancia.setSize(500, 400);
					instancia.setLocationRelativeTo(null);					
					instancia.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});				
	}

	// Constructor
	public TestSwingActions() {
		// Creamos una barra de menús
		JMenuBar barraMenu = new JMenuBar();
		
		// Creamos un menú
		JMenu menuOpciones = new JMenu("Opciones");
		
		/*
		 * Creamos dos elementos de menú. Al proporcionar un 
		 * objeto Action al método add() de JMenu, esta action ya 
		 * queda vinculada al nuevo elemento de menú que se crea.
		 */
		// Version corta
		//menuOpciones.add(new MostrarDialogoAction());
		
		// Version larga
		JMenuItem menuItemMostrarDialogo = new JMenuItem();
		menuItemMostrarDialogo.setAction(new MostrarDialogoAction());
		menuOpciones.add(menuItemMostrarDialogo);

		// Version corta
		//menuOpciones.add(new SalirAction());
		
		// Version larga
		JMenuItem menuItemSalir = new JMenuItem();
		menuItemSalir.setAction(new SalirAction());
		menuOpciones.add(menuItemSalir);
		
		// Añadimos el menu a la barra
		barraMenu.add(menuOpciones);
		
		// Añadimos la barra al JFrame (ventana principal)
		setJMenuBar(barraMenu);
	}
}