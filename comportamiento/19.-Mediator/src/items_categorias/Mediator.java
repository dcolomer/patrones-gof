package comportamiento.mediator.items_categorias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Mediator {

	private HashMap<String, Item> items;
	private HashMap<String, Category> categories;
	private Map<Item, ArrayList<Category>> itemCatAssoc;

	public Mediator() {
		items = new HashMap<String, Item>();
		categories = new HashMap<String, Category>();
		itemCatAssoc = new HashMap<Item, ArrayList<Category>>();
	}

	public void registerItem(Item item) {		
		if (items.containsKey(item.getDesc())) {
			throw new RuntimeException("El item " + item.getDesc() + " ya existe");
		}
		
		items.put(item.getDesc(), item);
		System.out.println("Registrado el item: " + item.getDesc());
	}
	
	public void registerCategory(Category category) {		
		if (categories.containsKey(category.getDesc())) {
			throw new RuntimeException("La categoria " + category.getDesc() + " ya existe");
		}
		
		categories.put(category.getDesc(), category);
		System.out.println("Registrada la categoria: " + category.getDesc());
	}
	
	public void add(Item item, Category category) {		
		ArrayList<Category> listCat = itemCatAssoc.get(item);
		if (listCat == null) { // No existe el item
			System.out.println("No existe en la relacion el item " + item.getDesc() + 
					". Creando la categoria " + category.getDesc() + 
					" y asociandolos.");
			listCat = new ArrayList<Category>();
			listCat.add(category);
			itemCatAssoc.put(item, listCat);	
		} else {
			System.out.println("Añadiendo nueva asociacion: " + 
					item.getDesc() + " - " + category.getDesc());
			itemCatAssoc.get(item).add(category);
		}			
	}

	public void delete(Item item, Category category) {
				
		for (Entry<Item, ArrayList<Category>> entry : itemCatAssoc.entrySet()) {
			if (entry.getValue().contains(category)) {
				entry.getValue().remove(category);
				System.out.println("Eliminada la relacion: " + 
						item.getDesc() + " - " + category.getDesc());
				return;
			}
		}
		
		System.out.println("Error. No exite la relacion: " + 
				item.getDesc() + " - " + category.getDesc());
		
	}
	
	public void delete(Item item) {
		ArrayList<Category> listCat = itemCatAssoc.get(item);
		
		if (listCat == null) { // No existe el item en la relacion
			items.remove(item.getDesc());
			System.out.println("Eliminado el item: " + item.getDesc());
		} else {
			System.out.println("No se puede eliminar el item " + item.getDesc() + 
					", pues existe una relacion con categorias.");
		}						
	}

	public void delete(Category category) {
		for (Entry<Item, ArrayList<Category>> entry : itemCatAssoc.entrySet()) {
			if (entry.getValue().contains(category)) {
				System.out.println("No se puede eliminar la categoria " + 
						category.getDesc() + ", pues existen item que la relacionan.");
				return;
			}
		}
		categories.remove(category.getDesc());
	}	
	
	public void printStatus() {
		
		System.out.println("Items existentes:");		
		for (Entry<String, Item> entry : items.entrySet()) {
			System.out.println(entry.getKey());		
		}
		
		System.out.println();
		System.out.println("Categorias existentes:");		
		for (Entry<String, Category> entry : categories.entrySet()) {
			System.out.println(entry.getKey());		
		}
				
		System.out.println();
		System.out.println("Relaciones existentes:");
		for (Entry<Item, ArrayList<Category>> entry : itemCatAssoc.entrySet()) {
			System.out.print(entry.getKey() + " -> ");			
			
			if (entry.getValue().isEmpty()) {
				System.out.println("Sin categorias!");
			} else {
				for (Category cat : entry.getValue()) {
					System.out.print(cat + " ");	
				}	
			}
			System.out.println();
		}			
	}	
}
