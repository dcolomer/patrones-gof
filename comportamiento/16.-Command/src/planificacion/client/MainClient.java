package comportamiento.command.planificacion.client;

import comportamiento.command.planificacion.client.receivers.AgenteExterno;
import comportamiento.command.planificacion.client.receivers.BaseQuimica;
import comportamiento.command.planificacion.client.tareas.TareaSensorAgente;
import comportamiento.command.planificacion.client.tareas.TareaSensorTemperatura;
import comportamiento.command.planificacion.frmwrk.Planificador;
import comportamiento.command.planificacion.frmwrk.Tarea;

public class MainClient {
	
	public static void main(String args[]) {	
		
		/*
		 * Receivers. Son quienes implementan la funcionalidad que 
		 * realmente queremos que se ejecute.
		 */
		BaseQuimica baseQuimica = new BaseQuimica();
		AgenteExterno HX1000D = new AgenteExterno(baseQuimica);	
				
		/*
		 * Creacion de tareas 
		 */		
		Tarea sensorTempBQ = 
				new TareaSensorTemperatura(baseQuimica, HX1000D);
		
		Tarea sensorEfectividadAgente = new TareaSensorAgente(HX1000D);
		
		/*
		 * Creamos un planificador que:
		 * -Cada medio segundo comprobará si hay tareas que deban ejecutarse.
		 * -Como maximo admitirá 100 tareas.
		 */
		Planificador planificador = Planificador.getInstancia();
		planificador.configurar(500, 100);
				
		/*
		 * Añadimos el command (la tarea) al planificador.
		 * Indicamos que debe arrancarse lo antes posible y repetirse cada 3 segundos.
		 */
		planificador.addTarea(sensorTempBQ, 0, 3000);
		
		/*
		 * Añadimos el command (la tarea) al planificador.
		 * Indicamos que debe arrancarse a los 10 segundos y repetirse cada 8 segundos.
		 */
		planificador.addTarea(sensorEfectividadAgente, 10000, 8000);
		
		/*
		 * Activamos la base quimica
		 */
		baseQuimica.activar();
		
		/*
		 * Iniciamos el planificador
		 */
		planificador.iniciar();
	}
}
