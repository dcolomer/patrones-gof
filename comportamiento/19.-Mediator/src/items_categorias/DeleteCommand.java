package comportamiento.mediator.items_categorias;

public class DeleteCommand implements Command {
	Item item;
	Category cat;

	public DeleteCommand(Item i, Category c) {
		item = i;
		cat = c;
	}

	public void execute() {
		item.getMediator().delete(item, cat);
	}
}
