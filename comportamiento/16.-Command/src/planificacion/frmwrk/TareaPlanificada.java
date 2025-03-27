package comportamiento.command.planificacion.frmwrk;

/*
 * Wrapper (envoltorio) para las tareas.
 * Una tarea planificada es una tarea con los siguientes atributos:
 * -La frecuencia de repeticion
 * -El instante de su última ejecucion
 */
public class TareaPlanificada {
	
	private Tarea tarea; // La tarea a realizar (el objeto Command
	private long tFrecuencia; // Frecuencia de repeticion de la tarea
	private long tInicio; // tiempo que debe transcurrir para la primera ejecucion de la tarea
	private long tUltimaEjecucion; // Instante de la ultima ejecucion

	public TareaPlanificada(Tarea tarea, long tIni, long frecuencia) {
		this.tarea = tarea;
		this.tFrecuencia = frecuencia;
		this.tInicio = tIni + System.currentTimeMillis();
		this.tUltimaEjecucion = System.currentTimeMillis();
	}
	
	public Tarea getTarea() { return tarea; }
	public long getIntervaloRepeticion() { return tFrecuencia; }
	public long getTiempoUltimaEjecucion() { return tUltimaEjecucion; }
	public long getStart() { return tInicio; }
	
	public void setTiempoUltimaEjecucion(long tUltimaEjecucion) {
		this.tUltimaEjecucion = tUltimaEjecucion;
	}	
}

