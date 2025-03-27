package comportamiento.mediator.mensajeria;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MediatorImpl implements Mediator {

    private Map<String, Colleague> map = new HashMap<String, Colleague>();

    @Override
    public void register(Colleague colleague) {
        map.put(colleague.getName(), colleague);
    }

    @Override
    public void send(String sender, String receiver, String message) {
        Colleague colleague = map.get(receiver);
        if (colleague != null)            
        	notify(colleague, sender, message);
    }
    
    @Override
    public void broadcast(String sender, String message) {
        for (Entry<String, Colleague> colleague : map.entrySet()) {
        	notify(colleague.getValue(), sender, message);	
        }        
    }
    
    private void notify(Colleague colleague, String sender, String message) {
		System.out.println(colleague.getName() + " recibio de " + sender
				+ " el mensaje \"" + message + "\"");
	}
    
}
