package comportamiento.state.interruptor;

/*
 * Singleton
 */
public class EstadoOff implements Estado {

	private static EstadoOff estadoOff;
	
	private EstadoOff() {		
	}
	
	public static Estado getInstance() {
		if (estadoOff == null) {
			estadoOff = new EstadoOff();
		}
		System.out.println("Off");
		return estadoOff;		
	}
	
    @Override
    public void conmutar(Interruptor interruptor) {
    	// Transicion al siguiente estado.
        // Actualizar el estado del objeto de contexto
        interruptor.setEstado(EstadoOn.getInstance());    	       
    }

}
