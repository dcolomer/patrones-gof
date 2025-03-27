package comportamiento.strategy.ordenacion;

public class Contexto {
	private Estrategia estrategia;

	public void ordenarLista(double[] lista) {
		estrategia.ordenar(lista);
	}

	public Estrategia getEstrategia() {
		return estrategia;
	}

	public void setEstrategia(Estrategia estrategia) {
		this.estrategia = estrategia;
	}
}
