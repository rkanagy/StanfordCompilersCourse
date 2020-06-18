import java.util.ArrayList;

public class MethodSymbol {
	private AbstractSymbol name;
	private AbstractSymbol returnType;
	private ArrayList<FormalParameterSymbol> formals;
	
	public MethodSymbol(AbstractSymbol name, AbstractSymbol returnType, ArrayList<FormalParameterSymbol> formals) {
		this.name = name;
		this.returnType = returnType;
		this.formals = formals;
	}
	
	public AbstractSymbol getName() {
		return name;
	}
	public AbstractSymbol getReturnType() {
		return returnType;
	}
	public ArrayList<FormalParameterSymbol> getFormals() {
		return formals;
	}
}
