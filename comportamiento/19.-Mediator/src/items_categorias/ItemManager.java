package comportamiento.mediator.items_categorias;

public class ItemManager {
	Command command;

	public ItemManager setCommand(Command c) {
		command = c;
		return this;
	}

	public void process() {
		command.execute();
	}
}
