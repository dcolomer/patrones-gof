package comportamiento.mediator.mensajeria;

public class Colleague {

	protected Mediator mediator;
	private String name;

	protected Colleague(Mediator mediator, String name) {
		this.mediator = mediator;
		this.name = name;
		mediator.register(this);
	}

	public String getName() {
		return name;
	}

	public void send(String receiver, String message) {
		mediator.send(name, receiver, message);
	}

	public void broadcast(String sender, String message) {
		mediator.broadcast(sender, message);
	}
}
