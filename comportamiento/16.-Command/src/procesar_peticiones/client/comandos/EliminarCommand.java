package comportamiento.command.procesar_peticiones.client.comandos;

import javax.naming.OperationNotSupportedException;

import comportamiento.command.procesar_peticiones.client.receivers.Articulo;
import comportamiento.command.procesar_peticiones.client.receivers.Catalogo;
import comportamiento.command.procesar_peticiones.frmwrk.Command;


/*
 * Clase creada por el usuario del framework
 */
public class EliminarCommand implements Command {

	private Catalogo catalogo;
	private String codigo;
	private Articulo recoverArticulo;
	private boolean canUnExecute = true;
	
	public EliminarCommand(Catalogo catalogo, String codigo) {
		this.catalogo = catalogo;
		this.codigo = codigo;
	}
	
	@Override
	public void execute() {
		recoverArticulo = catalogo.getArticulo(codigo);
		if (recoverArticulo != null) {
			System.out.print("Eliminado el articulo con codigo: " + codigo + "...");
			catalogo.eliminarArticulo(codigo);
			System.out.println("hecho.");
		} else {
			System.out.println("El articulo con codigo: " + codigo + " NO EXISTE.");
			canUnExecute = false;
		}
	}

	@Override
	public void unExecute() throws OperationNotSupportedException {
		System.out.print("Creando el articulo con codigo: " + codigo + "...");
		catalogo.nuevo(recoverArticulo);
		System.out.println("hecho.");				
	}

	@Override
	public boolean canUnExecute() {		
		return canUnExecute;
	}

}
