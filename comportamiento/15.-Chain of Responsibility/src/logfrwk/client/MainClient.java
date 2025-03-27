package comportamiento.CoR.logfrwk.client;

import comportamiento.CoR.logfrwk.wrapper.Logger;
import static comportamiento.CoR.logfrwk.core.CtesLog.*;

public class MainClient {
	
	private static final Logger log = new Logger(MainClient.class);
					
	private Calculo calculo = new Calculo();
	
	public MainClient() {		
		/*
		 * Si no establecemos ninguna configuracion el framework de log 
		 * adopta una por defecto, consistente en mostrar mensajes por 
		 * pantalla a partir del menor nivel de log (TRAZA)
		 */
		//ejemplo1ConfigurarLog();
		//ejemplo2ConfigurarLog();
		//ejemplo3ConfigurarLog();

		log.traza("llamando a metodoSuma()");				
		calculo.sumar(Integer.MAX_VALUE, Integer.MAX_VALUE);
		
		log.traza("llamando a metodoResta()");
		calculo.restar(2.00, 1.10);
		
		log.traza("llamando a metodoBucle()");
		metodoBucle();
	}
		
	public static void main(String[] args) {		
		new MainClient();				
	}		

	private void metodoBucle() {		
		// Idem a double i = 1.0 / 0.0;
		double infinito = Double.POSITIVE_INFINITY;
		
		while (infinito + 1 == infinito + 2) {
			log.aviso("Imposible que se muestre esto, ¿o no?");
			break;
		}		
	}
	
	/*
	 * En este ejemplo se genera un fichero de log ademas de mostrar 
	 * los mensajes en pantalla
	 */
	private void ejemplo1ConfigurarLog() {
		
		/*
		 * -primer param: nivel de log a partir del cual hay que mostrar mensajes
		 * -segundo param: sólo fichero o fichero y pantalla		
		 */
		log.configurar(TRAZA, PANTALLA_Y_FICHERO);
		
		// Las dos siguientes líneas son equivales a la anterior:
		//log.setMinNivel(TRAZA);		
		//log.setSalida(PANTALLA_Y_FICHERO);		
		
	}
	
	/*
	 * En este ejemplo sólo se muestran los mensajes por pantalla y
	 * con un nivel de log mayor o igual a AVISO
	 */
	private void ejemplo2ConfigurarLog() {
		log.setMinNivel(AVISO);
		
	}
	
	/*
	 * En este ejemplo se desactiva todo el sistema de log, por lo
	 * que no se mostrará ningún mensaje
	 */
	private void ejemplo3ConfigurarLog() {
		log.desactivar();
		// Si queremos activar los logs
		// log.activar();
	}	
		
}
