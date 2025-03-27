package comportamiento.mediator.tienda;

import static comportamiento.mediator.tienda.Accion.*;

import java.util.Scanner;

public class CatalogoPag {
	Mediator mediator;
	String respuesta = "n";

	public CatalogoPag(Mediator m) {
		mediator = m;
	}

	public void go() {
		System.out.print("¿Listo para comprar [s/n]? ");
		Scanner scan = new Scanner(System.in);
		respuesta = scan.next();		
		if (respuesta.equals("s")) {
			mediator.handle(catalogo_comprar);
		} else {
			mediator.handle(catalogo_salir);
		}
	}
}
