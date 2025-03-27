package creacionales.builder.vehiculos;

import java.util.HashMap;

//Product
public class Vehiculo {
	private String tipo;
	
	public Vehiculo(String tipo) {
		this.tipo = tipo;
	}

	private HashMap<String, String> partes = 
		new HashMap<String, String>();

	public void addParte(String clave, String parte) {
		partes.put(clave, parte);
	}
	
	public String getParte(String clave) {
		return partes.get(clave);
	}

	@Override 
	public String toString() {
		return "\n—————————" +
		"Tipo de vehiculo: " + tipo + "\n" +
		" Chasis : " + getParte("chasis") + "\n" +
		" Motor : " + getParte("motor") + "\n" +
		" Num. ruedas: " + getParte("ruedas") + "\n" +
		" Num. puertas : " + getParte("puertas");		
	}
	
}