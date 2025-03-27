package estructurales.facade.subsistema;

import java.io.*;
import java.util.*;

/*
 * Clase de utilidad para leer y escribir datos
 * mediante ficheros de texto. 
 */
public class UtilidadesFichero {

	DataOutputStream dos;

	/*
	 * Escribir texto en un fichero
	 */
	public boolean writeToFile(String fileName, 
			String dataLine, boolean isAppendMode, boolean isNewLine) 
	{
		if (isNewLine) {
			dataLine = "\n" + dataLine;
		}

		try {
			File outFile = new File(fileName);
			if (isAppendMode) {
				dos = new DataOutputStream(new FileOutputStream(fileName, true));
			} else {
				dos = new DataOutputStream(new FileOutputStream(outFile));
			}

			dos.writeBytes(dataLine);
			dos.close();
		} catch (FileNotFoundException ex) {
			return false;
		} catch (IOException ex) {
			return false;
		}
		return true;

	}

	/*
	 * Leer con buffer datos de un fichero
	 */
	public String readFromFile(String fileName) {
		String DataLine = "";
		try {
			File inFile = new File(fileName);
			BufferedReader br = 
				new BufferedReader(
						new InputStreamReader(
								new FileInputStream(inFile)));

			DataLine = br.readLine();
			br.close();
		} catch (FileNotFoundException ex) {
			return null;
		} catch (IOException ex) {
			return null;
		}
		return (DataLine);

	}

	public boolean isFileExists(String fileName) {
		File file = new File(fileName);
		return file.exists();
	}

	public boolean deleteFile(String fileName) {
		File file = new File(fileName);
		return file.delete();
	}

	/*
	 * Leer datos de un fichero y almacenar cada fila 
	 * como elemento de un vector.
	 */
	public Vector<String> fileToVector(String fileName) {
		Vector<String> v = new Vector<String>();
		String inputLine;
		try {
			File inFile = new File(fileName);
			BufferedReader br = 
				new BufferedReader(
						new InputStreamReader(
								new FileInputStream(inFile)));

			while ((inputLine = br.readLine()) != null) {
				v.addElement(inputLine.trim());
			}
			br.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return v;
	}

	/*
	 * Leer los elementos de un vector y guardarlos en un fichero
	 */
	public void vectorToFile(Vector<Object> elementos, String fileName) {
		
		for (Object o : elementos) {
			writeToFile(fileName, String.valueOf(o), true, true);
		}
		/*for (int i = 0; i < elementos.size(); i++) {
			writeToFile(fileName, (String) elementos.elementAt(i), true, true);
		}*/
	}

}
