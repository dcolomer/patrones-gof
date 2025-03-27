package comportamiento.command.callback.client;

import comportamiento.command.callback.framework.Command;

public class CommandImpl implements Command<String> {

    @Override
    public String execute() {
        int i = (int) (Math.random() * 10);
        if (i < 5)
            throw new RuntimeException("Se ha producido un error simulado.");
        
        return String.valueOf(i);
    }

}
