package comportamiento.state.pedidos;

public class EstadoAbierto extends AbstractEstado {

	public EstadoAbierto(Pedido pedido) {
		super(pedido);
	}

	@Override
	protected Estados getDescipcionEstado() {
		return Estados.Abierto;
	}

	@Override
	public AbstractEstado addArticulo(Articulo item, int cantidad) {
		getPedido().getLineas().add(new Linea(item, cantidad));
		return this;
	}

	@Override
	public AbstractEstado confirmar() {
		if (getPedido().getLineas().isEmpty()) {
			throw new IllegalStateException("Error: Se requiere que exista al menos una linea para poder confirmar el pedido.");
		} else if (getPedido().getDireccion() == null) {
			throw new IllegalStateException("Error: Se requiere una direccion para poder confirmar el pedido.");
		}
		return new EstadoConfirmado(getPedido());
	}

	@Override
	public AbstractEstado cancelar() {
		return new EstadoCancelado(getPedido());
	}

}
