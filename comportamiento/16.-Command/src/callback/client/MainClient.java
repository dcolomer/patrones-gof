package comportamiento.command.callback.client;

import comportamiento.command.callback.framework.Command;
import comportamiento.command.callback.framework.Invoker;

public class MainClient {

	public static void main(String[] args) {
		
		Invoker invoker = new Invoker();
		
		Command<String> comando = new CommandImpl();
		
		invoker.execute(comando, new CommandImplCallback() {
		    @Override
		    public void onSuccess(String msg) {
		        System.out.println("El comando se ha ejecutado correctamente, produciendo un resultado de \"" + msg + "\"");
		    }
		});
	}
}
