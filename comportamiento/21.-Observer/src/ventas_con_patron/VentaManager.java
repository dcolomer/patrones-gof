package comportamiento.observer.ventas_con_patron;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * Clase que se encarga de gestionar los procesos
 * relaciondos con un objeto Venta:
 * -Crea objetos LineaVenta a partir del fichero externo
 * -Añade al fichero lineas de venta
 * -Calcula el total de la venta
 */
public class VentaManager implements PropertyPublisher {
	
	private Venta venta;
	
	//private CuadroMandosVentas pantalla;
	
	private Set<PropertyListener> listeners;
	
	@Override
	public void addPropertyListener(PropertyListener listener) {
		listeners.add(listener);		
	}

	@Override
	public void removePropertyListener(PropertyListener listener) {
		listeners.remove(listener);		
	}

	@Override
	public void publishPropertyEvent(String nombre, float valor) {
		for (PropertyListener listener : listeners) {
			listener.onPropertyEvent(this, nombre, valor);
		}		
	}
	
	/* Ya no es necesario
	 * public static void main(String[] args) {
		new VentaManager(new Venta());
	}*/
		
	public VentaManager(Venta venta) {
		listeners = new HashSet<PropertyListener>();
		
		this.venta = venta;
		
		//pantalla = new CuadroMandosVentas(this);
		//pantalla.setVisible(true);
	}
	
	public void initProcess() {
		procesar();
		
		// Simulamos que el sistema externo ha añadido una nueva línea de venta
		addVenta("4;Articulo P4;6");
		
		procesar();
		
		// Antes de finalizar el programa volvemos a quitar la linea 
		// para que todo funcione en la siguiente ejecución
		removeVenta(4);
		System.out.println("end");
	}
	
	/*
	 * Se lee el fichero que contiene las lineas de venta y
	 * se vuelve a recalcular el total vendido.
	 * Se actualiza el cuadro de mandos de ventas, aunque
	 * no hayan variado las ventas.
	 *   
	 */
	private void procesar() {
		calcTotal();
		publishPropertyEvent("venta.total", venta.getTotalVenta());
		
		//pantalla.setTxtVentas(venta.getTotalVenta() + "");		
		
		// Detenmos la ejecución 5 segundos para que el usuario 
		// del cuadro de mandos pueda percibir el cambio
		try { 
			Thread.sleep(5 * 1000); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Invoca al método encargado de procesar
	 * el fichero que contiene las lineas de venta.
	 * Después recorre la colección de objetos 
	 * LineaVenta perteneciente a la Venta y calcula 
	 * el total general.
	 */
	public void calcTotal() {
		comprobarNuevasVentas();
		float acumulado = 0F;
		for (LineaVenta linea : venta.getLineas()) {
			acumulado = acumulado + linea.getTotalLinea();
		}
		venta.setTotalVenta(acumulado);
	}
	
	/*
	 * Abre el fichero que contiene las lineas de venta
	 * y crea a partir de ellas objetos LineaVenta, los
	 * cuales se asocian al objeto Venta gestionado por
	 * esta clase.
	 */
	private void comprobarNuevasVentas() {
		InputStream in = getClass().getResourceAsStream(
				"/comportamiento/observer/ventas_con_patron/ventas.dat");
		BufferedReader bufRdr = null;
		try {
			bufRdr = new BufferedReader(new InputStreamReader(in, "utf-8"));
			String linea = null;
			while ((linea = bufRdr.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(linea, ";");
				while (st.hasMoreTokens()) {
					int codigo = Integer.parseInt(st.nextToken());
					
					/*
					 * El codigo de articulo del fichero debe ser uno de
					 * los artículos definidos en la clase DataBase, ya que
					 * no podemos vender algo que no fabricamos.   
					 */
					if (DataBase.existeArticulo(codigo)) {
						String desc = st.nextToken();
						int cantidad = Integer.parseInt(st.nextToken());
						float precio = DataBase.getPrecio(codigo);
						Articulo articulo = new Articulo(codigo, desc, precio);
						LineaVenta lineaVenta = new LineaVenta(articulo, cantidad);
						venta.getLineas().add(lineaVenta);
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufRdr != null) {
				try {
					bufRdr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * Abre el fichero que contiene las lineas de venta
	 * y añade al final del mismo la linea recibida por
	 * parámetro.
	 */
	public void addVenta(String lineaVentaFile) {
		URL url = getClass().getResource(
				"/comportamiento/observer/ventas_con_patron/ventas.dat");
		FileWriter fstream = null;
		BufferedWriter out = null;
		try {
			File file = new File(url.toURI().getPath());
			fstream = new FileWriter(file, true);
			out = new BufferedWriter(fstream);
			out.write(lineaVentaFile);
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fstream != null) {
				try {
					fstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * Elimina del fichero de lineas de venta, la venta
	 * del artículo cuyo código coincida con el parámetro 
	 * recibido.
	 * Para ello, crea un Set de lineas de venta que excluye
	 * al artículo en cuestión. Después regenera el fichero
	 * de lineas de venta, volcando el contenido del Set.
	 */
	public void removeVenta(int codigo) {
		Set<LineaVenta> ventaFile = new HashSet<LineaVenta>();
		
		// Crea un Set temporal que excluya al articulo a eliminar
		for (LineaVenta linea : venta.getLineas()) {
			if (linea.getArticulo().getCodigo() != codigo) {
				ventaFile.add(linea);
			}
		}
		
		URL url = getClass().getResource(
				"/comportamiento/observer/ventas_con_patron/ventas.dat");
		FileWriter fstream = null;
		BufferedWriter out = null;
		try {
			File file = new File(url.toURI().getPath());
			fstream = new FileWriter(file);
			out = new BufferedWriter(fstream);
			
			for (LineaVenta linea : ventaFile) {
				int code = linea.getArticulo().getCodigo();
				String desc = linea.getArticulo().getDesc();
				int cantidad = linea.getCantidad();
				String lineaToFile = code + ";" + desc + ";" + cantidad + "\n";
				out.write(lineaToFile);
			}
			
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fstream != null) {
				try {
					fstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
