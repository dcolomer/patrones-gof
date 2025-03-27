package comportamiento.observer.ventas_con_patron;

public interface PropertyListener {
	public void onPropertyEvent(VentaManager mng, String propiedad, float valor);
}
