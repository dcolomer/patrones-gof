package comportamiento.state.interruptor;

/*
 * Singleton
 */
public class EstadoOn implements Estado {

	private static EstadoOn estadoOn;
	
	private EstadoOn() {		
	}
	
	public static Estado getInstance() {
		if (estadoOn == null) {
			estadoOn = new EstadoOn();
		}
		System.out.println("On");
		return estadoOn;		
	}
	
    @Override
    public void conmutar(Interruptor interruptor) {                
        // Transicion al siguiente estado.
        // Actualizar el estado del objeto de contexto
        interruptor.setEstado(EstadoOff.getInstance());
    }

}
