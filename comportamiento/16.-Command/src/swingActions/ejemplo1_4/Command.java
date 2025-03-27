package comportamiento.command.swingActions.ejemplo1_4;

/**
 * Interface representing commands, based on the Command design pattern.
 */
public interface Command {

	/**
	 * Executes the command.
	 */
	public void execute();
	
	/**
	 * Determines if the command can currently execute.
	 * @return true if the command is currently executable, false otherwise.
	 */
	public boolean canExecute();
}
