package comportamiento.mediator.tienda;

public class SalirPag {
	Mediator mediator;

	public SalirPag(Mediator m) {
		mediator = m;
	}

	public void go() {
		System.out.println("Vuelva pronto a visitarnos!");
	}
}
