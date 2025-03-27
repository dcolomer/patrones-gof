package comportamiento.command.planificacion.client.tareas;

import comportamiento.command.planificacion.client.receivers.AgenteExterno;
import comportamiento.command.planificacion.client.receivers.BaseQuimica;
import comportamiento.command.planificacion.frmwrk.Tarea;

public class TareaSensorTemperatura implements Tarea {

	BaseQuimica baseQuimica;
	AgenteExterno agente;
	
	public TareaSensorTemperatura(BaseQuimica baseQuimica, AgenteExterno agente) {
		this.baseQuimica = baseQuimica;
		this.agente = agente;
	}
	
	@Override
	public void ejecutar() {
		System.out.print("Sensor temperatura invocando a tarea...");
		float temperatura = baseQuimica.getTemperatura();
		
		if (temperatura >= BaseQuimica.TEMPERATURA_LIMITE) {			
			System.out.println("\n******Base preparada para la fusion...");
			agente.inyectar();
		} else {
			System.out.println("temperatura estable...Ok.");
		}
	}
}

