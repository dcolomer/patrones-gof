package estructurales.bridge.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import estructurales.bridge.persistencia.client.Alumno;

/** "ConcreteImplementor" **/

/**
 * Si no tenemos en MySQL la BD hacemos lo siguiente:
 * create database dbalumnos;
 * use alumnos;
 * create table alumnos (id int primary key, nombre varchar(20));
 */
class DataBasePersistenceImplementor 
		implements PersistenceImplementor {

	// Cadena de conexión
	private final String URLConexion = 
		"jdbc:mysql://localhost:3306/dbalumnos?user=root&password=root";
	
	public DataBasePersistenceImplementor() {	
			
	}
	
	/*
	 * Grabar un Alumno.	 
	 */
	@Override
	public long saveObject(Object object) {
		Alumno alumno = (Alumno) object;
		// Si no existe un registro en la BD con ese ID -> INSERT
		if (getObject(alumno.getId()) == null) {
			Connection con = null;		
			try {
				con = getConnection();
				PreparedStatement ps = con.prepareStatement("insert into alumnos"
						+ " (id, nombre) "
						+ " values (?,?) ");
				ps.setInt(1, alumno.getId());			
				ps.setString(2, alumno.getNombre());									
				if (ps.executeUpdate() == 0)
					throw new RuntimeException("No se ha podido grabar el alumno");
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				releaseConnection(con);			
			}
		// Existe un registro en la BD con ese ID -> UPDATE	
		} else { 
			actualizarAlumno(alumno);
		}
		
		return alumno.getId();
	}
	

	/*
	 * Recuperar el alumno cuyo ID coincide con el parametro 	 
	 */
	@Override
	public Object getObject(long objectId) {
		Connection con = null;		
		try {
			con = getConnection();
			PreparedStatement ps = con.prepareStatement("select id, nombre from alumnos where id=?");
			ps.setInt(1, (int)objectId);
			ResultSet rs = ps.executeQuery();
			Alumno alumno = null;
			if (rs.next()) {										
				alumno=new Alumno(rs.getInt(1), rs.getString(2));								
			}
			rs.close();
			ps.close();
			return alumno;
		} catch (SQLException e) {
			for (Throwable t : e) {
				System.err.println("Errores: " + t);
			}
		} finally {
			releaseConnection(con);
		}
		return null;
	}
	
	/*
	 * Borrar el Alumno cuyo ID coincide con el parametro
	 */
	@Override
	public void deleteObject(long objectId) {		
		Connection con = null;		
		try {
			con = getConnection();
			PreparedStatement ps = con.prepareStatement("delete from alumnos"
					+ " where id=?) ");
			ps.setInt(1, (int)objectId);														
			if (ps.executeUpdate() == 0)
				throw new RuntimeException("No se ha podido eliminar el alumno");
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseConnection(con);			
		}		
	}

	// METODOS PRIVADOS **********************
	
	/*
	 * Hacer un update en lugar de un insert
	 * cuando el alumno ya existe
	 */
	private void actualizarAlumno(Alumno alumno) {
		Connection con = null;		
		try {
			con = getConnection();
			PreparedStatement ps = con.prepareStatement("update alumnos"
					+ " set nombre=? where id=?");					
			ps.setString(1, alumno.getNombre());
			ps.setInt(2, alumno.getId());												
			if (ps.executeUpdate() == 0)
				throw new RuntimeException("No se ha podido actualizar el alumno");
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseConnection(con);			
		}		
	}
	
	/*
	 * OPERACIONES GENERALES
	 */
	
	// Obtener la conexión
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URLConexion);		
	}

	// Liberar la conexión
	private void releaseConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
				con = null;
			} catch (SQLException e) {
				for (Throwable t : e) {
					System.err.println("Error: " + t);
				}
			}
		}
	}

}
