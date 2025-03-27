package comportamiento.memento.sin_clase_interna;

public class Memento {
	private String snapshot;

	Memento(String words) {
		snapshot = new String(words);
	}

	String getSnapshot() {
		return snapshot;
	}
}
