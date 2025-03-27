package comportamiento.command.swingActions.ejemplo1_1;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

class SalirAction extends AbstractAction {
	private static final long serialVersionUID = 1L;

	public SalirAction() {
		super("Salir");
	}

	public void actionPerformed(ActionEvent e) {
		int respuesta = JOptionPane.showConfirmDialog(
			null,
			"Esta acción cerrará la aplicación, ¿desea continuar?",
			"Atención",
			JOptionPane.YES_NO_OPTION);

		if (respuesta == JOptionPane.YES_OPTION) {
			System.exit(0);	
		}			
	}
}
