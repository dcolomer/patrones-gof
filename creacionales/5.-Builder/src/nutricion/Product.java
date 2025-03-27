package creacionales.builder.nutricion;

public class Product {

	private final int cantidad; // (mL) requerido
	private final int unidades; // (por envase) requerido
	private final int calorias; // (kcal) requerido
	private final int grasas; // (g) opcional
	private final int sodio; // (mg) opcional
	private final int potasio; // (mg) opcional
	private final int carbohidratos; // (g) opcional

	/*
	 * Clase Builder: Se trata de una clase anidada. NOTA: 
	 * Cuando una clase interna NO es estática sólo se pueden 
	 * crear instancias de ella mediante una instancia de la 
	 * clase que la envuelve. Por este motivo Builder es 
	 * estática: porque se crea la instancia de Builder antes 
	 * que la de Product!
	 */
	public static class Builder {

		// parametros obligatorios
		private final int cantidad;
		private final int unidades;
		private final int calorias;

		// parametros opcionales-inicializados a su valor predetermiando
		private int grasas = 0;
		private int sodio = 0;
		private int potasio = 0;
		private int carbohidratos = 0;
		
		// Constructor privado. Esto es opcional, podria haber sido publico
		// y entonces no haría falta el metodo de factoria estatico crearBuilder
		// (Cuestion de gustos)
		private Builder (int cantidad, int unidades, int calorias) {
			this.cantidad = cantidad;
			this.unidades = unidades;
			this.calorias = calorias;
		}

		// Metodo de factoria estatico que crear la instancia del 
		// Builder invocando al constructor privado. Inicializa
		// los atributos obligatorios
		public static Builder crearBuilder(int cantidad, int unidades, int calorias) {
			return new Builder(cantidad, unidades, calorias);
		}

		// setters para establecer los parametros opcionales.
		// Notad como devuelven el tipo Builder!
		public Builder grasas(int val) {
			grasas = val;
			return this;
		}

		public Builder sodio(int val) {
			sodio = val;
			return this;
		}

		public Builder potasio(int val) {
			potasio = val;
			return this;
		}

		public Builder carbohidratos(int val) {
			carbohidratos = val;
			return this;
		}

		// Finalmente, el método Builder.build() es quien crea
		// la instancia de Product
		public Product build() {
			return new Product(this);
		}
	}

	/*
	 * _______________________________________________________
	 * 
	 * Final de clase Builder
	 * _______________________________________________________
	 */

	// Constructor privado que recibe un builder (es llamado 
	// por el método buid() del Builder)
	private Product(Builder builder) {
		cantidad = builder.cantidad;
		unidades = builder.unidades;
		calorias = builder.calorias;
		grasas = builder.grasas;
		sodio = builder.sodio;
		potasio = builder.potasio;
		carbohidratos = builder.carbohidratos;
	}

	@Override
	public String toString() {
		return "[\nvalores obligatorios: cantidad=" + cantidad + ", unidades=" + unidades
				+ ", calorias=" + calorias + "\n" + "valores optativos: grasas=" + grasas + ", sodio="
				+ sodio + ", potasio=" + potasio + ", carbohidratos="
				+ carbohidratos + "\n]\n";
	}	
}
