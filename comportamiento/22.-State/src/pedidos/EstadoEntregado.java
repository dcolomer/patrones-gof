package comportamiento.state.pedidos;

public class EstadoEntregado extends AbstractEstado {

	public EstadoEntregado(Pedido pedido) {
		super(pedido);
	}

	@Override
	protected Estados getDescipcionEstado() {
		return Estados.Entregado;
	}

	public boolean isFinalizado() {
		return true;
	}
}
