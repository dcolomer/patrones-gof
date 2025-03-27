package comportamiento.command.swingActions.ejemplo1_3;

import java.awt.event.*;
import javax.swing.*;

/*
 Esta Action escribe un mensaje en una caja de texto y a continuación 
 se desactiva para poner como activa a su Action opuesta.
 */
public class SimulacionVueloAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private String sentidoVuelo;
	private JTextArea textArea;
	private Action accionOpuesta;

	public SimulacionVueloAction(String sentidoVuelo, JTextArea textArea) {
		this.sentidoVuelo = sentidoVuelo;
		this.textArea = textArea;
	}

	/*
	 * Establecer la Action opuesta
	 */
	public void setOpuesto(Action action) {
		accionOpuesta = action;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		textArea.append(sentidoVuelo);
		textArea.append("\n");
		if (accionOpuesta != null) {
			setEnabled(false); // Se autidesactiva
			accionOpuesta.setEnabled(true); // activa la opuesta
		}
	}
}
