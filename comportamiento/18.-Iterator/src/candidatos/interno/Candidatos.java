package comportamiento.iterator.candidatos.interno;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Candidatos implements Iterator<Candidato> {
	private Vector<Candidato> candidatos;
	private Enumeration<Candidato> enumCandidatos;
	private Candidato siguienteCandidato;

	public Candidatos() {
		inicializar();
		enumCandidatos = candidatos.elements();
	}

	private void inicializar() {

		candidatos = new Vector<Candidato>();

		// Recuperar los datos desde el fichero de texto.
		Vector<String> filas = fileToVector("candidatos.txt");

		/*
		 * Crear objetos Candidato a partir del vector de String's y añadirlos
		 * al Vector datos
		 */
		for (int i = 0; i < filas.size(); i++) {
			String str = (String) filas.elementAt(i);
			/*
			 * String[] token = str.split(","); datos.add(new
			 * Candidato(token[0],token[1],token[2]));
			 */
			StringTokenizer st = new StringTokenizer(str, ",");
			candidatos.add(new Candidato(st.nextToken(), st.nextToken(), st
					.nextToken()));
		}
	}

	public boolean hasNext() {
		siguienteCandidato = null;

		while (enumCandidatos.hasMoreElements()) {
			Candidato tempObj = enumCandidatos.nextElement();
			siguienteCandidato = tempObj;
			break;
		}
		return siguienteCandidato != null;
	}

	public Candidato next() {
		if (siguienteCandidato == null) {
			throw new NoSuchElementException();
		} else {
			return siguienteCandidato;
		}
	}

	public void remove() {
		
	}

	/*
	 * Metodo que lee datos de un fichero dado y los devuelve en un Vector
	 */
	private Vector fileToVector(String fileName) {
		Vector v = new Vector();
		String inputLine;
		try {
			File inFile = new File(fileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(inFile)));

			while ((inputLine = br.readLine()) != null) {
				v.addElement(inputLine.trim());
			}
			br.close();
		}
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			//
		}
		return v;
	}
}
