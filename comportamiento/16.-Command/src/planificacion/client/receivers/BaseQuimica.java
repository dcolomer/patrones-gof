package comportamiento.command.planificacion.client.receivers;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/** Receiver **/
public class BaseQuimica  {
	private float temperatura;	
	private Timer timer;
	private TimerTask timerTask;
	private float valorEnfriamientoAgente;
	
	public static final float TEMPERATURA_NOMINAL = 95.0F;
	public static final float TEMPERATURA_LIMITE = 99.0F;
	public static final long SEGUNDOS_CAMBIO_TEMPERATURA = 4000L;
	
	// MainClient activará la BaseQuimica, por lo que cada 4 segundos cambiará su temperatura
	public void activar() {
		activar(SEGUNDOS_CAMBIO_TEMPERATURA);		
		System.out.println("------Base quimica activada. Variara su temperatura cada " + 
				SEGUNDOS_CAMBIO_TEMPERATURA + " ms.");
	}
		
	// Metodo interno en el que se planifica la obtención de la siguiente temperatura
	private void activar(long tiempoCambioTemp) {
		timer = new Timer();
		timerTask = new TimerTask() {
			@Override
			public void run() {
				temperatura = calcTemperatura();
			}
		};		
		timer.scheduleAtFixedRate(timerTask, 0L, tiempoCambioTemp);				
	}
	
	// Permitir que sea posible desactivar la BaseQuimica
	public void desactivar() {		
		timerTask.cancel();		
		timer.cancel();		
		timer.purge();
		timerTask = null;
		timer = null;
		System.out.println("------Base quimica desactivada");
	}
	
	// Devolver la temperatura
	public float getTemperatura() {	
		return temperatura;	
	}
	
	/*
	 * Metodo que se ejecuta cuando la BaseQuimica alcanza el límite de tolerancia.
	 * El agente externo afecta a la base quimica de dos formas: 
	 *    -Provoca que la BQ no varie de temperatura con tanta frecuencia.
	 *    -Cuando la BQ varíe, lo hará con menores subidas de temperatura
	 */
	public void fusionar(AgenteExterno agente) {		
		/*
		 * Se vuelve a planificar el intervalo del calculo de temperatura, 
		 * asegurando que el intervalo será mayor a 4 segundos
		 */
		reactivar(agente.getSegundosCambiosTemperatura());
		
		/*
		 * Se obtiene un valor aleatorio (entre 1.0-3.0) que interviene
		 * en el cálculo de la temperatura para refrigerarla
		 */
		valorEnfriamientoAgente = agente.getValorEnfriamiento();				
		System.out.println("******Fusion realizada");
	}
	
	private void reactivar(long tiempo) {
		timerTask.cancel();
		activar(tiempo);
		System.out.println("------Base quimica reactivada. Variara ahora su temperatura cada " + tiempo + " ms.");	
	}
	
	private float calcTemperatura() {		
		// nextFloat() devuelve un valor entre 0.0 and 1.0
		// ([0...1] * 99-95) + 95+1-[1...3];
		// ([0...1] * 4) + 96-[1...3];
		// valor más bajo: 0 + 96 - 3 = 93 aprox.
		// valor más alto: 4 + 96 - 1 = 99 aprox., aunque inicialmente, cuando valorEnfriamientoAgente=0, podemos obtener 100				
		float nuevaTemperatura = (new Random().nextFloat() * (TEMPERATURA_LIMITE - TEMPERATURA_NOMINAL)) + 
				TEMPERATURA_NOMINAL + 1.0F - valorEnfriamientoAgente; // valorEnfriamientoAgente inicialmente es 0
		
		valorEnfriamientoAgente -= 0.33F; // el efecto refrigerante del agente tiende a desaparecer 
		
		System.out.println(this.getClass().getSimpleName() + " - temperatura:" + nuevaTemperatura);
		
		return nuevaTemperatura; 		
	}
	
}
