package comportamiento.observer.reports;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class Utilidades {

	public static int getMesEnCurso() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.get(Calendar.MONTH) + 1;
	}
	
	/*
	 * Lee datos de un fichero y los devuelve en un vector de String
	 */
	public static Vector<String> fileToVector(String fileName) {
		URL url = Utilidades.class.getResource(
				"/comportamiento/observer/reports/" + fileName);

		Vector<String> v = new Vector<String>();
		String inputLine;
		try {

			File inFile = new File(url.toURI().getPath());
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(inFile)));

			while ((inputLine = br.readLine()) != null) {
				v.add(inputLine.trim());
			}
			br.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
		}
		return (v);
	}
}
