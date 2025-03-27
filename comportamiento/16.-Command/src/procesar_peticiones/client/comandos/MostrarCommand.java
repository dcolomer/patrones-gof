package comportamiento.command.procesar_peticiones.client.comandos;

import javax.naming.OperationNotSupportedException;

import comportamiento.command.procesar_peticiones.client.receivers.Catalogo;
import comportamiento.command.procesar_peticiones.frmwrk.Command;

/*
 * Clase creada por el usuario del framework
 */
public class MostrarCommand implements Command {

	private Catalogo catalogo;
	private String msg = "El comando " + this.getClass().getSimpleName() + " no se puede revertir.";
		
	public MostrarCommand(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	@Override
	public void execute() {
		catalogo.mostrar();		
	}

	@Override
	public void unExecute() throws OperationNotSupportedException {
		throw new OperationNotSupportedException(msg);		
	}

	@Override
	public boolean canUnExecute() {		
		return false;
	}

}
