package estructurales.flyweight.aprox0;

public class TarjetaVisitaNoFlyweight {

	private String empresa;
	private String nombre;
	private String cargo;
	private String region;
	private String direccion;
	private String ciudad;
	private String provincia;
	private String codPostal;	

	public TarjetaVisitaNoFlyweight(String empresa, String nombre, 
			String cargo, String region, String direccion, 
			String ciudad, String provincia, String codPostal) 
	{		
		this.empresa = empresa;
		this.nombre = nombre;
		this.cargo = cargo;
		this.direccion = direccion;
		this.region = region;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.codPostal = codPostal;
	}
	
	public void print() {		
		System.out.println(nombre);
		System.out.println(cargo);
		System.out.println(region);
		System.out.println(empresa + "-" + 
				direccion + "-" +
				ciudad + "-" +
				provincia + "-" +
				codPostal
		);
		System.out.println("----------------");
	}
}
