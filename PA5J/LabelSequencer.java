
public class LabelSequencer {
	private static LabelSequencer instance = null;
	private Integer labelNumber = -1;
	
	private LabelSequencer() {
	}
	
	public static LabelSequencer getInstance() {
		if (instance == null) {
			instance = new LabelSequencer();
		}
		
		return instance;
	}
	
	public Integer getLabelNumber() {
		labelNumber++;
		return labelNumber;
	}
}
