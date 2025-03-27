package comportamiento.mediator.items_categorias;

public class MainClient {
	
	private Mediator mediator = new Mediator();
	private ItemManager manager = new ItemManager(); // invoker
	
	public MainClient() {						
		Item itemBeautiful = createItem("A Beautiful Mind");
		Category catCD = createCategory("CD");
		Command cmd = new AddCommand(itemBeautiful, catCD);
		manager.setCommand(cmd).process();
				
		Category catDVD = createCategory("DVD");
		cmd = new AddCommand(itemBeautiful, catDVD);
		manager.setCommand(cmd).process();
				
		Item itemDuet = createItem("Duet");		
		cmd = new AddCommand(itemDuet, catCD);
		manager.setCommand(cmd).process();
		cmd = new AddCommand(itemDuet, catDVD);
		manager.setCommand(cmd).process();
			
		Item itemTutuuu = createItem("Tutuuu");
		Category catNewRealese = createCategory("New Releases");
		cmd = new AddCommand(itemTutuuu, catNewRealese);
		manager.setCommand(cmd).process();
		
		// Delete the itemBeautiful from the DVD category
		cmd = new DeleteCommand(itemBeautiful, catDVD);
		manager.setCommand(cmd).process();
		
		mediator.printStatus();
	}

	public static void main(String[] args) {
		new MainClient();
	}
	
	private Category createCategory(String categoryName) {
		return new Category(categoryName, mediator);
	}
	
	private Item createItem(String itemName) {		
		return new Item(itemName, mediator);
	}
	
}
