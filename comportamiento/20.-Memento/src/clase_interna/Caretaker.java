package comportamiento.memento.clase_interna;

import java.util.Stack;

public class Caretaker {

    private Stack<Object> history = new Stack<Object>();
    private Originator originator;

    public Caretaker(Originator originator) {
        this.originator = originator;
    }

    public void save() {
        history.push(originator.getMemento());
    }

    public void restore() {
        originator.setMemento(history.pop());
    }

}