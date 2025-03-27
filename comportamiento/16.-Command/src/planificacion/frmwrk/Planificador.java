package comportamiento.command.planificacion.frmwrk;

import java.util.Vector;

public class Planificador {
	
	private static Planificador INSTANCIA = new Planificador();
	
	// Cada cuanto se debe comprobar si hay tareas que deben ejecutarse
	private long tiempoInactividad = 5000; 
	
	private Vector<TareaPlanificada> listaTareasPlanificadas;
	
	private boolean continuar = true;
		
	private Planificador() { // clase no instanciable
		
	}
	
	public static Planificador getInstancia() {
		return INSTANCIA;
	}
	
	public void configurar(long tInactividad, int maxNumTareas) {
		tiempoInactividad = tInactividad;
		listaTareasPlanificadas = new Vector<TareaPlanificada>(maxNumTareas);		
	}
	
	public void addTarea(Tarea tarea, long tInicial, long frecuencia) {
		long intervaloRep = (frecuencia > 0) ? frecuencia : 0;
		TareaPlanificada tareaPlan = new TareaPlanificada(tarea, tInicial, intervaloRep);
		listaTareasPlanificadas.add(tareaPlan);
		System.out.println("Tarea " + tarea.getClass().getSimpleName() + 
				" añadida al planificador. Programada para ejecutarse cada " + 
				frecuencia + " ms.");
	}
	
	// Iniciar el thread
	public void iniciar() {
		new Thread() {
			@Override
			public void run() {
				System.out.println("=====================");
				System.out.println("Planificador iniciado");
				System.out.println("=====================");
				while (continuar) {
					try {
						sleep(tiempoInactividad);
						long presente = System.currentTimeMillis();
						 
						for (TareaPlanificada tareaPlan : listaTareasPlanificadas) {
							if (presente > tareaPlan.getStart()) {								
								long tiempoTranscurrido = 
										tareaPlan.getIntervaloRepeticion() + tareaPlan.getTiempoUltimaEjecucion();
								if (presente > tiempoTranscurrido) {
									tareaPlan.getTarea().ejecutar();
									tareaPlan.setTiempoUltimaEjecucion(presente);								
								}
							}
						}								
					} catch (Exception e) {
						System.out.println("El tiempo de inactividad del planificador ha sido interrumpido de manera inesperada: " + e);
					}
				}
				System.out.println("=====================");
				System.out.println("Planificador detenido");
				System.out.println("=====================");
			}			
		}.start(); 
	}
	
	public Vector<TareaPlanificada> getTareas() {
		return listaTareasPlanificadas;
	}
	
	public long getTiempoInactividad() {
		return tiempoInactividad;
	}
	
	public void setTiempoInactividad(long tInactividad) {
		tiempoInactividad = tInactividad;
	}

	public void detener() {
		continuar = false;
		System.out.println("------Planificador detenido");
	}		
}