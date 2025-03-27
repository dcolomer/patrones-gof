package creacionales.singleton.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import creacionales.singleton.SingletonEnum;

public class MainClientEnum {

	public static void main(String[] args) throws Exception {
		SingletonEnum INSTANCE = SingletonEnum.instance;
		
		INSTANCE.imprimirBebidas();
		
		ObjectOutputStream oos = 
			new ObjectOutputStream(
				new FileOutputStream("singletonEnum.ser"));
		oos.writeObject(INSTANCE);
		oos.close();
		ObjectInputStream ois = 
			new ObjectInputStream(
				new FileInputStream("singletonEnum.ser"));
		SingletonEnum test = (SingletonEnum) ois.readObject();
		ois.close();
		
		System.out.println(test == INSTANCE);
	}
}
