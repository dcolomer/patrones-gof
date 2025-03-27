package comportamiento.command.procesar_peticiones.client.receivers;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/** Receiver **/

/*
 * Clase creada por el usuario del framework
 */
public class Catalogo {
	private static Map<String, Articulo> catalogo = inicializarCatalogo();
	
	private static Map<String, Articulo> inicializarCatalogo() {
		Map<String, Articulo> catalogo = new LinkedHashMap<String, Articulo>();
		catalogo.put("1000", new Articulo("1000", "Televisor plano X100", new BigDecimal("540.50")));	
		catalogo.put("1001", new Articulo("1001", "Televisor plano X600", new BigDecimal("920.60")));
		return catalogo;
	}

	public void mostrar() {		
		System.out.println("========================");
		System.out.println("Mostrando el catalogo...");
		System.out.println("========================");
		
		if (!catalogo.isEmpty()) {
			for (Articulo articulo : catalogo.values()) {
				System.out.println(articulo);
			}
		} else {
			System.out.println("El catalogo esta vacio.");
		}
	}
	
	public void nuevo(Articulo articulo) {
		catalogo.put(articulo.getCodigo(), articulo);		
	}
	
	public void eliminarArticulo(String codigo) {
		catalogo.remove(codigo);
	}
	
	public Articulo getArticulo(String codigo) {
		return catalogo.get(codigo);
	}
}
