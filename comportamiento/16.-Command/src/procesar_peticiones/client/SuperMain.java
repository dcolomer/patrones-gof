package comportamiento.command.procesar_peticiones.client;

import java.math.BigDecimal;

import comportamiento.command.procesar_peticiones.client.comandos.EliminarCommand;
import comportamiento.command.procesar_peticiones.client.comandos.MostrarCommand;
import comportamiento.command.procesar_peticiones.client.comandos.NuevoCommand;
import comportamiento.command.procesar_peticiones.client.receivers.Articulo;
import comportamiento.command.procesar_peticiones.client.receivers.Catalogo;
import comportamiento.command.procesar_peticiones.frmwrk.Command;
import comportamiento.command.procesar_peticiones.frmwrk.ProcesadorPeticiones;

/*
 * Clase creada por el usuario del framework.
 * Contiene la funcionalidad común a todas las clases de 'demo'.
 */
public class SuperMain {

	private ProcesadorPeticiones procesador;
	private Catalogo catalogo;

	public SuperMain(ProcesadorPeticiones procesador, Catalogo catalogo) 
	{		
		this.procesador = procesador; 
		this.catalogo = catalogo;
	}
	
	public void crearArticulo(String codigo, 
			String descripcion, String strPrecio) 
	{		
		Articulo articulo = new Articulo(codigo, descripcion,
				new BigDecimal(strPrecio));
		
		NuevoCommand nuevoCmd = 
				new NuevoCommand(catalogo, articulo);
		
		procesador.setCmd(nuevoCmd);		
		procesador.procesar();		
	}

	/*
	 * Crear un objeto MostrarCommand proporcionándole su Receiver (catalogo)
	 * Al invoker (procesador) se le pasa ahora el objeto MostrarCommand y 
	 * se le solicita que procese.  
	 */
	public void mostrarArticulos() {
		MostrarCommand mostrarCmd = new MostrarCommand(catalogo);
		procesador.setCmd(mostrarCmd);		
		procesador.procesar();		
	}

	public void eliminarArticulo(String codigo) {
		EliminarCommand eliminarCmd = 
				new EliminarCommand(catalogo, codigo);
		procesador.setCmd(eliminarCmd);		
		procesador.procesar();		
	}

	public void procesarTodoDeNuevo() {
		procesador.procesarHistorial();		
	}

	public void deshacer(int i) {
		procesador.deshacer(i);		
	}

	public void procesarMacroComando(Command macroComando) {
		procesador.setCmd(macroComando);		
		procesador.procesar();		
	}
}
