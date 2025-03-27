package creacionales.builder.vehiculos;

// ConcreteBuilder1
public class MotoBuilder extends VehiculoBuilder {
	
	@Override
	public void construirChasis() {
		vehiculo = new Vehiculo("Moto");
		vehiculo.addParte("chasis", "chasis moto");
	}

	@Override
	public void construirMotor() {
		vehiculo.addParte("motor", "500 cc");
	}

	@Override
	public void construirRuedas() {
		vehiculo.addParte("ruedas", "2");
	}

	@Override
	public void construirPuertas() {
		vehiculo.addParte("puertas", "0");
	}

}