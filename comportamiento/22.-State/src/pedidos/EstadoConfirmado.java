package comportamiento.state.pedidos;

public class EstadoConfirmado extends AbstractEstado {

	public EstadoConfirmado(Pedido pedido) {
		super(pedido);
	}

	@Override
	protected Estados getDescipcionEstado() {
		return Estados.Confirmado;
	}

	@Override
	public AbstractEstado cancelar() {
		return new EstadoCancelado(getPedido());
	}

	@Override
	public AbstractEstado enviar(String direccion) {
		if (!getPedido().isPagado()) {
			throw new IllegalStateException("Error: No se puede enviar un pedido que aun no se ha pagado.");
		}
		return new EstadoEnviado(getPedido());
	}

}
