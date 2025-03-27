package comportamiento.mediator.tienda;

import static comportamiento.mediator.tienda.Accion.*;

/*
 * Clase que conecta el flujo entre páginas.
 * Crea las cuatro páginas, pasando en el constructor de cada 
 * una la referencia de si mismo. De esta manera cada página
 * podrá acceder a él.
 */

public class Mediator {
	BienvenidaPag bienvenidaPag;
	CatalogoPag catalogoPag;
	ComprarPag comprarPag;
	SalirPag salirPag;

	public Mediator() {
		bienvenidaPag = new BienvenidaPag(this);
		catalogoPag = new CatalogoPag(this);
		comprarPag = new ComprarPag(this);
		salirPag = new SalirPag(this);
	}

	public void handle(Accion estado) {
		if (estado.equals(bienvenida_catalogo)) {
			catalogoPag.go();
		} else if (estado.equals(catalogo_comprar)) {
			comprarPag.go();
		} else if (estado.equals(comprar_salir)) {
			salirPag.go();
		} else if (estado.equals(bienvenida_salir)) {
			salirPag.go();
		} else if (estado.equals(catalogo_salir)) {
			salirPag.go();
		} else if (estado.equals(comprar_salir)) {
			salirPag.go();
		}
	}

	public BienvenidaPag getBienvenida() {
		return bienvenidaPag;
	}
}
