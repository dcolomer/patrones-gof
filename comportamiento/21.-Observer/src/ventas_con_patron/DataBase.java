package comportamiento.observer.ventas_con_patron;

import java.util.HashSet;
import java.util.Set;

/*
 * Clase que simula una tabla de artículos.
 * Crea algunos artículos y proporciona una 
 * serie de métodos estáticos.
 * En una linea de venta no puede existir un
 * artículo que no esté aquí definido previamente.
 * 
 */
public class DataBase {
	private static Set<Articulo> articulos;
	
	static {
		initDataBase();
	}
	
	private static void initDataBase() {
		articulos = new HashSet<Articulo>();
		articulos.add(new Articulo(1,"Articulo P1", 2.5F));
		articulos.add(new Articulo(2,"Articulo P2", 4.25F));
		articulos.add(new Articulo(3,"Articulo P3", 1.75F));
		articulos.add(new Articulo(4,"Articulo P4", 2.00F));
	}
	
	public static boolean existeArticulo(int codigo) {
		for (Articulo articulo : articulos) {
			if (articulo.getCodigo() == codigo) {
				return true;
			}
		}
		return false;
	}
	
	public static float getPrecio(int codigo) {
		for (Articulo articulo : articulos) {
			if (articulo.getCodigo() == codigo) {
				return articulo.getPrecio();
			}
		}
		return 0;
	}
	
	public static Articulo getArticulo(int codigo) {
		for (Articulo articulo : articulos) {
			if (articulo.getCodigo() == codigo) {
				return articulo;
			}
		}
		return null;
	}
	
	public static Set<Articulo> getArticulos() {
		return articulos;
	}
}
