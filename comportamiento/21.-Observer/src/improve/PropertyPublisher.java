package comportamiento.observer.improve;

import comportamiento.observer.improve.event.PropertyEvent;

public interface PropertyPublisher {
	public void addPropertyListener(PropertyListener listener, Class<? extends PropertyEvent> clase);
	public void removePropertyListener(PropertyListener listener);
	public void publishPropertyEvent(PropertyEvent event);
}
