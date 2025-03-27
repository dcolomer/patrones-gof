package comportamiento.command.swingActions.ejemplo1_4;

import javax.swing.*;

/*
 Esta Action escribe un mensaje en una caja de texto y a continuación 
 se desactiva para poner como activa a su Action opuesta.
 */
public class BajarAction extends CommandAction {

	private static final long serialVersionUID = 1L;

	private static String nombre = "Bajar";

	private static ImageIcon icono = new ImageIcon("bajar.png");
	
	public BajarAction(Command command) {
		super(command, nombre, icono);			
	}
	
}
