package comportamiento.observer.ventas_con_patron;

public interface PropertyPublisher {
	public void addPropertyListener(PropertyListener listener);
	public void removePropertyListener(PropertyListener listener);
	public void publishPropertyEvent(String nombre, float valor);
}
