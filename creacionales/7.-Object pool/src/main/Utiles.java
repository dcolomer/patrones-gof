package creacionales.objectpool.main;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Random;

import org.apache.log4j.PropertyConfigurator;

import static creacionales.objectpool.main.HiloCliente.*;

/*
 * Clase que contiene métodos de utilidad (estáticos)
 */
class Utiles {

	/*
	 * Carga el fichero de configuracion de Log4j
	 * y lo inicializa
	 */
	static void loadConfigLogging(Class<?> clase, String fichero, 
			boolean ficheroEnPaquete) 
	{
		Properties logProperties = new Properties();

		try { // Cargar la configuracion del log			
			if (ficheroEnPaquete) {
				// Busca el fichero en el mismo paquete que la clase
				logProperties.load(clase.getResourceAsStream(fichero));	
			} else {
				// Busca en el classpath de la aplicacion
				logProperties.load(clase.getClassLoader().getResourceAsStream(fichero));	
			}
			
			PropertyConfigurator.configure(logProperties);
									
		} catch (Exception e) {
			String msg="No se ha podido cargar el fichero de configuracion de logging: "
				+ fichero;			
			System.out.println(msg);
		}
	}
	 
	/*
	 * Aleatoriamente retorna una cadena que representa una de 
	 * las N consultas disponibles 
	 */
	static String getConsultaAleatoriamente() {			
		int numConsulta = new Random().nextInt(MAX_CONSULTAS)+1;
		String qry = null; 
		switch (numConsulta) {
			case 1: // Mostrar las columnas de título y artista de la tabla ‘pel’
				qry = "SELECT titulo, artista FROM pel";
				break;			
			case 2:	// Mostrar TODAS las columnas de la tabla ‘pel’.
				qry = "SELECT * FROM pel";
				break;
			case 3: // Mostrar todos los tipos de películas existentes
				qry = "SELECT DISTINCT tipo FROM pel";
				break;
			case 4: // Mostrar todas las películas cuyo tipo sea comedia.
				qry = "SELECT * FROM pel WHERE tipo='COMEDIA'";
				break;
			case 5: // Mostrar las películas de tipo comedia que costaron a la empresa más de 12€.
				qry = "SELECT * FROM pel WHERE tipo='COMEDIA' AND precio>12";
				break;
			case 6: // Listar el título de cada película, el número de copias disponibles y la inversión hecha por la empresa.
				qry = "SELECT titulo, copias, precio*copias AS inversion FROM pel";
				break;
			case 7: // Listar las películas en las que la empresa ha invertido más de 60€.
				qry = "SELECT titulo, copias, precio*copias FROM pel WHERE precio*copias>60";
				break;
			case 8: // Obtener el número de películas que hay en la tabla 'pel'.
				qry = "SELECT COUNT(*) FROM pel";
				break;
			case 9: // Obtener la facturación total.
				qry = "SELECT SUM(total) FROM fac";
				break;
			case 10: // Obtener la inversión máxima, mínima y promedio hecha por la compra de películas.
				qry = "SELECT MAX(precio*copias), MIN(precio*copias), AVG(precio*copias) FROM pel";
				break;	
			default:
				LOG.error("Se ha intentado ejecutar una consulta cuyo numero no se contemplaba: "+numConsulta);
		}
		return qry;
	}
	
	/*
	 * Metodo que evalua un ResultSet y lo imprime por pantalla
	 */
	static void mostrarSelect(ResultSet res, String consulta) throws SQLException {
		if (LOG.isDebugEnabled()) {
			LOG.debug(consulta);
		}
		ResultSetMetaData resMD = res.getMetaData();
		int num_cols = resMD.getColumnCount();
		
		res.beforeFirst();
		while (res.next()) {
			StringBuffer sbTitle = new StringBuffer();
			StringBuffer sbValues = new StringBuffer();
			
			for (int cont=0; cont<num_cols; cont++) {
				sbTitle.append(resMD.getColumnName(cont+1));
				sbTitle.append("\t");

				if (resMD.getColumnTypeName(cont+1).equals("DATE"))	{
					java.sql.Date fecha = res.getDate(cont+1);
		          	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		          	String sDate = df.format(fecha);
		          	sbValues.append(sDate);
				} else {
					sbValues.append(res.getString(cont+1));
				}
				sbValues.append("\t");
			}
			
			//LOG.debug(sbTitle.toString());
			//LOG.debug(sbValues.toString());			
			//LOG.debug("----------------------------------------------------------------------------------------------------------");
         }
	}
	
}
