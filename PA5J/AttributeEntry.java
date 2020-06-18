
public class AttributeEntry {
	private AbstractSymbol name;
	private AbstractSymbol dataType;
	private String defaultValue;
	private Integer objectOffset;
	
	public AttributeEntry(AbstractSymbol name, AbstractSymbol dataType, String defaultValue, Integer offset) {
		this.name = name;
		this.dataType = dataType;
		this.defaultValue = defaultValue;
		this.objectOffset = offset;
	}
	
	public AbstractSymbol getName() {
		return name;
	}
	public void setName(AbstractSymbol name) {
		this.name = name;
	}
	public AbstractSymbol getDataType() {
		return dataType;
	}
	public void setDataType(AbstractSymbol dataType) {
		this.dataType = dataType;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public Integer getObjectOffset() {
		return objectOffset;
	}
	public void setObjectOffset(Integer objectOffset) {
		this.objectOffset = objectOffset;
	}
}
