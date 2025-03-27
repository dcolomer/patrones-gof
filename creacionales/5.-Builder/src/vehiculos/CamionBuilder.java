package creacionales.builder.vehiculos;

// ConcreteBuilder3
public class CamionBuilder extends VehiculoBuilder {

	@Override 	
	public void construirChasis() {
		vehiculo = new Vehiculo("Camion");
		vehiculo.addParte("chasis", "chasis camion");		
	}

	@Override
	public void construirMotor() {		
		vehiculo.addParte("motor", "10000 cc");
	}

	@Override
	public void construirRuedas() {
		vehiculo.addParte("ruedas", "12");		
	}

	@Override
	public void construirPuertas() {
		vehiculo.addParte("puertas", "3");		
	}

}
