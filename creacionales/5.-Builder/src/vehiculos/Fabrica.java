package creacionales.builder.vehiculos;

// Director
public class Fabrica {
	
	// El Director necesita saber los pasos generales 
	// para la construcci�n de un vehiculo
	public void construct(VehiculoBuilder vehiculoBuilder) {
		vehiculoBuilder.construirChasis();
		vehiculoBuilder.construirMotor();
		vehiculoBuilder.construirRuedas();
		vehiculoBuilder.construirPuertas();
	}
}
