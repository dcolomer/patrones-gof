package creacionales.builder.vehiculos;

// Client
public class MainClient {

	public static void main(String args[]) {

		// Crear la fabrica
		Fabrica fabrica = new Fabrica();
		
		// Crear tres builder concretos
		VehiculoBuilder vb1 = new CamionBuilder();
		VehiculoBuilder vb2 = new CocheBuilder();
		VehiculoBuilder vb3 = new MotoBuilder();

		// Construirlos y mostrarlos
		fabrica.construct(vb1);
		Vehiculo v1 = vb1.getVehiculo();
		System.out.println(v1);
		
		fabrica.construct(vb2);
		Vehiculo v2 = vb2.getVehiculo();
		System.out.println(v2);

		fabrica.construct(vb3);
		Vehiculo v3 = vb3.getVehiculo();
		System.out.println(v3);
	}
}
