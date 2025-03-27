package comportamiento.command.swingActions.ejemplo1_4;

import java.awt.Component;
import java.util.Vector;

/**
 * A manager class for all ActionCommand objects. When created, an ActionCommand
 * object is automatically registered with the ActionManager. ActionManager is a
 * singleton class.
 */
public class ActionManager {

	/* The single instance of ActionManager. */
	private static ActionManager theInstance = null;

	/* The collection of CommandAction objects known to the ActionManager. */
	private Vector<CommandAction> actions;

	private Component focus;

	/**
	 * Returns an ActionManager instance. This method returns a reference to the
	 * same ActionManager object, in keeping with the Singleton pattern.
	 * 
	 * @return the single ActionManager instance.
	 */
	public static ActionManager getInstance() {
		if (theInstance == null)
			theInstance = new ActionManager();
		return theInstance;
	}

	/* Hidden constructor - can't be called by clients. */
	protected ActionManager() {
		actions = new Vector<CommandAction>();
	}

	/**
	 * Registers a CommandAction object with the ActionManager.
	 * 
	 * @param action
	 *            the CommandAction object.
	 */
	public void add(CommandAction action) {
		actions.add(action);
	}

	/**
	 * Iterates through the set of CommandAction objects known to the
	 * ActionManager and enables/disables each according to whether the Command
	 * object associated with each CommandAction instance is currently
	 * executable.
	 */
	public void update() {
		for (CommandAction a : actions) {
			if (a.getAccionOpuesta() == null) {
				a.setEnabled(a.getCommand().canExecute());
			}
		}
		if (focus != null) {
			focus.requestFocusInWindow();
		}
	}

	/**
	 * Sets the component to focus on following a call to update().
	 * 
	 * @param component
	 *            the component to focus on.
	 */
	public void setDefaultFocusOn(Component component) {
		focus = component;
	}
}
