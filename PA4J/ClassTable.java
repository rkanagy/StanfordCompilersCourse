import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

/** This class may be used to contain the semantic information such as
 * the inheritance graph.  You may use it or not as you like: it is only
 * here to provide a container for the supplied methods.  */
class ClassTable {
    private int semantErrors;
    private PrintStream errorStream;
    private Graph<class_c> inheritanceGraph;
    private Classes classes;
    private Hashtable<String, Hashtable<String, AttributeSymbol>> classAttributes;
    private Hashtable<String, Hashtable<String, MethodSymbol>> classMethods;
    
    /** Creates data structures representing basic Cool classes (Object,
     * IO, Int, Bool, String).  Please note: as is this method does not
     * do anything useful; you will need to edit it to make if do what
     * you want.
     * */
    private void installBasicClasses() {
	AbstractSymbol filename 
	    = AbstractTable.stringtable.addString("<basic class>");
	
	// The following demonstrates how to create dummy parse trees to
	// refer to basic Cool classes.  There's no need for method
	// bodies -- these are already built into the runtime system.

	// IMPORTANT: The results of the following expressions are
	// stored in local variables.  You will want to do something
	// with those variables at the end of this method to make this
	// code meaningful.

	// The Object class has no parent class. Its methods are
	//        cool_abort() : Object    aborts the program
	//        type_name() : Str        returns a string representation 
	//                                 of class name
	//        copy() : SELF_TYPE       returns a copy of the object

	class_c Object_class = 
	    new class_c(0, 
		       TreeConstants.Object_, 
		       TreeConstants.No_class,
		       new Features(0)
			   .appendElement(new method(0, 
					      TreeConstants.cool_abort, 
					      new Formals(0), 
					      TreeConstants.Object_, 
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.type_name,
					      new Formals(0),
					      TreeConstants.Str,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.copy,
					      new Formals(0),
					      TreeConstants.SELF_TYPE,
					      new no_expr(0))),
		       filename);
	
	// The IO class inherits from Object. Its methods are
	//        out_string(Str) : SELF_TYPE  writes a string to the output
	//        out_int(Int) : SELF_TYPE      "    an int    "  "     "
	//        in_string() : Str            reads a string from the input
	//        in_int() : Int                "   an int     "  "     "

	class_c IO_class = 
	    new class_c(0,
		       TreeConstants.IO,
		       TreeConstants.Object_,
		       new Features(0)
			   .appendElement(new method(0,
					      TreeConstants.out_string,
					      new Formals(0)
						  .appendElement(new formalc(0,
								     TreeConstants.arg,
								     TreeConstants.Str)),
					      TreeConstants.SELF_TYPE,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.out_int,
					      new Formals(0)
						  .appendElement(new formalc(0,
								     TreeConstants.arg,
								     TreeConstants.Int)),
					      TreeConstants.SELF_TYPE,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.in_string,
					      new Formals(0),
					      TreeConstants.Str,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.in_int,
					      new Formals(0),
					      TreeConstants.Int,
					      new no_expr(0))),
		       filename);

	// The Int class has no methods and only a single attribute, the
	// "val" for the integer.

	class_c Int_class = 
	    new class_c(0,
		       TreeConstants.Int,
		       TreeConstants.Object_,
		       new Features(0)
			   .appendElement(new attr(0,
					    TreeConstants.val,
					    TreeConstants.prim_slot,
					    new no_expr(0))),
		       filename);

	// Bool also has only the "val" slot.
	class_c Bool_class = 
	    new class_c(0,
		       TreeConstants.Bool,
		       TreeConstants.Object_,
		       new Features(0)
			   .appendElement(new attr(0,
					    TreeConstants.val,
					    TreeConstants.prim_slot,
					    new no_expr(0))),
		       filename);

	// The class Str has a number of slots and operations:
	//       val                              the length of the string
	//       str_field                        the string itself
	//       length() : Int                   returns length of the string
	//       concat(arg: Str) : Str           performs string concatenation
	//       substr(arg: Int, arg2: Int): Str substring selection

	class_c Str_class =
	    new class_c(0,
		       TreeConstants.Str,
		       TreeConstants.Object_,
		       new Features(0)
			   .appendElement(new attr(0,
					    TreeConstants.val,
					    TreeConstants.Int,
					    new no_expr(0)))
			   .appendElement(new attr(0,
					    TreeConstants.str_field,
					    TreeConstants.prim_slot,
					    new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.length,
					      new Formals(0),
					      TreeConstants.Int,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.concat,
					      new Formals(0)
						  .appendElement(new formalc(0,
								     TreeConstants.arg, 
								     TreeConstants.Str)),
					      TreeConstants.Str,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.substr,
					      new Formals(0)
						  .appendElement(new formalc(0,
								     TreeConstants.arg,
								     TreeConstants.Int))
						  .appendElement(new formalc(0,
								     TreeConstants.arg2,
								     TreeConstants.Int)),
					      TreeConstants.Str,
					      new no_expr(0))),
		       filename);

	/* Do somethind with Object_class, IO_class, Int_class,
           Bool_class, and Str_class here */
		classes.addElement(Object_class);
		classes.addElement(IO_class);
		classes.addElement(Int_class);
		classes.addElement(Bool_class);
		classes.addElement(Str_class);
    }
	

    public ClassTable(Classes cls) {
    	semantErrors = 0;
    	errorStream = System.err;
    	inheritanceGraph = new Graph<class_c>();
    	classAttributes = new Hashtable<String, Hashtable<String, AttributeSymbol>>();
    	classMethods = new Hashtable<String, Hashtable<String, MethodSymbol>>();
    	
    	/* fill this in */
    	if (classes == null) classes = new Classes(0);
    	this.installBasicClasses();
    	for (Enumeration c = cls.getElements(); c.hasMoreElements(); ) {
    		class_c coolClass = (class_c)c.nextElement();
    		classes.appendElement(coolClass);
    	}
//    	Boolean built = this.buildInheritanceGraph();
//    	if (built) {
//        	this.buildFeatureTables();
//    	}
    }

    /** Prints line number and file name of the given class.
     *
     * Also increments semantic error count.
     *
     * @param c the class
     * @return a print stream to which the rest of the error message is
     * to be printed.
     *
     * */
    public PrintStream semantError(class_c c) {
	return semantError(c.getFilename(), c);
    }

    /** Prints the file name and the line number of the given tree node.
     *
     * Also increments semantic error count.
     *
     * @param filename the file name
     * @param t the tree node
     * @return a print stream to which the rest of the error message is
     * to be printed.
     *
     * */
    public PrintStream semantError(AbstractSymbol filename, TreeNode t) {
	errorStream.print(filename + ":" + t.getLineNumber() + ": ");
	return semantError();
    }

    /** Increments semantic error count and returns the print stream for
     * error messages.
     *
     * @return a print stream to which the error message is
     * to be printed.
     *
     * */
    public PrintStream semantError() {
	semantErrors++;
	return errorStream;
    }

    /** Returns true if there are any static semantic errors. */
    public boolean errors() {
	return semantErrors != 0;
    }
    
    private Boolean buildInheritanceGraph() {
    	Boolean success = true;
    	
    	// First, create vertices of graph from defined classes
    	for (Enumeration e = classes.getElements(); e.hasMoreElements(); ) {
    		class_c coolClass = (class_c)e.nextElement();
    		AbstractSymbol classSymbol = coolClass.getName();
    		
    		// add vertex for current class, if not already existing
    		String className = classSymbol.getString();
    		if (className.equals(TreeConstants.SELF_TYPE.getString())) {
    			success = false;
    			printRedifinitionOfSelfTypeError(coolClass);
    		}
    		Vertex<class_c> classVertex = this.inheritanceGraph.findVertex(className);
    		if (classVertex == null) {
        		classVertex = new Vertex<class_c>(className, coolClass);
        		inheritanceGraph.addVertex(classVertex);
    		} else {
    			success = false;
    			printClassAlreadyDefinedError(coolClass);
    		}
    	}

    	// Next, add nodes for primitive classes
    	class_c primSlotClass = new class_c(0, TreeConstants.prim_slot, null, new Features(0), null);
    	class_c selfTypeClass = new class_c(0, TreeConstants.SELF_TYPE, null, new Features(0), null);
    	class_c noClassClass = new class_c(0, TreeConstants.No_class, null, new Features(0), null);
    	Vertex<class_c> primSlotVertex = new Vertex<class_c>(TreeConstants.prim_slot.getString(), primSlotClass);
    	Vertex<class_c> selfTypeVertex = new Vertex<class_c>(TreeConstants.SELF_TYPE.getString(), selfTypeClass);
    	Vertex<class_c> noClassVertex = new Vertex<class_c>(TreeConstants.No_class.getString(), noClassClass);
    	inheritanceGraph.addVertex(primSlotVertex);
    	inheritanceGraph.addVertex(selfTypeVertex);
    	inheritanceGraph.addVertex(noClassVertex);
    	
    	// Then, create edges using the inheritance information for each class
    	for (Enumeration e = classes.getElements(); e.hasMoreElements(); ) {
    		class_c coolClass = (class_c)e.nextElement();
    		AbstractSymbol classSymbol = coolClass.getName();
    		AbstractSymbol classParentSymbol = coolClass.getParent();
    		
    		String className = classSymbol.getString();
    		Vertex<class_c> classVertex = this.inheritanceGraph.findVertex(className);
    		
    		String parentName = classParentSymbol.getString();
    		if (!canInheritFromClass(parentName)) {
    			success = false;
    			printCantInheritParentClass(coolClass, className, parentName);
    		} else {
        		Boolean atHierarchyTop = parentName.equals(TreeConstants.No_class.getString());
        		if (!atHierarchyTop)
        		{
        			Vertex<class_c> parentVertex = this.inheritanceGraph.findVertex(parentName);
        			if (parentVertex != null) {
    	    			inheritanceGraph.addEdge(classVertex, parentVertex);
        			} else {
        				success = false;
            			printParentClassNotFoundError(coolClass, parentName);
        			}
        		}
    		}
        }
    	
    	if (!success) this.inheritanceGraph = new Graph<class_c>();
    	return success;
    }
    
    private Boolean canInheritFromClass(String className) {
    	String intName = TreeConstants.Int.getString();
    	String boolName = TreeConstants.Bool.getString();
    	String stringName = TreeConstants.Str.getString();
    	String selfTypeName = TreeConstants.SELF_TYPE.getString();
    	
    	return (!(className.equals(intName) || className.equals(boolName) || 
    			className.equals(stringName) || className.equals(selfTypeName)));
    }
    private void printInheritanceCycleError(class_c coolClass)
    {
    	String className = coolClass.getName().getString();
		PrintStream errorStream = this.semantError(coolClass);
		errorStream.println("Class " + className + ", or an ancestor of " + className + ", is involved in an inheritance cycle.");
    }
    
    private void printParentClassNotFoundError(class_c coolClass, String parentClassName) {
    	String className = coolClass.getName().getString();
		PrintStream errorStream = this.semantError(coolClass);
		errorStream.println("Class " + className + " inherits from an undefined class " + parentClassName +".");
    }
    
    private void printClassAlreadyDefinedError(class_c coolClass) {
    	String className = coolClass.getName().getString();
		PrintStream errorStream = this.semantError(coolClass);
		errorStream.println("Class " + className + " was previously defined.");
    }
    
    private void printClassMainNotDefinedError() {
    	PrintStream errorStream = this.semantError();
    	errorStream.println("Class Main is not defined.");
    }
    
    private void printNoMainMethodInClassMainError(class_c classMain) {
    	PrintStream errorStream = this.semantError(classMain);
    	errorStream.println("No 'main' method in class Main.");
    }
    
    private void printMainMethodHasArgumentsError(class_c classMain) {
    	PrintStream errorStream = this.semantError(classMain);
    	errorStream.println("'main' method in class Main should have no arguments.");
    }
    
    private void printCantInheritParentClass(class_c coolClass, String className, String parentName) {
		PrintStream errorStream = this.semantError(coolClass);
		errorStream.println("Class " + className +" cannot inherit class " + parentName + ".");
    }

    private void printRedifinitionOfSelfTypeError(class_c coolClass) {
    	PrintStream errorStream = this.semantError(coolClass);
    	errorStream.println("Redefinition of basic class SELF_TYPE.");
    }
    
    public ArrayList<String> getClassAncestryTypes(String className) {
    	ArrayList<String> ancestryTypes = new ArrayList<String>();
    	String foundClassName = className;
    	
    	while (foundClassName != null) {
    		if (!foundClassName.equals(className)) { // don't add start class to list
    			ancestryTypes.add(foundClassName);
    		}
    		
    		if (foundClassName.equals(TreeConstants.Object_.getString())) {
    			foundClassName = null;
    		} else {
    			class_c foundClass = this.getClassAncestor(foundClassName);
    			foundClassName = foundClass.getName().getString();
    		}
    		
    	}
    	
    	return ancestryTypes;
    }
    
    public Boolean isClassHierarchyWellDefined() {
    	// build inheritance graph for class hierarchy
    	Boolean built = this.buildInheritanceGraph();
    	if (!built) return false;
    	
    	// check for cycles in class hierarchy
    	Boolean hasCycle = false;
    	for (Enumeration<Vertex<class_c>> e = this.inheritanceGraph.getVertices(); e.hasMoreElements(); ) {
			Vertex<class_c> vertex = (Vertex<class_c>)e.nextElement();
    		if (!vertex.isVisited() && this.inheritanceGraph.hasCycle(vertex)) {
				hasCycle = true;
				class_c coolClass = vertex.getAdditionalData();
				this.printInheritanceCycleError(coolClass);
			}
		}
    	if (hasCycle) return false;
    	
    	return true;
    }
    
    public AbstractSymbol CalculateLeastType(List<AbstractSymbol> types) {
		if (types.size() == 0) return null;
		
		// this loop algorithm will work for 1 or more types in list
		AbstractSymbol first = types.get(0);
		for(Integer i = 1; i < types.size(); i++) {
			AbstractSymbol second = types.get(i);
			first = CalculateLeastType(first, second);
		}
		
		return first;
    }
    
    private AbstractSymbol CalculateLeastType(AbstractSymbol first, AbstractSymbol second) {
    	inheritanceGraph.setVerticesNotVisited();
    	
    	Vertex<class_c> firstVertex = this.inheritanceGraph.findVertex(first.getString());
    	inheritanceGraph.findVisitedVertexInHierarchy(firstVertex);
    	
    	Vertex<class_c> secondVertex = this.inheritanceGraph.findVertex(second.getString());
    	class_c leastTypeClass = inheritanceGraph.findVisitedVertexInHierarchy(secondVertex);
    	
    	return leastTypeClass.getName();
    }
    
    public void buildFeatureTables() {
    	class_c topClass = this.getClass(TreeConstants.Object_.getString());
    	this.buildFeatureTablesForClassAndDescendants(topClass);
    }
    
    private void buildFeatureTablesForClassAndDescendants(class_c coolClass) {
    	this.buildFeatureTableForClass(coolClass);
    	
		String className = coolClass.getName().getString();
		List<class_c> descendants = this.getClassDescendants(className);
		if (descendants == null) return;
		
		for (class_c descendant: descendants) {
			this.buildFeatureTablesForClassAndDescendants(descendant);
		}
    }
    
    private void buildFeatureTableForClass(class_c coolClass) {
		String className = coolClass.getName().getString();
		ArrayList<String> classAncestryTypes = this.getClassAncestryTypes(className);
		Hashtable<String, AttributeSymbol> attrs = new Hashtable<String, AttributeSymbol>();
		Hashtable<String, MethodSymbol> methods = new Hashtable<String, MethodSymbol>();
		
		for(Enumeration f = coolClass.getFeatures().getElements(); f.hasMoreElements(); ) {
			Feature feature = (Feature)f.nextElement();
			
			if (feature instanceof attr) {
				attr attrFeature = (attr)feature;
				if (checkAttribute(coolClass, attrFeature, classAncestryTypes)) {
					AttributeSymbol attrSymbol = new AttributeSymbol(attrFeature.getName(), attrFeature.getTypeDecl());
					attrs.put(attrFeature.getName().getString(), attrSymbol);
					classAttributes.put(className, attrs);
				}
			} else if (feature instanceof method) {
				method methodFeature = (method)feature;
				if (checkMethod(coolClass, methodFeature, classAncestryTypes, classMethods)) {
					Formals formals = methodFeature.getFormals();
					ArrayList<FormalParameterSymbol> methodFormals = new ArrayList<FormalParameterSymbol>();
					for (Enumeration mf = formals.getElements(); mf.hasMoreElements(); ) {
						formalc formal = (formalc)mf.nextElement();
						FormalParameterSymbol formalParameterSymbol = new FormalParameterSymbol(formal.getName(), formal.getTypeDecl());
						methodFormals.add(formalParameterSymbol);
					}
					MethodSymbol methodSymbol = new MethodSymbol(methodFeature.getName(), methodFeature.getReturnType(), methodFormals);
					methods.put(methodFeature.getName().getString(), methodSymbol);
					classMethods.put(className,  methods);
				}
			}
		}
    }
    
    private Boolean checkAttribute(class_c coolClass, attr attrToCheck, ArrayList<String> classAncestryTypes) {
    	String currentAttrName = attrToCheck.getName().getString();
    	Boolean attrExists = false;
    	
    	// check to see if it is redefining an existing attribute in an inherited class
    	for (String ancestorType: classAncestryTypes) {
    		class_c ancestorClass = this.getClass(ancestorType);
    		attrExists = this.checkForExistingAttributeInClass(ancestorClass, currentAttrName);
    		if (attrExists) {
    			this.printAttributeRedefinedError(coolClass, attrToCheck);
    			return false;
    		}
    	}
    	    	
    	// check to see if attribute is multiply defined in class
    	attrExists = checkForExistingAttributeInClass(coolClass, currentAttrName);
    	if (attrExists) {
    		this.printAttributeMultiplyDefinedError(coolClass, attrToCheck);
    		return false;
    	}
    	
    	return true;
    }
    
    private Boolean checkForExistingAttributeInClass(class_c coolClass, String attrName) {
		String className = coolClass.getName().getString();
    	Hashtable<String, AttributeSymbol> currentClassAttributes = classAttributes.get(className);
    	if (currentClassAttributes != null) {
        	AttributeSymbol existingAttr = currentClassAttributes.get(attrName);
        	if (existingAttr != null) {
        		return true;
        	}
        	return false;
    	} else
    		return false;
   }
    
    private Boolean checkMethod(class_c coolClass, method methodToCheck, ArrayList<String> classAncestryTypes, 
    		Hashtable<String, Hashtable<String, MethodSymbol>> classMethods) {
    	String currentMethodName = methodToCheck.getName().getString();
    	
    	// if method is redefining a method in an inherited class, make sure that the return type,
    	// the number of formal parameters and the types of each formal parameter all match
    	for (String ancestorType: classAncestryTypes) {
    		MethodSymbol originalMethod = this.getMethodInClass(ancestorType, currentMethodName);
    		if (originalMethod != null) { // found method being redefined
    			// check return types
    			String currentReturnType = methodToCheck.return_type.getString();
    			String originalReturnType = originalMethod.getReturnType().getString();
    			if (!currentReturnType.equals(originalReturnType)) {
    				this.printMethodReturnTypeDifferent(coolClass, methodToCheck, currentReturnType, originalReturnType);
    				return false;
    			}
    			
    			// check number of formal parameters
    			Integer currentParmCount = methodToCheck.getFormals().getLength();
    			Integer originalParmCount = originalMethod.getFormals().size();
    			if (currentParmCount != originalParmCount) {
    				this.printMethodFormalParameterCountMismatch(coolClass, methodToCheck);
    				return false;
    			}
    			
    			// check types of each formal parameter
    			Formals currentMethodParms = methodToCheck.getFormals();
    			ArrayList<FormalParameterSymbol> originalMethodParms = originalMethod.getFormals();
    			for (Integer i = 0; i < currentParmCount; i++) {
    				formalc currentParm = (formalc)currentMethodParms.getNth(i);
    				FormalParameterSymbol originalParm = originalMethodParms.get(i);
    				
    				String currentParmType = currentParm.getTypeDecl().getString();
    				String originalParmType = originalParm.getType().getString();
    				if (!currentParmType.equals(originalParmType)) {
    					this.printMethodFormalParameterTypeMismatch(coolClass, methodToCheck, currentParmType, originalParmType);
    					return false;
    				}
    			}
    		}
    	}
    	
    	// check that method is not already defined in current class
    	String currentClassName = coolClass.getName().getString();
    	MethodSymbol currentMethod = this.getMethodInClass(currentClassName, currentMethodName);
    	if (currentMethod != null) {
    		this.printMethodMultipyDefinedError(coolClass, methodToCheck);
    		return false;
    	}
    	
    	return true;
    }
    
    private MethodSymbol getMethodInClass(String className, String methodName) {
    	Hashtable<String, MethodSymbol> currentClassMethods = classMethods.get(className);
    	if (currentClassMethods != null)
    		return currentClassMethods.get(methodName);
    	else
    		return null;
    }
    
    private void printAttributeMultiplyDefinedError(class_c coolClass, attr classAttr) {
    	String attrName = classAttr.getName().getString();
		PrintStream errorStream = this.semantError(coolClass.getFilename(), classAttr);
		errorStream.println("Attribute " + attrName + " is multiply defined in class.");
    }
    
    private void printAttributeRedefinedError(class_c coolClass, attr classAttr) {
    	String attrName = classAttr.getName().getString();
		PrintStream errorStream = this.semantError(coolClass.getFilename(), classAttr);
		errorStream.println("Attribute " + attrName + " is an attribute of an inherited class.");
    }
    
    private void printMethodMultipyDefinedError(class_c coolClass, method classMethod) {
    	String methodName = classMethod.getName().getString();
		PrintStream errorStream = this.semantError(coolClass.getFilename(), classMethod);
		errorStream.println("Method " + methodName + " is multiply defined.");
    }
    
    private void printMethodReturnTypeDifferent(class_c coolClass, method classMethod,
    		String currentReturnType, String originalReturnType) {
    	String methodName = classMethod.getName().getString();
		PrintStream errorStream = this.semantError(coolClass.getFilename(), classMethod);
		errorStream.println("In redefined method " + methodName + " return type " + currentReturnType + " is different from original return type " + originalReturnType + ".");
    }
    
    private void printMethodFormalParameterCountMismatch(class_c coolClass, method classMethod) {
    	String methodName = classMethod.getName().getString();
		PrintStream errorStream = this.semantError(coolClass.getFilename(), classMethod);
		errorStream.println("Incompatible number of formal parameters in redefined method " + methodName + ".");
    }

    private void printMethodFormalParameterTypeMismatch(class_c coolClass, method classMethod,
    		String currentParmType, String originalParmType) {
    	String methodName = classMethod.getName().getString();
		PrintStream errorStream = this.semantError(coolClass.getFilename(), classMethod);
		errorStream.println("In redefined method " + methodName + " parameter type " + currentParmType + " is different from original type " + originalParmType + ".");
    }
    
    public void validateClassMain() {
    	// check for class Main
    	class_c classMain = null;
    	Vertex<class_c> classMainVertex = this.inheritanceGraph.findVertex("Main");
    	if (classMainVertex == null) {
    		this.printClassMainNotDefinedError();
    		return;
    	} else {
        	classMain = classMainVertex.getAdditionalData();
    	}
		
    	// check for main method in class Main
    	method mainMethod = classMain.findMethod("main");
    	if (mainMethod == null) {
    		printNoMainMethodInClassMainError(classMain);
    		return;
    	}
    	
    	// check that main method has no arguments
    	Formals formals = mainMethod.getFormals();
    	if (formals != null && formals.getLength() > 0) {
    		printMainMethodHasArgumentsError(classMain);
    		return;
    	}
    }
    
    public class_c getClass(String className) {
    	Vertex<class_c> foundVertex = this.inheritanceGraph.findVertex(className);
    	if (foundVertex == null) return null;
    	return foundVertex.getAdditionalData();
    }
    
    public class_c getClassAncestor(String className) {
    	Vertex<class_c> foundVertex = this.inheritanceGraph.findVertex(className);
    	if (foundVertex == null) return null;
    	Vertex<class_c> ancestorVertex = foundVertex.getAncestor();
    	
    	if (ancestorVertex == null) return null;
    	if (ancestorVertex.getLabel().equals(TreeConstants.No_class.getString())) return null; // top of hierarchy
    	return ancestorVertex.getAdditionalData();
    }
    
    public List<class_c> getClassDescendants(String className) {
    	Vertex<class_c> foundVertex = this.inheritanceGraph.findVertex(className);
    	if (foundVertex == null) return null;
    	List<Vertex<class_c>> descendantVertices = foundVertex.getDescendants();
    	if (descendantVertices == null) return null;

    	List<class_c> descendants = new ArrayList<class_c>();
    	for (Vertex<class_c>descendant: descendantVertices) {
    		descendants.add(descendant.getAdditionalData());
    	}
    	return descendants;
    }
    public Hashtable<String, AttributeSymbol> getClassAttributes(String className) {
    	return classAttributes.get(className);
    }
    
    public Hashtable<String, MethodSymbol> getClassMethods(String className) {
    	return classMethods.get(className);
    }
}
			  
    
