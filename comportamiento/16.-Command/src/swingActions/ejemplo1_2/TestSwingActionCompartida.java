package comportamiento.command.swingActions.ejemplo1_2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public class TestSwingActionCompartida extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JMenuBar barraMenu;
	private JMenu menuFichero;
	private JMenuItem elementoMenuAbrir;
	
	private JToolBar barraHerramientas;
	private JButton botonAbrir;
	
	private JTextArea cajaTexto;
	
	private Action abrirAction;
	
	public TestSwingActionCompartida() {
		super("TestSwingActionCompartida");
		crearGUI();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestSwingActionCompartida instancia = new TestSwingActionCompartida();
					instancia.pack();
					instancia.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
	}
	
	private void crearGUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Crear la barra de menú y el menú Fichero
		barraMenu = new JMenuBar();
		menuFichero = new JMenu("Fichero");
		
		// Crear la barra de herramientas
		barraHerramientas = new JToolBar();
				
		// Crear la caja de texto para mostrar la salida.
		cajaTexto = new JTextArea(5, 30);
		
		// Añadir barras de desplazamiento a la caja de texto
		JScrollPane scrollPane = new JScrollPane(cajaTexto);
		
		// Creamos un panel para organizar en él los controles
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(400, 150));
		panel.add(barraHerramientas, BorderLayout.NORTH);
		panel.add(scrollPane, BorderLayout.CENTER);
		setContentPane(panel);
		
		// Configurar la barra de menú y añadirla a la ventana (JFrame)		
		barraMenu.add(menuFichero);
		setJMenuBar(barraMenu);
		
		/*
		 * Crear una action como clase anónima para el comando "Abrir"
		 */
		ImageIcon iconoAbrir = new ImageIcon("abrir.gif");
		abrirAction = new AbstractAction("Abrir", iconoAbrir) 
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				cajaTexto.append("Ejecutada la action \"Abrir\" desde " + 
						e.getActionCommand() + "\n");
			}
		};
		
		/*
		 * Añadimos el botón "Abrir" a la barra de herramientas e 
		 * indicamos la action que se debe ejecutar al pulsar el botón
		 */
		botonAbrir = barraHerramientas.add(abrirAction);
		botonAbrir.setIcon(new ImageIcon("abrir.gif"));
		botonAbrir.setText("");
		botonAbrir.setActionCommand("Boton abrir");
		botonAbrir.setToolTipText("Este es el boton abrir");
				
		/*
		 * Usamos la misma action para añadir un elemento de menú
		 * al menú 'Fichero'
		 */
		elementoMenuAbrir = menuFichero.add(abrirAction);
		elementoMenuAbrir.setIcon(iconoAbrir);
		elementoMenuAbrir.setActionCommand("Elemento de menu abrir");
	}	
}
