package comportamiento.state.pedidos;

public class EstadoCancelado extends AbstractEstado {

	public EstadoCancelado(Pedido pedido) {
		super(pedido);
	}

	@Override
	protected Estados getDescipcionEstado() {
		return Estados.Cancelado;
	}

	public boolean isFinalizado() {
		return true;
	}
}
