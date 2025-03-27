package creacionales.builder.vehiculos;

// Builder (abstracto)
public abstract class VehiculoBuilder {
	
	protected Vehiculo vehiculo;

	public abstract void construirChasis();
	public abstract void construirMotor();
	public abstract void construirRuedas();
	public abstract void construirPuertas();

	public Vehiculo getVehiculo() {
		return vehiculo;
	}
}