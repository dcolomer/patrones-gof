package comportamiento.command.swingActions.ejemplo1_4;

import javax.swing.JOptionPane;

public class SalirCommand implements Command {

	@Override
	public void execute() {
		int respuesta = JOptionPane.showConfirmDialog(
				null,
				"Esta acción cerrará la aplicación, ¿desea continuar?",
				"Atención",
				JOptionPane.YES_NO_OPTION);

		if (respuesta == JOptionPane.YES_OPTION) {
			System.exit(0);	
		}
		
	}

	@Override
	public boolean canExecute() {		
		return true;
	}

}
