package comportamiento.command.callback.framework;

public interface Callback<T> {

    void onSuccess(T message);
    void onError(Throwable t);

}
