package comportamiento.mediator.mensajeria;

public class MainClient {

	public static void main(String[] args) {		
		Mediator mediator = new MediatorImpl();
		Colleague userOne = new Colleague(mediator, "Alicia");
		Colleague userTwo = new Colleague(mediator, "Robert");
		userOne.send("Robert", "Esta tarde tenemos reunion a las 17:00");
		userTwo.send("Alicia", "Pues no me va muy bien.");
		
		userOne.broadcast("Alicia", "Ya tengo vacaciones!!!");
	}

}
