package comportamiento.observer.ventas_con_patron;

public class Articulo {
	
	private int codigo;
	private String desc;
	private float precio;
	
	public Articulo(int codigo, String desc, float precio) {		
		this.codigo = codigo;
		this.desc = desc;
		this.precio = precio;
	}
	
	public int getCodigo() { return codigo; }
	public String getDesc() { return desc; }
	public float getPrecio() { return precio;	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Articulo other = (Articulo) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Articulo [codigo=" + codigo + ", desc=" + desc + ", precio="
				+ precio + "]";
	}
		
}
