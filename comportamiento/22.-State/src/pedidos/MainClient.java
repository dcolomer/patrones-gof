package comportamiento.state.pedidos;

import java.util.Arrays;

public class MainClient {
	private Pedido pedido;
	private Direccion direccion;
	private Articulo cable_USB;
	private Articulo papel_DIN_A_4;
	
	public static void main(String args[]) {
		MainClient main = new MainClient();
		main.preparar();
		System.out.println("Prueba de flujo de ejecucion normal...");
		main.probarFlujoNormal();
		main.reset();
		System.out.println("Prueba de flujo de ejecucion anomalo...");
		main.probarFlujoAnomalo();
	}
	
	public void reset() {
		pedido = new Pedido();
	}
	
	public void preparar() {
		pedido = new Pedido();

		direccion = new Direccion();
		direccion.setDirecciones(Arrays.asList("Mr S. Claus", "Northpole 1A", "Arctica"));

		cable_USB = crearArticulo("1230010", "USB Cable (5.0m)");
		papel_DIN_A_4 = crearArticulo("1230030", "A4 paper (500 p)");
	}
		
	public void probarFlujoNormal() {
		pedido.setDireccion(direccion);		
		pedido.addArticulo(cable_USB, 4);
		pedido.addArticulo(papel_DIN_A_4, 20);		
		verificarEstado(Estados.Abierto, pedido.getDescripcionEstado());
		
		pedido.confirmar();		
		verificarEstado(Estados.Confirmado, pedido.getDescripcionEstado());
		
		pedido.setPagado(true);
		
		pedido.enviar("555555X");		
		verificarEstado(Estados.Enviado, pedido.getDescripcionEstado());
		
		pedido.perder(); // El transportista lo ha perdido
		verificarEstado(Estados.Perdido, pedido.getDescripcionEstado());
		
		// ...
		
		pedido.encontrar(); // Vuelve al estado de Enviado
		verificarEstado(Estados.Enviado, pedido.getDescripcionEstado());
		
		pedido.entregar();	
		verificarEstado(Estados.Entregado, pedido.getDescripcionEstado());
		
		confirmarCierto(pedido.isFinalizado());
	}
	
	public void probarFlujoAnomalo() {
		pedido.setDireccion(direccion);
		pedido.setPagado(true);
		pedido.addArticulo(cable_USB, 4);

		pedido.confirmar();
		pedido.enviar("555555X");
		try {
			pedido.cancelar();
			throw new RuntimeException("Error! Un pedido enviado no puede cancelarse.");
		} catch (RuntimeException e) {
			System.out.println("Excepcion controlada: " + e);
		}
	}
	
	private Articulo crearArticulo(String id, String nombre) {
		Articulo item = new Articulo();
		item.setNombre(nombre);
		item.setId(id);
		return item;
	}
	
	private void confirmarCierto(boolean condicion) {
		if (!condicion) {
			throw new RuntimeException("Error! La condicion no se ha cumplido");
		}		
	}

	private void verificarEstado(Estados estadoEsperado, Estados estadoReal) {
		if (!estadoEsperado.equals(estadoReal)) {
			throw new RuntimeException("Error! Estado esperado: " + 
					estadoEsperado + " | estado real: " + estadoReal);
		} else {
			System.out.println("Ok! Estado esperado: " + 
					estadoEsperado + " | estado real: " + estadoReal);
		}
	}
}
