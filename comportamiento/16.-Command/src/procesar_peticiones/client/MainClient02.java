package comportamiento.command.procesar_peticiones.client;

import comportamiento.command.procesar_peticiones.client.receivers.Catalogo;
import comportamiento.command.procesar_peticiones.frmwrk.ProcesadorPeticiones;

/*
 * Clase creada por el usuario del framework.
 * Clase cliente que demuestra el uso del historial de comandos ejecutados
 * 
 */
public class MainClient02 extends SuperMain {

	public MainClient02() {
		
		super(new ProcesadorPeticiones(), new Catalogo());	
		
		crearArticulo("2000", "Ordenador portatil PALSON-WQE", "610.60");				
		mostrarArticulos();
		eliminarArticulo("1000");		
		mostrarArticulos();
		procesarTodoDeNuevo();		
	}
	
	public static void main(String[] args) {
		new MainClient02();		
	}	
	
}
