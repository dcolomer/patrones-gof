package comportamiento.state.pedidos;

public abstract class AbstractEstado {

	private static final String 
		PRE = "No se puede ",
		SUF = "un pedido cuyo estado es ", 
		ERR_AGREGAR = PRE + "agregar lineas a " + SUF,
		ERR_PAGAR = PRE + "pagar " + SUF, 
		ERR_CONFIRMAR = PRE + "confirmar " + SUF, 
		ERR_CANCELAR = PRE + "cancelar " + SUF, 
		ERR_ENVIAR = PRE + "enviar " + SUF, 
		ERR_PERDER = PRE + "pasar a estado PERDIDO " + SUF,
		ERR_ENCONTRAR = PRE + "encontrar " + SUF,
		ERR_ENTREGAR = PRE + "entregar " + SUF;
	
	private Pedido pedido;

	public AbstractEstado(Pedido pedido) {		
		this.pedido = pedido;
	}

	protected abstract Estados getDescipcionEstado();
	
	public Pedido getPedido() {
		return pedido;
	}

	public AbstractEstado addArticulo(Articulo articulo, int cantidad) {
		throw new IllegalStateException(ERR_AGREGAR + getDescipcionEstado());
	}

	public AbstractEstado pagar() {
		throw new IllegalStateException(ERR_PAGAR + getDescipcionEstado());
	}
	
	public AbstractEstado confirmar() {
		throw new IllegalStateException(ERR_CONFIRMAR + getDescipcionEstado());
	}

	public AbstractEstado cancelar() {
		throw new IllegalStateException(ERR_CANCELAR + getDescipcionEstado());
	}

	public AbstractEstado enviar(String direccion) {
		throw new IllegalStateException(ERR_ENVIAR + getDescipcionEstado());
	}

	public AbstractEstado perder() {
		throw new IllegalStateException(ERR_PERDER + getDescipcionEstado());
	}

	public AbstractEstado encontrar() {
		throw new IllegalStateException(ERR_ENCONTRAR + getDescipcionEstado());
	}

	public AbstractEstado entregar() {
		throw new IllegalStateException(ERR_ENTREGAR + getDescipcionEstado());
	}

	public boolean isFinalizado() {
		return false;
	}
}

