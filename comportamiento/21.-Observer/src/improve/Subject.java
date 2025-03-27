package comportamiento.observer.improve;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import comportamiento.observer.improve.event.AllPropertiesEvent;
import comportamiento.observer.improve.event.PropertyDescripcionEvent;
import comportamiento.observer.improve.event.PropertyEvent;
import comportamiento.observer.improve.event.PropertyPrecioEvent;

public class Subject implements PropertyPublisher {

	String descripcion;
	Float precio;
	
	private Map<PropertyListener, Class<? extends PropertyEvent>> registerListeners;
	
	public Subject(String descripcion, float precio) {
		registerListeners =
				new HashMap<PropertyListener, Class<? extends PropertyEvent>>();
		this.descripcion = descripcion;
		this.precio = precio;
		System.out.println(this.getClass());
		System.out.println("Descripcion: " + descripcion);
		System.out.println("Precio: " + precio);
		System.out.println();
	}
	
	@Override
	public void addPropertyListener(PropertyListener listener,
			Class<? extends PropertyEvent> clase) {
		
		registerListeners.put(listener, clase);		
	}
	
	@Override
	public void removePropertyListener(PropertyListener listener) {
		registerListeners.remove(listener);		
	}

	@Override
	public void publishPropertyEvent(PropertyEvent event) {
		for (Entry<PropertyListener, Class<? extends PropertyEvent>> listener : 
			registerListeners.entrySet()) 
		{			
			Class<? extends PropertyEvent> currentEventClass = listener.getValue(); 
			if (currentEventClass.equals(event.getClass())) {
				listener.getKey().onPropertyEvent(event);
			}
			
			if (currentEventClass.equals(AllPropertiesEvent.class)) {
				listener.getKey().onPropertyEvent(event);
			}			
		}		
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		PropertyEvent event = 
				new PropertyDescripcionEvent("descripcion", descripcion, String.class); 
		publishPropertyEvent(event);				
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;		
		PropertyEvent event = 
				new PropertyPrecioEvent("precio", precio, Float.class); 
		publishPropertyEvent(event);
	}

}
