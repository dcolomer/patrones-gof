package comportamiento.command.swingActions.ejemplo1_1;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

class MostrarDialogoAction extends AbstractAction {
	private static final long serialVersionUID = 1L;

	public MostrarDialogoAction() {
		super("Mostrar dialogo");
	}

	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(
			(Component) e.getSource(),
			"Una \"action\" ha generado este cuadro de dialogo.");
	}
}
