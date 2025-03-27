package comportamiento.observer.improve.event;

public class PropertyPrecioEvent extends AbstractPropertyEvent {
	
	public PropertyPrecioEvent(String nombre, Object valor, Class<?> claseDelValor) {
		super(nombre, valor, claseDelValor);
	}

}
