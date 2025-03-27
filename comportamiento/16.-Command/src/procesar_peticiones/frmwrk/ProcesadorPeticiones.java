package comportamiento.command.procesar_peticiones.frmwrk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.OperationNotSupportedException;

/*
 * Invoker
 * Clase del framework
 */
public class ProcesadorPeticiones {
	private List<Command> historial = new ArrayList<Command>();
	private Command cmd;
		
	public void procesar() {		
		cmd.execute();	
		historial.add(cmd);
	}

	public void setCmd(Command cmd) {
		this.cmd = cmd;		
	}
	
	public void procesarHistorial()  {
		System.out.println("**********************************************************");
		System.out.println("EJECUTANDO HISTORIAL...");
		System.out.println("**********************************************************");
		for (Command cmd : historial) {
			System.out.println(">>>Ejecutando comando: " + cmd.getClass().getSimpleName());
			cmd.execute();
		}
	}
	
	public void deshacer() {
		deshacer(1);
	}
	
	public void deshacer(int niveles) {
		
		if (niveles < 1 || niveles > 10) {
			throw new RuntimeException("El numero de niveles tiene que estar entre 1 y 10.");
		}
		
		System.out.println("**********************************************************");
		System.out.println("DESHACIENDO LAS ULTIMAS (" + niveles + ") OPERACIONES...");
		System.out.println("**********************************************************");
		if (!historial.isEmpty()) {			
			int des_de = historial.size() - niveles;
			int hasta = historial.size();
			
			List<Command> listaDeComandosBorrable = 
					historial.subList(des_de , hasta);
			
			Collections.reverse(listaDeComandosBorrable);
			
			for (Command cmd : listaDeComandosBorrable ) {
				/*
				 * Un comando puede que no pueda deshacerse de manera circunstancial, 
				 * por ejemplo, se intento crear un nuevo articulo pero ya existia, 
				 * por lo que no hay nada que deshacer.  
				 */
				if (cmd.canUnExecute()) { 
					try {
						cmd.unExecute();					
					} catch (OperationNotSupportedException e) {
						/*
						 * Si se lanza esta excepcion es porque se ha intentado ejecutar un comando  
						 * cuya naturaleza le impide deshacerse, como sería el caso de MostrarCmd, ya
						 * que si ya se ha mostrado algo ¿cómo los desmostramos? 
						 */
						e.printStackTrace();
					}				
				} else {
					System.out.println("El comando " + cmd.getClass().getSimpleName() + " no se puede revertir.");					
				}	
			}			
		} else {
			System.out.println("Nada para deshacer. El historial esta vacio.");
		}
	}

}
