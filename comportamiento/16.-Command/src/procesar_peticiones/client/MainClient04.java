package comportamiento.command.procesar_peticiones.client;

import java.math.BigDecimal;

import comportamiento.command.procesar_peticiones.client.comandos.EliminarCommand;
import comportamiento.command.procesar_peticiones.client.comandos.MacroCommand;
import comportamiento.command.procesar_peticiones.client.comandos.MostrarCommand;
import comportamiento.command.procesar_peticiones.client.comandos.NuevoCommand;
import comportamiento.command.procesar_peticiones.client.receivers.Articulo;
import comportamiento.command.procesar_peticiones.client.receivers.Catalogo;
import comportamiento.command.procesar_peticiones.frmwrk.Command;
import comportamiento.command.procesar_peticiones.frmwrk.ProcesadorPeticiones;

/*
 * Clase creada por el usuario del framework.
 * Clase cliente que demuestra el uso de deshacer operaciones ya ejecutadas
 * 
 */
public class MainClient04 extends SuperMain {

	public MainClient04() {
		
		super(new ProcesadorPeticiones(), new Catalogo());
		
		crearArticulo("2000", "Ordenador portatil PALSON-WQE", "610.60");				
		crearArticulo("3000", "Ordenador sobr emesa FFFR", "920.00");
		mostrarArticulos();		
		procesarMacroComando(crearMacroComando());		
		
		/*
		 * Deshacemos las 2 ultimas operaciones.
		 * Un macro-comando se considera una única operación, independientemente
		 * del numero de comandos que lo compongan.
		 */
		deshacer(2);				
	}
	
	private Command crearMacroComando() {
		Catalogo catalogo = new Catalogo();
		MacroCommand macroCmd = new MacroCommand();
		
		macroCmd.addComando(new EliminarCommand(catalogo, "1000"));
		macroCmd.addComando(new EliminarCommand(catalogo, "1001"));
		
		Articulo articulo = new Articulo("4000", 
				"Disco duro externo 5TB Mantox", new BigDecimal("190.50"));
		
		macroCmd.addComando(new NuevoCommand(catalogo, articulo));
		macroCmd.addComando(new MostrarCommand(catalogo));
		
		return macroCmd;			
	}

	public static void main(String[] args) {
		new MainClient04();		
	}	
	
}
