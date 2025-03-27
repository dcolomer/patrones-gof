package comportamiento.memento.clase_interna;

public class MainClient {

	public static void main(String[] args) {
		Originator originator = new Originator();
		Caretaker caretaker = new Caretaker(originator);
		originator.setState(1);
		caretaker.save();
		originator.setState(2);
		caretaker.save();
		originator.setState(3);
		caretaker.restore();
		caretaker.restore();
		System.out.println(originator.getState()); // should print 1
	}
}
