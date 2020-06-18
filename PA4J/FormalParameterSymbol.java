
public class FormalParameterSymbol {
	private AbstractSymbol name;
	private AbstractSymbol type;
	
	public FormalParameterSymbol(AbstractSymbol name, AbstractSymbol type) {
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
