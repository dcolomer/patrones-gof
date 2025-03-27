package comportamiento.command.procesar_peticiones.client.comandos;

import javax.naming.OperationNotSupportedException;

import comportamiento.command.procesar_peticiones.client.receivers.Articulo;
import comportamiento.command.procesar_peticiones.client.receivers.Catalogo;
import comportamiento.command.procesar_peticiones.frmwrk.Command;


/*
 * Clase creada por el usuario del framework
 */
public class NuevoCommand implements Command {
	
	private Catalogo catalogo;
	private Articulo articulo;
	private String codigo;
	private boolean canUnExecute = true;
	
	public NuevoCommand(Catalogo catalogo, Articulo articulo) {
		this.catalogo = catalogo;
		this.articulo = articulo;
	}
	
	@Override
	public void execute() {
		codigo = articulo.getCodigo();
		Articulo recoverArticulo = catalogo.getArticulo(codigo);
		if (recoverArticulo == null) { // No exisite, se puede crear
			System.out.print("Creando el articulo con codigo: " + codigo + "...");
			catalogo.nuevo(articulo);
			System.out.println("hecho.");
		} else {
			System.out.println("El articulo con codigo: " + codigo + " YA EXISTE.");
			canUnExecute = false;
		}
	}

	@Override
	public void unExecute() throws OperationNotSupportedException {
		System.out.print("Eliminado el articulo con codigo: " + codigo + "...");
		catalogo.eliminarArticulo(codigo);
		System.out.println("hecho.");		
	}

	@Override
	public boolean canUnExecute() {
		return canUnExecute;
	}

}
