import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;

public class TypeEnvironment {
	private ClassTable classTable;
	private class_c currentClass;
	private SymbolTable objectStore;
	
	public TypeEnvironment(ClassTable classTable) {
		this.classTable = classTable;
		this.currentClass = null;
		this.objectStore = new SymbolTable();
	}
	
	public void printSemanticError(String errorMsg) {
		if (this.currentClass == null) {
			PrintStream stream = this.classTable.semantError();
			stream.println(errorMsg);
		}
		else {
			this.printSemanticError(this.currentClass, errorMsg);
		}
	}
	
	public void printSemanticError(TreeNode node, String errorMsg) {
		AbstractSymbol fileName = this.currentClass.getFilename();
		PrintStream stream = this.classTable.semantError(fileName, node);
		stream.println(errorMsg);
	}
	
	public class_c getClass(String className) {
		return classTable.getClass(className);
	}
	
	public Hashtable<String, AttributeSymbol> getClassAttributes(String className) {
		return classTable.getClassAttributes(className);
	}
	
	public Hashtable<String, MethodSymbol> getClassMethods(String className) {
		return classTable.getClassMethods(className);
	}
	
	public class_c getClassAncestor(String className) {
		return classTable.getClassAncestor(className);
	}
	
	public List<class_c> getClassDescendants(String className) {
		return classTable.getClassDescendants(className);
	}
	
	public Stack<class_c> getClassAncestry(String className) {
		Stack<class_c> ancestry = new Stack<class_c>();
		class_c foundClass = classTable.getClass(className);
		
		while (foundClass != null) {
			ancestry.push(foundClass);
			
			String foundClassName = foundClass.getName().getString();
			if (foundClassName.equals(TreeConstants.Object_.getString())) {
				foundClass = null;
			} else {
				foundClass = classTable.getClassAncestor(foundClassName);
			}
		}
		
		return ancestry;
	}
	
	public Boolean isConformable(AbstractSymbol fromClass, AbstractSymbol toClass) {
		String fromClassName = fromClass.getString();
		String toClassName = toClass.getString();
		
//		if (fromClassName.equals(TreeConstants.SELF_TYPE.getString())) {
//			fromClassName = this.currentClass.getName().getString();
//		}
//		if (toClassName.equals(TreeConstants.SELF_TYPE.getString())) {
//			toClassName = this.currentClass.getName().getString();
//		}
		
		Stack<class_c> ancestry = getClassAncestry(fromClassName);
		Boolean toClassFound = false;
		while (!ancestry.empty()) {
			class_c coolClass = ancestry.pop();
			if (coolClass.getName().getString().equals(toClassName)) {
				toClassFound = true;
				break;
			}
		}
		
		return toClassFound;
	}
	
	public AbstractSymbol CalculateLeastType(List<AbstractSymbol> types) {
		return classTable.CalculateLeastType(types);
	}
	
	public class_c getCurrentClass() {
		return this.currentClass;
	}
	public void setCurrentClass(class_c currentClass) {
		this.currentClass = currentClass;
	}
	
	public void enterObjectScope() {
		this.objectStore.enterScope();
	}
	
	public void exitObjectScope() {
		this.objectStore.exitScope();
	}
	
	public void addIdToObjectStore(AbstractSymbol id, Object obj) {
		this.objectStore.addId(id, obj);
	}
	
	public Object lookupObjectStore(AbstractSymbol id) {
		return this.objectStore.lookup(id);
	}
	
	public Object probeObjectStore(AbstractSymbol id) {
		return this.objectStore.probe(id);
	}
	
	public void addClassAttributesToObjectScope(String className) {
		Hashtable<String, AttributeSymbol> classAttributes = classTable.getClassAttributes(className);
		if (classAttributes != null) {
			for (Enumeration e = classAttributes.elements(); e.hasMoreElements(); ) {
				AttributeSymbol attribute = (AttributeSymbol)e.nextElement();
				this.addIdToObjectStore(attribute.getName(), attribute.getType());
			}
		}
	}
/*
	public void addMethod(method classMethod) {
		String className = this.currentClass.getName().getString();
		String methodName = classMethod.getName().getString();
		Hashtable<String, method> classMethods = methodStore.get(className);
		if (classMethods == null) {
			classMethods = new Hashtable<String, method>();
		}

		method existingMethod = classMethods.get(methodName);
		if (existingMethod == null) {
			classMethods.put(methodName, classMethod);
			methodStore.put(className, classMethods);
		} else {
			printSemanticError(classMethod, "Method " + methodName + " is multiply defined");
		}
	}
*/	
	// get method for class or closest ancestor
	public MethodSymbol getMethod(String className, String methodName) {
		MethodSymbol foundMethod = null;
		class_c foundClass = classTable.getClass(className);
		
		while (foundClass != null) {
			String foundClassName = foundClass.getName().getString();
			Hashtable<String, MethodSymbol> classMethods = classTable.getClassMethods(foundClassName);
			if (classMethods != null) {
				foundMethod = classMethods.get(methodName);
				if (foundMethod != null) break;
			}
			
			if (foundClass.getName().getString().equals(TreeConstants.Object_.getString())) {
				// We are at the top of the object hierarchy
				foundClass = null;
			} else {
				foundClass = classTable.getClassAncestor(foundClassName);
			}			
		}
		
		return foundMethod;
	}
}
