package comportamiento.CoR.basico.variante.client;


import java.util.Random;

import comportamiento.CoR.basico.ConcreteHandlerCero;
import comportamiento.CoR.basico.ConcreteHandlerNegativos;
import comportamiento.CoR.basico.ConcreteHandlerPositivos;
import comportamiento.CoR.basico.Handler;
import comportamiento.CoR.basico.Request;

public class MainClient {
	public static void main(String[] args) {
		// Montar la cadena de objetos manejadores
		Handler h1 = new ConcreteHandlerNegativos();
		Handler h2 = new ConcreteHandlerPositivos();
		Handler h3 = new ConcreteHandlerCero();
		h1.setSucesor(h2);
		h2.setSucesor(h3);

		// Enviar peticiones a la cadena
		for (int i = 0; i < 5; i++) {
			h1.handleRequest(new Request(getIntAleatorio()));
		}
	}
	
	private static int getIntAleatorio() {
		int num = new Random().nextInt(10);
		if (num != 0 && getSignoNegativoAleatorio()) {
			num = -num;
		}
		return num;
	}

	private static boolean getSignoNegativoAleatorio() {		
		return new Random().nextBoolean();
	}
}
