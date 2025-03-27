package comportamiento.command.procesar_peticiones.client.comandos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import comportamiento.command.procesar_peticiones.frmwrk.Command;

/*
 * Clase creada por el usuario del framework
 */
public class MacroCommand implements Command {

	private List<Command> comandos = new ArrayList<Command>();
	private boolean canUnExecute = true;

	public List<Command> getCommandos() {
		return comandos;
	}
	
	public void addComando(Command comando) {
		comandos.add(comando);
	}
	
	@Override
	public void execute() {
		System.out.println("---Ejecutando macro-comando...");
		for (Command cmd : comandos) {
			cmd.execute();
		}	
		System.out.println("---Fin del macro-comando.");
	}

	@Override
	public void unExecute() throws OperationNotSupportedException {
		Collections.reverse(comandos);
		for (Command cmd : comandos) {
			if (cmd.canUnExecute()) {
				cmd.unExecute();
			} else {
				canUnExecute = false;
			}
		}		
	}

	@Override
	public boolean canUnExecute() {
		return canUnExecute;
	}

}
