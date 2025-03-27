package comportamiento.iterator.privado.client;

import comportamiento.iterator.privado.Iterador;
import comportamiento.iterator.privado.Secuencia;

public class MainClient {

	public static void main(String[] args) {
		Secuencia secuencia = new Secuencia(10);

		secuencia.add("Paco");
		secuencia.add(6);

		Iterador iterador = secuencia.iterador();
		while (!iterador.end()) {
			System.out.println(iterador.current() + " ");
			iterador.next();
		}
	}

}
