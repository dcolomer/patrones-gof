package comportamiento.command.swingActions.ejemplo1_4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

/**
 * Class representing Swing actions that can be associated with Command objects.
 * CommandAction inherits from Swing's AbstractAction class.
 */
public abstract class CommandAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private Action accionOpuesta;
	
	/* The Command object associated with the action. */
	protected Command command;

	/**
	 * Creates a CommandAction object.
	 * 
	 * @param command
	 *            the Command object to execute for this CommandAction.
	 * @param name
	 *            the name to be displayed on GUI components associated with
	 *            this CommandAction.
	 */
	public CommandAction(Command command, String name) {
		super(name);
		this.command = command;

		/* Register this action with the single ActionManager instance. */
		ActionManager.getInstance().add(this);
	}

	/**
	 * Creates a CommandAction object.
	 * 
	 * @param command
	 *            the Command object to execute for this CommandAction.
	 * @param name
	 *            the name to be displayed on GUI components associated with
	 *            this CommandAction.
	 * @param icon
	 *            the icon to display on GUI components associated with this
	 *            CommandAction.
	 */
	public CommandAction(Command command, String name, ImageIcon icon) {
		super(name, icon);
		this.command = command;
		ActionManager.getInstance().add(this);
	}

	/**
	 * Called automatically by the AWT when a component associated with this
	 * CommandAction is clicked (in the case of a button for example). This
	 * method executes the Command object bound to this CommandAction and then
	 * enables/disables all CommandActions, including this one, based on the
	 * effects of executing the Command object.
	 * 
	 * @param event
	 *            The ActionEvent describing the event.
	 */
	public void actionPerformed(ActionEvent event) {
		
		command.execute();
		ActionManager.getInstance().update();
		
		if (accionOpuesta != null) {
			setEnabled(false); // Se autodesactiva
			accionOpuesta.setEnabled(true); // activa la opuesta
		}		
		
	}

	/**
	 * Sets the Command object bound to this CommandAction.
	 * 
	 * @param command
	 *            the Command object to execute
	 */
	public void setCommand(Command command) {
		this.command = command;
	}

	/**
	 * Returns the Command object bound to this CommandAction object.
	 * 
	 * @return the Command object.
	 */
	public Command getCommand() {
		return command;
	}
	
	
	public Action getAccionOpuesta() {
		return accionOpuesta;
	}

	public void setAccionOpuesta(Action accionOpuesta) {
		this.accionOpuesta = accionOpuesta;
	}
}
