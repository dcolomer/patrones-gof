package comportamiento.strategy.ordenacion;

public class EstrategiaQuickSort implements Estrategia {
	public void ordenar(double[] lista) {
		quicksort(lista, 0, lista.length - 1);
	}

	private void quicksort(double[] lista, int left, int right) {
		if (right <= left)
			return;
		int i = partition(lista, left, right);
		quicksort(lista, left, i - 1);
		quicksort(lista, i + 1, right);
	}

	private int partition(double[] lista, int left, int right) {
		int i = left;
		int j = right;
		while (true) {
			while (lista[i] < lista[right])
				i++;
			while (less(lista[right], lista[--j]))
				if (j == left)
					break;
			if (i >= j)
				break;
			exch(lista, i, j);
		}
		exch(lista, i, right);
		return i;
	}

	private boolean less(double x, double y) {
		return (x < y);
	}

	private void exch(double[] lista, int i, int j) {
		double swap = lista[i];
		lista[i] = lista[j];
		lista[j] = swap;
	}
}
