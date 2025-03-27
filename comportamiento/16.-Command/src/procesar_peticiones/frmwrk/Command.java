package comportamiento.command.procesar_peticiones.frmwrk;

import javax.naming.OperationNotSupportedException;

/*
 * Command
 * Clase del framework
 */
public interface Command {
	public void execute();
	public void unExecute() throws OperationNotSupportedException;
	public boolean canUnExecute();
}
