package creacionales.objectpool;

/*
 * Clase que encapsula la informacion necesaria para 
 * conectar con una base de datos.
 * Se utiliza por conveniencia para no tener que pasar
 * tantos parámetros en las llamadas a métodos
 */
public class DatosConexion {
	String driver, dsn, usr, pwd;
	public DatosConexion(String driver_, String dsn_, String usr_, String pwd_) {
		driver = driver_;
		dsn = dsn_;
		usr = usr_;
		pwd = pwd_;
	}
}
