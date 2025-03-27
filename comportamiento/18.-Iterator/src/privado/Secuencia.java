package comportamiento.iterator.privado;

public class Secuencia {
	private Object[] items;
	private int next = 0;

	public Secuencia(int size) {
		items = new Object[size];
	}

	public void add(Object x) {
		if (next < items.length)
			items[next++] = x;
	}

	private class IteradorImpl implements Iterador {
		private int i = 0;

		public boolean end() {
			return i == items.length;
		}

		public void next() {
			if (i < items.length)
				i++;
		}

		public Object current() {
			return items[i];
		}
	}

	public Iterador iterador() {
		return new IteradorImpl();
	}
}
