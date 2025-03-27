package comportamiento.command.procesar_peticiones.client.receivers;

import java.math.BigDecimal;

/*
 * Clase creada por el usuario del framework
 */
public class Articulo {
	
	private String codigo;
	private String descripcion;
	private BigDecimal precio;
	
	public Articulo(String codigo, String descripcion, BigDecimal precio) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public String getCodigo() {	return codigo; }
	public String getDescripcion() { return descripcion; }
	public BigDecimal getPrecio() {	return precio; }	
	
	@Override
	public String toString() {
		return "Articulo [codigo=" + codigo + ", descripcion=" + descripcion
				+ ", precio=" + precio + "]";
	}
	
}
