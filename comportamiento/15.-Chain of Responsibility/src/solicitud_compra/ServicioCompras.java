package comportamiento.CoR.solicitud_compra;

public class ServicioCompras {
	
	private final static ServicioCompras 
			servicioCompras = new ServicioCompras();
	
	private final GestorPeticiones INICIO_CADENA;
	
	// Constructor privado, no instanciable
	private ServicioCompras() { 
		INICIO_CADENA = 
			new DirectorSucursal("Sr. Garcia")
				.setSiguienteGestor(new DirectorProvincial("Sra. Martinez")
					.setSiguienteGestor(new DirectorRegional("Sr. Perez")
						.setSiguienteGestor(new DirectorGeneral("Sra. Sanchez")
							.setSiguienteGestor(null))));
	}	
	
	public static ServicioCompras getInstance() {
		return servicioCompras;
	}
	
	public boolean solicitarAutorizacion(Peticion solicitud) {				
		return INICIO_CADENA.autorizar(solicitud); 							
	}
}
