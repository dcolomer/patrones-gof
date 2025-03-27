package comportamiento.strategy.video;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends ObjetoDominio {

	private static final String CATEGORIA = "clientes";
	
	private List<Alquiler> alquileres = new ArrayList<Alquiler>();

	public Cliente(String nombre) {
		super(nombre);
	}

	public String mostrarEstadoCuentas() {		
		String head = "Alquiler del cliente " + getNombre() + ":\n";
		String resultado = "";		
		for (Alquiler alquiler : alquileres) {					
            resultado += "\t" + alquiler.getSoporteFisico().getPelicula().getNombre() +
            		"\t" + String.valueOf(alquiler.calcularImportePrestamo()) + "€\n";
		}       
        // Informacion pie factura
        resultado += "A cobrar: " + String.valueOf(calcularPagoTodosAlquileres()) + "€\n";
        resultado += "Puntos: " + String.valueOf(getPuntosTodosAlquileres()) + " puntos";
        return head + resultado;
    }

	private double calcularPagoTodosAlquileres() {
		double resultado = 0;		
		for (Alquiler alquiler : alquileres) {
			resultado += alquiler.calcularImportePrestamo();
		}		
		return resultado;
	}

	private int getPuntosTodosAlquileres() {		
        int resultado = 0;        
        for (Alquiler alquiler : alquileres) {
			resultado += alquiler.getPuntos();
		}                
        return resultado;
    }

	public void nuevoAlquiler(Alquiler alquiler) {
		alquileres.add(alquiler);
	}

	public void persist() {
		Registro.add(CATEGORIA, this);
	}

	public static Cliente get(String nombre) {
		return (Cliente) Registro.get(CATEGORIA, nombre);
	}

	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", alquileres=" + printAlquileres() + "]";
	}

	private String printAlquileres() {
		String resultado = "";
		for (Alquiler alquiler : alquileres) {
			resultado += alquiler.toString() + "\n";
		}
		return resultado;
	}
}
