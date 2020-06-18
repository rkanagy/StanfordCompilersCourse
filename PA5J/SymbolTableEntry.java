
public class SymbolTableEntry {
	private Integer offset;
	private OffsetBaseType offsetBaseType;
	private AbstractSymbol typeDecl;
	
	public SymbolTableEntry(Integer offset, OffsetBaseType offsetBaseType, AbstractSymbol typeDecl) {
		this.offset = offset;
		this.typeDecl = typeDecl;
		this.offsetBaseType = offsetBaseType;
	}
	
	public Integer getOffset() {
		return offset;
	}
	public OffsetBaseType getOffsetBaseType() {
		return offsetBaseType;
	}
	public AbstractSymbol getTypeDecl() {
		return typeDecl;
	}
}
