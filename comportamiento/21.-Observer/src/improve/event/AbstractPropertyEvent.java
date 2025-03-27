package comportamiento.observer.improve.event;

public abstract class AbstractPropertyEvent implements PropertyEvent{
	
	private String nombre;
	private Object valor;
	private Class<?> claseDelValor;
	
	protected AbstractPropertyEvent(String nombre, Object valor, Class<?> claseDelValor) {		
		this.nombre = nombre;
		this.valor = valor;
		this.claseDelValor = claseDelValor;
	}

	@Override
	public String getNombre() { return nombre;	}
	
	@Override
	public Object getValor() {	return valor; }
	
	@Override
	public Class<?> getClassValor() { return claseDelValor; }
		
}
