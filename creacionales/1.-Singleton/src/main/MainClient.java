package creacionales.singleton.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import creacionales.singleton.Singleton;

public class MainClient {

	public static void main(String[] args) throws Exception {
		Singleton INSTANCE = Singleton.getInstance();
		ObjectOutputStream oos = 
			new ObjectOutputStream(
				new FileOutputStream("singleton.ser"));
		oos.writeObject(INSTANCE);
		oos.close();
		ObjectInputStream ois = 
			new ObjectInputStream(
				new FileInputStream("singleton.ser"));
		Singleton test = (Singleton) ois.readObject();
		ois.close();
		System.out.println(test == INSTANCE);
	}
}
