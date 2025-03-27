package comportamiento.mediator.items_categorias;

public class AddCommand implements Command {
	Item item;
	Category cat;

	public AddCommand(Item i, Category c) {
		item = i;
		cat = c;
	}

	public void execute() {
		item.getMediator().add(item, cat);
	}
}
