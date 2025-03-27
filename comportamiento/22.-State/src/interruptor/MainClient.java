package comportamiento.state.interruptor;

public class MainClient {
	public static void main(String args[]) {
		Interruptor interruptor = 
				new Interruptor(EstadoOff.getInstance());
		
		interruptor.conmutar();
		interruptor.conmutar();
		interruptor.conmutar();
	}
}
