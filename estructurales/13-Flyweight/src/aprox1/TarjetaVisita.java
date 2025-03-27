package estructurales.flyweight.aprox1;

public class TarjetaVisita {
	private String nombre;
	private String cargo;
	private Flyweight flyweight;
	
	public TarjetaVisita(String nombre, String cargo, Flyweight flyweight) {
		this.nombre = nombre;
		this.cargo = cargo;
		this.flyweight = flyweight;
	}
	
	public void print() {
		System.out.println(nombre);
		System.out.println(cargo);
		System.out.println(flyweight.getEmpresa() + "-" + 
				flyweight.getDireccion() + "-" +
				flyweight.getCiudad() + "-" +
				flyweight.getProvincia() + "-" +
				flyweight.getCodPostal()
		);
		System.out.println("----------------");
	}

}
