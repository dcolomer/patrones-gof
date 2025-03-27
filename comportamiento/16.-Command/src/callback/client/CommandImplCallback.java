package comportamiento.command.callback.client;

import comportamiento.command.callback.framework.Callback;

public abstract class CommandImplCallback implements Callback<String> {

    @Override
    public void onError(Throwable t) {
        t.printStackTrace(System.err);
    }

}