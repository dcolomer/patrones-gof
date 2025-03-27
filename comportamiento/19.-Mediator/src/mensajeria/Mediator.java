package comportamiento.mediator.mensajeria;

public interface Mediator {

    void register(Colleague colleague);
    void send(String sender, String receiver, String message);
	void broadcast(String sender, String message);

}