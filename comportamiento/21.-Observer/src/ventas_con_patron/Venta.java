package comportamiento.observer.ventas_con_patron;

import java.util.HashSet;
import java.util.Set;

public class Venta {

	private Set<LineaVenta> lineas;

	private float totalVenta;

	public void setTotalVenta(float total) {
		this.totalVenta = total;
	}

	public Venta() {
		lineas = new HashSet<LineaVenta>();
	}

	public float getTotalVenta() {
		return totalVenta;
	}

	public Set<LineaVenta> getLineas() {
		return lineas;
	}

}
