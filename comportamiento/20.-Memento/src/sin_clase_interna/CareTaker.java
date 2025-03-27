package comportamiento.memento.sin_clase_interna;

import java.util.LinkedList;

public class CareTaker {
	private Originator originator = null;
	private LinkedList<Memento> mementos = new LinkedList<Memento>();

	public CareTaker(Originator originator) {
		this.originator = originator;
	}

	public void save() {
		Memento memento = originator.createMemento();
		mementos.add(memento);
	}

	public void putBack(int index) {
		if (index >= mementos.size()) {
			return;
		}	
		Memento memento = (Memento) mementos.get(index);
		originator.setMemento(memento);
	}
}
