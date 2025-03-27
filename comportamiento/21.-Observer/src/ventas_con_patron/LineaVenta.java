package comportamiento.observer.ventas_con_patron;

public class LineaVenta {
	
	private Articulo articulo;
	private int cantidad;
	private float totalLinea;
	
	public LineaVenta(Articulo articulo, int cantidad) {		
		this.articulo = articulo;
		this.cantidad = cantidad;
		this.totalLinea = articulo.getPrecio() * cantidad;
	}
	
	public Articulo getArticulo() {
		return articulo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public float getTotalLinea() {
		return totalLinea;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((articulo == null) ? 0 : articulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineaVenta other = (LineaVenta) obj;
		if (articulo == null) {
			if (other.articulo != null)
				return false;
		} else if (!articulo.equals(other.articulo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LineaVenta [articulo=" + articulo + ", cantidad=" + cantidad
				+ ", totalLinea=" + totalLinea + "]";
	}
	
}
