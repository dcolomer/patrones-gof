package comportamiento.command.planificacion.client.tareas;

import comportamiento.command.planificacion.client.receivers.AgenteExterno;
import comportamiento.command.planificacion.frmwrk.Planificador;
import comportamiento.command.planificacion.frmwrk.Tarea;

public class TareaSensorAgente implements Tarea {

	AgenteExterno agente;
	
	public TareaSensorAgente(AgenteExterno agente) {
		this.agente = agente;
	}
	
	@Override
	public void ejecutar() {
		System.out.print("Sensor efectividad invocando a tarea...");
		if (!agente.esEfectivo()) { 
			System.out.println("El agente externo ya no es efectivo! Finalizando el programa según el protocolo de seguridad...");
			
			Planificador.getInstancia().detener();
			
			agente.getBaseQuimica().desactivar();				
			
			System.out.println("Programa finalizado.");
			System.exit(0);
		}
	}

}
