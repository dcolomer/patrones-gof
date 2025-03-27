package creacionales.builder.vehiculos;

//ConcreteBuilder2
public class CocheBuilder extends VehiculoBuilder {

	@Override
	public void construirChasis() {	
		vehiculo = new Vehiculo("Coche");
		vehiculo.addParte("chasis", "chasis coche");		
	}

	@Override
	public void construirMotor() {
		vehiculo.addParte("motor", "2500 cc");
	}

	@Override
	public void construirRuedas() {
		vehiculo.addParte("ruedas", "4");		
	}

	@Override
	public void construirPuertas() {
		vehiculo.addParte("puertas", "4");		
	}
}
