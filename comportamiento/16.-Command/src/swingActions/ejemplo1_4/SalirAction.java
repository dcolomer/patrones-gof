package comportamiento.command.swingActions.ejemplo1_4;

import javax.swing.ImageIcon;

public class SalirAction extends CommandAction {

	private static final long serialVersionUID = 1L;

	private static String nombre = "Salir";

	private static ImageIcon icono = new ImageIcon("salir.jpg");
	
	public SalirAction(Command command) {
		super(command, nombre, icono);			
	}
}
