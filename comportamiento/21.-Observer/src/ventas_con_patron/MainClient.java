package comportamiento.observer.ventas_con_patron;

public class MainClient {

	private Venta venta;
	private VentaManager mng;
	
	public MainClient() {
		venta = new Venta();
		mng = new VentaManager(venta);
		
		CuadroMandosVentas frame = new CuadroMandosVentas(mng);
		frame.setVisible(true);
		
		mng.initProcess();
	}
	
	public static void main(String[] args) {
		new MainClient();		
	}

}
