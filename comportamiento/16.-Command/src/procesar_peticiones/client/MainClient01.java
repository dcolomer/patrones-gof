package comportamiento.command.procesar_peticiones.client;

import comportamiento.command.procesar_peticiones.client.receivers.Catalogo;
import comportamiento.command.procesar_peticiones.frmwrk.ProcesadorPeticiones;

/*
 * Clase creada por el usuario del framework.
 * Hace lo siguiente:
 * 
 * -Crea un articulo
 * -Lista el catalogo
 * -Elimina un articulo del catalogo
 * -Vuelve a listar el catalogo
 * 
 * Notad que está en un nivel de abstracción muy alto, es decir, 
 * muy familiar al lenguaje natural.
 */
public class MainClient01 extends SuperMain {
	
	public MainClient01() {		
		super(new ProcesadorPeticiones(), new Catalogo());	
		
		crearArticulo("2000", "Ordenador portatil PALSON-WQE", "610.60");				
		mostrarArticulos();
		eliminarArticulo("1000");		
		mostrarArticulos();		
	}
	
	public static void main(String[] args) {
		new MainClient01();		
	}		
}
