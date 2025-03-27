package comportamiento.observer.improve.event;

public class AllPropertiesEvent extends AbstractPropertyEvent {
	
	public AllPropertiesEvent(String nombre, Object valor, Class<?> claseDelValor) {
		super(nombre, valor, claseDelValor);
	}

}
