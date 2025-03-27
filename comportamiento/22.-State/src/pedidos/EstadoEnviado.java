package comportamiento.state.pedidos;

public class EstadoEnviado extends AbstractEstado {

	public EstadoEnviado(Pedido pedido) {
		super(pedido);
	}

	@Override
	protected Estados getDescipcionEstado() {
		return Estados.Enviado;
	}

	@Override
	public AbstractEstado perder() {
		return new EstadoPerdido(getPedido());
	}

	@Override
	public AbstractEstado entregar() {
		return new EstadoEntregado(getPedido());
	}

}
