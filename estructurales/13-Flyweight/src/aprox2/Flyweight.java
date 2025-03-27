package estructurales.flyweight.aprox2;

public interface Flyweight {
	public String getEmpresa();
	public String getDireccion();
	public String getCiudad();
	public String getProvincia();
	public String getCodPostal();
	public void print(String nombre, String cargo);
}
