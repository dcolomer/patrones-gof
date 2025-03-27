package comportamiento.command.swingActions.ejemplo1_4;

import javax.swing.JTextArea;

public class SimulacionVueloCommand implements Command {

	private String sentidoVuelo;
	private JTextArea textArea;
	
	public SimulacionVueloCommand(String sentidoVuelo, JTextArea textArea) {
		this.sentidoVuelo = sentidoVuelo;
		this.textArea = textArea;
	}	
	
	@Override
	public void execute() {
		textArea.append(sentidoVuelo);
		textArea.append("\n");				
	}	
	
	@Override
	public boolean canExecute() {			
		return true;
	}

}
