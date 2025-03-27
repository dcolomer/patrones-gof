package comportamiento.memento.sin_clase_interna;

public class Originator {
	private String words = "";

	Memento createMemento() {
		return new Memento(words);
	}

	void setMemento(Memento memento) {
		words = memento.getSnapshot();
	}

	public void write(String words) {
		this.words += words;
	}

	public void print() {
		System.out.println("*****************************");
		System.out.println(words);
	}
}
