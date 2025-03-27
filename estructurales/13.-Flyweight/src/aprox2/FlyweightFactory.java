package estructurales.flyweight.aprox2;

import java.util.HashMap;

/*
 * Factoría de objetos flyweight (es un Singleton).
 * 
 * Encapsula los datos de una tarjeta de visita que 
 * son constantes para todos los empleados de una
 * misma región.
 * A traves del metodo print() recibe los datos de 
 * la tarjeta de visita que son variables.
 */
public class FlyweightFactory {
	private HashMap<Region, Flyweight> flyweights;
	
	// Instancia única de la factoria (Singleton)
	private static FlyweightFactory factory = 
		new FlyweightFactory();

	public static FlyweightFactory getInstance() {
		return factory;
	}

	public synchronized Flyweight getFlyweight(Region region) {
		if (flyweights.get(region) == null) {
			Flyweight flyweight = new FlyweightImpl(region);
			flyweights.put(region, flyweight);
			return flyweight;
		} else {
			return (Flyweight) flyweights.get(region);
		}
	}

	// Constructor privado
	private FlyweightFactory() {
		flyweights = new HashMap<Region, Flyweight>();
	}

	// Clase FlyweightImpl
	private class FlyweightImpl implements Flyweight {
		// El nombre de la empresa es el mismo para 
		// los cuatro tipos de objetos flyweight
		private static final String empresa = "LuxMetal";
		private String direccion, ciudad, provincia, codPostal;
		
		/*
		 * Constructor. Los valores los proporcionamos directamente 
		 * pero tendrían que venir de una base de datos.
		 */
		private FlyweightImpl(Region region) {
			if (region.equals(Region.Norte)) {
				setValues("Calle Cantabria 456", "Bilbao",
						"Vizcaya", "10000");
			}
			if (region.equals(Region.Sur)) {
				setValues("Calle de las sierpes 111",
						"Alcala de Guadaira", "Sevilla", "20000");
			}
			if (region.equals(Region.Este)) {
				setValues("Avenida del Mediterraneo s/n",
						"Oropesa", "Castellon", "30000");
			}
			if (region.equals(Region.Oeste)) {
				setValues("Carretera de Portugal",
						"Jerez de los Caballeros", "Badajoz", "40000");
			}
		}

		public String getEmpresa() { return empresa; }
		public String getDireccion() { return direccion; }
		public String getCiudad() { return ciudad; }
		public String getProvincia() { return provincia; }
		public String getCodPostal() { return codPostal; }
		
		private void setValues(String dir, 
				String ciu, String prov, String cp) 
		{			
			direccion = dir;
			ciudad = ciu;
			provincia = prov;
			codPostal = cp;
		}

		@Override
		public void print(String nombre, String cargo) {
			System.out.println(nombre);
			System.out.println(cargo);
			System.out.println(getEmpresa() + "-" + 
					getDireccion() + "-" +
					getCiudad() + "-" +
					getProvincia() + "-" +
					getCodPostal()
			);
			System.out.println("----------------");		
		}

	} // Final de FlyweightImpl
}
