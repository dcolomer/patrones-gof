package estructurales.composite.sistemaficheros;

public class MainClient {
	
	public static void main(String[] args) {
		
		/* Raiz */
		Directorio raiz = new Directorio("C:");
		
		/* Nivel 1 ****************************/
		
		// Primer hijo
		Componente arc1niv1 = new Archivo("leeme.txt");
		raiz.agregar(arc1niv1);		
		
		// Segundo hijo
		Componente dir1niv1 = new Directorio("videos");
		raiz.agregar(dir1niv1);
		
		// Tercer hijo
		Componente dir2niv1 = new Directorio("musica");
		raiz.agregar(dir2niv1);
		
		/* Nivel 2 ****************************/
		
		// Primer hijo de 'videos'
		Componente dir1niv2 = new Directorio("entretenimiento");
		dir1niv1.agregar(dir1niv2);
		
		// Segundo hijo de 'videos'
		Componente dir2niv2 = new Directorio("formacion");
		dir1niv1.agregar(dir2niv2);
		
		// Unico hijo de 'musica'
		Componente dir3niv2 = new Directorio("clasica");
		dir2niv1.agregar(dir3niv2);		
		
		/* Nivel 3 ****************************/
		
		// Primer hijo de 'entretenimiento'
		Componente arc1niv3 = new Archivo("Cars.avi");
		dir1niv2.agregar(arc1niv3);
		
		// Segundo hijo de 'entretenimiento'
		Componente arc2niv3 = new Archivo("Cars 2.avi");
		dir1niv2.agregar(arc2niv3);
		
		// Unico hijo de 'formacion'
		Componente dir1niv3 = new Directorio("2011");
		dir2niv2.agregar(dir1niv3);
		
		// Unico hijo de 'clasica'
		Componente dir2niv3 = new Archivo("Berliotz.mp3");
		dir3niv2.agregar(dir2niv3);
		
		/* Nivel 4 ****************************/
		
		// Unico hijo de '2011'
		Componente arc1niv4 = new Archivo("Curso Office.docx");
		dir1niv3.agregar(arc1niv4);
		
		// ejemplo
		// raiz.eliminar(3);
		
		/*
		 * Aqui podemos ver que el código cliente no hace distinciones
		 * para tratar con la clase Archivo o con la clase Directorio.
		 * Esto simplifica enormemente la programacion del codigo cliente. 
		 */
		raiz.mostrar(0);
	}
}