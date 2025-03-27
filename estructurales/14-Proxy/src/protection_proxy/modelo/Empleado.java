package estructurales.proxy.protection_proxy.modelo;

public class Empleado implements Comparable<Empleado> {
		
	private int codigo;
	private String nombre;
	private float sueldoBruto;
	
	public Empleado(int codigo, String nombre, float sueldoBruto) {		
		this.codigo = codigo;
		this.nombre = nombre;
		this.sueldoBruto = sueldoBruto;
	}
	
	public int getCodigo() { return codigo;	}
	public String getNombre() { return nombre; }
	public float getSueldoBruto() {	return sueldoBruto;	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + Float.floatToIntBits(sueldoBruto);
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
		Empleado other = (Empleado) obj;
		if (codigo != other.codigo)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (Float.floatToIntBits(sueldoBruto) != Float
				.floatToIntBits(other.sueldoBruto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Empleado [codigo=" + codigo + ", nombre=" + nombre
				+ ", sueldoBruto anual=" + sueldoBruto + "]";
	}

	@Override
	public int compareTo(Empleado e) {		
		return this.codigo < e.codigo ? -1: 1;		
	}
			
}
