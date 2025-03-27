package comportamiento.mediator.tienda;

import static comportamiento.mediator.tienda.Accion.*;

import java.util.Scanner;

public class ComprarPag {
	Mediator mediator;
	String respuesta = "n";

	public ComprarPag(Mediator m) {
		mediator = m;
	}

	public void go() {
		System.out.print("¿Comprar ahora el articulo [s/n]? ");
		Scanner scan = new Scanner(System.in);
		respuesta = scan.next();	
		if (respuesta.equals("s")) {
			System.out.println("Gracias por la compra.");
		}
		mediator.handle(comprar_salir);
	}
}
