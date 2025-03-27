package comportamiento.state.pedidos;

public class Linea {
	private Articulo articulo;
	private int cantidad;

	public Linea(Articulo articulo, int cantidad) {
		this.articulo = articulo;
		this.cantidad = cantidad;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

}
