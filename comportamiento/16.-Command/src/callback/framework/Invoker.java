package comportamiento.command.callback.framework;

public class Invoker {

	public <T> void execute(Command<T> command, Callback<T> callback) {
		try {
			T response = command.execute();
			callback.onSuccess(response);
		} catch (Throwable t) {
			callback.onError(t);
		}
	}
}
