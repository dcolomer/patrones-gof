package comportamiento.mediator.items_categorias;

public class Item {
	private Mediator mediator;
	private String desc;

	public Item(String s, Mediator mediator) {
		desc = s;
		this.mediator = mediator;
		this.mediator.registerItem(this);	
	}

	public String getDesc() {
		return desc;
	}

	public Mediator getMediator() {		
		return mediator;
	}

	@Override
	public String toString() {
		return desc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		return true;
	}
	
}
