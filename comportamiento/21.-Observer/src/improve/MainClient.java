package comportamiento.observer.improve;

import comportamiento.observer.improve.event.AllPropertiesEvent;
import comportamiento.observer.improve.event.PropertyDescripcionEvent;
import comportamiento.observer.improve.event.PropertyPrecioEvent;

public class MainClient {

	public static void main(String[] args) {

		Subject subject = new Subject("Caja roja", 5.50F);
		
		PropertyListener listenerPrecio = new PrecioObserver();				
		subject.addPropertyListener(listenerPrecio, PropertyPrecioEvent.class);
		
		PropertyListener listenerDescripcion = new DescripcionObserver();
		subject.addPropertyListener(listenerDescripcion, PropertyDescripcionEvent.class);
		
		PropertyListener fullListener = new FullObserver();
		subject.addPropertyListener(fullListener, AllPropertiesEvent.class);
		
		subject.setDescripcion("Caja negra");
		subject.setPrecio(6.00F);
	}

}

