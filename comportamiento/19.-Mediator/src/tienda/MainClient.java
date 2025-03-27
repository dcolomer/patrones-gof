package comportamiento.mediator.tienda;

public class MainClient {
	public static void main(String args[]) {
		new MainClient();
	}

	public MainClient() {
		Mediator mediator = new Mediator();
		mediator.getBienvenida().go();
	}
}
