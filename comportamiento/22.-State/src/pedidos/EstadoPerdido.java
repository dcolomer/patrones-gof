package comportamiento.state.pedidos;

public class EstadoPerdido extends AbstractEstado {

	public EstadoPerdido(Pedido pedido) {
		super(pedido);
	}

	@Override
	protected Estados getDescipcionEstado() {
		return Estados.Perdido;
	}

	@Override
	public AbstractEstado cancelar() {
		return new EstadoCancelado(getPedido());
	}

	@Override
	public AbstractEstado encontrar() {
		return new EstadoEnviado(getPedido());
	}

}
