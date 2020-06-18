
public class FormalParmEntry {
	private AbstractSymbol parmName;
	private AbstractSymbol parmType;
	
	public AbstractSymbol getParmName() {
		return this.parmName;
	}
	
	public AbstractSymbol getParmType() {
		return this.parmType;
	}
	
	public FormalParmEntry(AbstractSymbol parmName, AbstractSymbol parmType) {
		this.parmName = parmName;
		this.parmType = parmType;
	}
}
