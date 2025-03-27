package creacionales.objectpool.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class HiloCliente extends Thread {
	
	// Disponer un log para que cada hilo escriba los mensajes que considere oportunos
	static final Logger LOG = Logger.getLogger(HiloCliente.class);
	
	// Cada hilo ejecutará 10 consultas SQL
	static final int MAX_CONSULTAS = 10;
		
	HiloCliente(String nombre) {
		this.setName(nombre);			
	}
	
	@Override
	public void run() {		
						
		// Obtener una conexion
		Connection con = Main.pool.adquirir();
		
		// Mostrar estadisticas del pool
		printEstadisticas("UNA VEZ ADQUIRIDA LA CONEXION.");
		
		// Usar la conexion
		try {
			// Se ejecutaran MAX_CONSULTAS, pero cada hilo en un orden aleatorio 
			// para que así la simulacion sea mas real
			for (int i=1; i<=MAX_CONSULTAS; i++) {
				String consulta = Utiles.getConsultaAleatoriamente();								
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(consulta);
				Utiles.mostrarSelect(rs, consulta);
				if (!s.isClosed()) {
					s.close();
				}
				if (!rs.isClosed()) {
					rs.close();
				}
			}							
		} catch (SQLException ex) {
			LOG.error(ex);			
		} catch (Exception e) {
			LOG.error(e);
		} finally { // Devolver la conexion al pool
			Main.pool.devolver(con);
			
			// Mostrar estadisticas del pool
			printEstadisticas("DESPUES DE DEVOLVER LA CONEXION.");
		}
	
		
	}
		
	private void printEstadisticas(String msjVariable) {
		if (LOG.isDebugEnabled()) {
			LOG.debug(msjVariable + " Conexiones -- disponibles: " + Main.pool.getDisponiblesEnPool() 
					+ " Ocupadas: " + Main.pool.getOcupadosEnPool() 
					+ " Totales: " + Main.pool.getOcupacionTotal());
		}			
	}
}
