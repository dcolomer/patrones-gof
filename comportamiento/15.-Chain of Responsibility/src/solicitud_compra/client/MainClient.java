package comportamiento.CoR.solicitud_compra.client;

import comportamiento.CoR.solicitud_compra.Peticion;
import comportamiento.CoR.solicitud_compra.ServicioCompras;

public class MainClient {

	public static void main(String[] args) {
		
		/*
		 * Creamos 3 solicitudes
		 */
		Peticion[] solicitudes = {					
			new Peticion(1000, "Ordenadores laboratorio", 25000),
			new Peticion(1001, "Viaje feria Alemania", 25800),
			new Peticion(1002, "Nuevo almacen BCN", 1000001)
		};
		
		/*
		 * Obtenemos una instancia del servicio de compras y pedimos 
		 * la aprobación de cada una de la solicitudes de compra
		 */
		ServicioCompras servicio = ServicioCompras.getInstance();
		
		for (Peticion solicitud : solicitudes) {
			boolean aprobada = servicio.solicitarAutorizacion(solicitud);
			System.out.println(solicitud);
			if (!aprobada) {
				/*
				 * Simulamos que hemos conseguido un descuento y 
				 * la volvemos a enviar al flujo de aprobación
				 */
				double importe = getDescuento(solicitud.getImporte());
				solicitud.setImporte(importe);
				servicio.solicitarAutorizacion(solicitud);
				System.out.println(solicitud);
			}
		}
	}

	private static double getDescuento(double importe) {		
		return importe*0.75;
	}

}
