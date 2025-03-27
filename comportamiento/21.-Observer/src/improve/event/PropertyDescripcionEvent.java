package comportamiento.observer.improve.event;

public class PropertyDescripcionEvent extends AbstractPropertyEvent {
	
	public PropertyDescripcionEvent(String nombre, Object valor, Class<?> claseDelValor) {
		super(nombre, valor, claseDelValor);
	}

}
