package creacionales.singleton.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import creacionales.singleton.SingletonConClaseInterna;

public class MainClientConClaseInterna {

	public static void main(String[] args) throws Exception {
		SingletonConClaseInterna INSTANCE = 
			SingletonConClaseInterna.getInstance();
		
		ObjectOutputStream oos = 
			new ObjectOutputStream(
				new FileOutputStream("singletonConClaseInterna.ser"));
		oos.writeObject(INSTANCE);
		oos.close();
		ObjectInputStream ois = 
			new ObjectInputStream(
				new FileInputStream("singletonConClaseInterna.ser"));
		SingletonConClaseInterna test = 
			(SingletonConClaseInterna) ois.readObject();
		ois.close();
		
		System.out.println(test == INSTANCE);
	}
}
