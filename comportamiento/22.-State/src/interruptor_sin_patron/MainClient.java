package comportamiento.state.interruptor_sin_patron;

public class MainClient {
	public static void main(String args[]) {
		Interruptor interruptor = 
				new Interruptor();
		
		interruptor.conmutar();
		interruptor.conmutar();
		interruptor.conmutar();
	}
}
