package comportamiento.mediator.tienda;

import static comportamiento.mediator.tienda.Accion.*;

import java.util.Scanner;

public class BienvenidaPag {
	Mediator mediator;
	String respuesta = "n";

	public BienvenidaPag(Mediator m) {
		mediator = m;
	}

	public void go() {
		System.out.print("¿Desea ver el catalogo de productos [s/n]? ");
		Scanner scan = new Scanner(System.in);
		respuesta = scan.next();	
		if (respuesta.equals("s")) {
			mediator.handle(bienvenida_catalogo);
		} else {
			mediator.handle(bienvenida_salir);
		}
	}
}
