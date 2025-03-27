package comportamiento.command.procesar_peticiones.client;

import java.math.BigDecimal;

import comportamiento.command.procesar_peticiones.client.comandos.EliminarCommand;
import comportamiento.command.procesar_peticiones.client.comandos.MacroCommand;
import comportamiento.command.procesar_peticiones.client.comandos.MostrarCommand;
import comportamiento.command.procesar_peticiones.client.comandos.NuevoCommand;
import comportamiento.command.procesar_peticiones.client.receivers.Articulo;
import comportamiento.command.procesar_peticiones.client.receivers.Catalogo;
import comportamiento.command.procesar_peticiones.frmwrk.ProcesadorPeticiones;

/*
 * Clase creada por el usuario del framework.
 * Demuestra el uso de un macro-comando.
 * Utiliza los métodos de la superclase para las operaciones comunes. Sin
 * embargo crea un método especifico en esta misma clase para concretar 
 * en qué consiste el macro-comando.
 * 
 */
public class MainClient03 extends SuperMain {

	// Creamos un Invoker
	private static ProcesadorPeticiones procesador = new ProcesadorPeticiones();
	
	// Creamos un Receiver
	private static Catalogo catalogo = new Catalogo();
	
	public MainClient03() {
		
		super(procesador, catalogo);
		
		crearArticulo("2000", "Ordenador portatil PALSON-WQE", "610.60");				
		mostrarArticulos();
		
		crearMacroComando();			
	}
	
	private void crearMacroComando() {		
		
		MacroCommand macroCmd = new MacroCommand();
		
		macroCmd.addComando(new EliminarCommand(catalogo, "1000"));
		macroCmd.addComando(new EliminarCommand(catalogo, "1001"));
		
		Articulo articulo = new Articulo("4000", 
				"Disco duro externo 5TB Mantox", new BigDecimal("190.50"));
		
		macroCmd.addComando(new NuevoCommand(catalogo, articulo));
		macroCmd.addComando(new MostrarCommand(catalogo));
		
		procesador.setCmd(macroCmd);
				
		procesador.procesar();		
	}

	public static void main(String[] args) {
		new MainClient03();		
	}	
	
}
