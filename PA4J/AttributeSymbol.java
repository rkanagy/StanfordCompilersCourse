
public class AttributeSymbol {
	private AbstractSymbol name;	
	private AbstractSymbol type;

	public AttributeSymbol(AbstractSymbol name, AbstractSymbol type) {
		this.name = name;
		this.type = type;
	}
	
	public AbstractSymbol getName() {
		return name;
	}
	public AbstractSymbol getType() {
		return type;
	}
}
