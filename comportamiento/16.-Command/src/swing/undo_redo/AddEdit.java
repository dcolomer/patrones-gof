package comportamiento.command.swing.undo_redo;

import javax.swing.DefaultListModel;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

/**
 * The Add Edit class record the changes occured to the list after performing an
 * add action. The add edit support undo / redo of add action.
 * 
 * @author Tomer Meshorer
 */

public class AddEdit extends AbstractUndoableEdit {

	private static final long serialVersionUID = 1L;
	
	private Object elemento;
	private int posicion;
	private DefaultListModel modelo;

	public AddEdit(DefaultListModel modelo, Object elemento, int posicion) {
		this.modelo = modelo;
		this.elemento = elemento;
		this.posicion = posicion;
	}

	@Override
	public void undo() throws CannotUndoException {
		modelo.removeElementAt(posicion);
	}

	@Override
	public void redo() throws CannotRedoException {
		modelo.insertElementAt(elemento, posicion);
	}

	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public boolean canRedo() {
		return true;
	}

	@Override
	public String getPresentationName() {
		return "Add";
	}

}
