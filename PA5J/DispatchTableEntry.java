import java.util.Vector;

public class DispatchTableEntry {
	private String methodName;
	public String getMethodName() {
		return methodName;
	}

	private String className;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	private Vector<FormalParmEntry> formalParms;
	public Vector<FormalParmEntry> getFormalParms() {
		return formalParms;
	}

	public DispatchTableEntry(String methodName, String className, Vector<FormalParmEntry> formalParms) {
		this.methodName = methodName;
		this.className = className;
		this.formalParms = formalParms;
	}
}
