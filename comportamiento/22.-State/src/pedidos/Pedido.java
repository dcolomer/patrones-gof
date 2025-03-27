package comportamiento.state.pedidos;

import java.util.ArrayList;
import java.util.List;

/**
 * Context
 */
public class Pedido {
	private AbstractEstado estadoPedido;
	private List<Linea> lineas;
	private Direccion direccion;
	private boolean pagado;

	public Pedido() {
		estadoPedido = new EstadoAbierto(this);
		lineas = new ArrayList<Linea>();
	}
	
	public Estados getDescripcionEstado() {
		return estadoPedido.getDescipcionEstado();
	}

	/*
	 * Operaciones delegadas sobre la subclase de estado en curso
	 */
	public void addArticulo(Articulo articulo, int cantidad) {
		estadoPedido = estadoPedido.addArticulo(articulo, cantidad);
	}

	public void cancelar() {
		estadoPedido = estadoPedido.cancelar();
	}

	public void confirmar() {
		estadoPedido = estadoPedido.confirmar();
	}

	public void perder() {
		estadoPedido = estadoPedido.perder();
	}
	
	public void entregar() {
		estadoPedido = estadoPedido.entregar();
	}
	
	public void encontrar() {
		estadoPedido = estadoPedido.encontrar();
	}

	public void pagar() {
		estadoPedido = estadoPedido.pagar();
	}

	public void enviar(String direccion) {
		estadoPedido = estadoPedido.enviar(direccion);
	}

	public boolean isFinalizado() {
		return estadoPedido.isFinalizado();
	}
	
	/*
	 * Getters y setters
	 */
	public List<Linea> getLineas() {
		return lineas;
	}
	
	public Direccion getDireccion() {
		return direccion;
	}
	
	public boolean isPagado() {
		return pagado;
	}
	
	public void setLineas(List<Linea> lineas) {
		this.lineas = lineas;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

}
