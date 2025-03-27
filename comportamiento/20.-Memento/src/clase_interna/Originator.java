package comportamiento.memento.clase_interna;

public class Originator {

    private int state;

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setMemento(Object object) {
    	Memento memento = (Memento) object;
        state = memento.state;
    }

    public Object getMemento() {
        return new Memento(state);
    }

    private static class Memento {

        private int state;

        private Memento(int state) {
            this.state = state;
        }
    }
}
