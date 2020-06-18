/*
Copyright (c) 2000 The Regents of the University of California.
All rights reserved.

Permission to use, copy, modify, and distribute this software for any
purpose, without fee, and without written agreement is hereby granted,
provided that the above copyright notice and the following two
paragraphs appear in all copies of this software.

IN NO EVENT SHALL THE UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR
DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES ARISING OUT
OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF THE UNIVERSITY OF
CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
AND FITNESS FOR A PARTICULAR PURPOSE.  THE SOFTWARE PROVIDED HEREUNDER IS
ON AN "AS IS" BASIS, AND THE UNIVERSITY OF CALIFORNIA HAS NO OBLIGATION TO
PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
*/

// This is a project skeleton file

import java.io.PrintStream;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Enumeration;

/** This class is used for representing the inheritance tree during code
    generation. You will need to fill in some of its methods and
    potentially extend it in other useful ways. */
class CgenClassTable extends SymbolTable {

    /** All classes in the program, represented as CgenNode */
    private Vector nds;

    /** This is the stream to which assembly instructions are output */
    private PrintStream str;

    private int stringclasstag;
    private int intclasstag;
    private int boolclasstag;


    // The following methods emit code for constants and global
    // declarations.

    /** Emits code to start the .data segment and to
     * declare the global names.
     * */
    private void codeGlobalData() {
	// The following global names must be defined first.

	str.print("\t.data\n" + CgenSupport.ALIGN);
	str.println(CgenSupport.GLOBAL + CgenSupport.CLASSNAMETAB);
	str.print(CgenSupport.GLOBAL); 
	CgenSupport.emitProtObjRef(TreeConstants.Main, str);
	str.println("");
	str.print(CgenSupport.GLOBAL); 
	CgenSupport.emitProtObjRef(TreeConstants.Int, str);
	str.println("");
	str.print(CgenSupport.GLOBAL); 
	CgenSupport.emitProtObjRef(TreeConstants.Str, str);
	str.println("");
	str.print(CgenSupport.GLOBAL); 
	BoolConst.falsebool.codeRef(str);
	str.println("");
	str.print(CgenSupport.GLOBAL); 
	BoolConst.truebool.codeRef(str);
	str.println("");
	str.println(CgenSupport.GLOBAL + CgenSupport.INTTAG);
	str.println(CgenSupport.GLOBAL + CgenSupport.BOOLTAG);
	str.println(CgenSupport.GLOBAL + CgenSupport.STRINGTAG);

	// We also need to know the tag of the Int, String, and Bool classes
	// during code generation.

	str.println(CgenSupport.INTTAG + CgenSupport.LABEL 
		    + CgenSupport.WORD + intclasstag);
	str.println(CgenSupport.BOOLTAG + CgenSupport.LABEL 
		    + CgenSupport.WORD + boolclasstag);
	str.println(CgenSupport.STRINGTAG + CgenSupport.LABEL 
		    + CgenSupport.WORD + stringclasstag);

    }

    /** Emits code to start the .text segment and to
     * declare the global names.
     * */
    private void codeGlobalText() {
	str.println(CgenSupport.GLOBAL + CgenSupport.HEAP_START);
	str.print(CgenSupport.HEAP_START + CgenSupport.LABEL);
	str.println(CgenSupport.WORD + 0);
	str.println("\t.text");
	str.print(CgenSupport.GLOBAL);
	CgenSupport.emitInitRef(TreeConstants.Main, str);
	str.println("");
	str.print(CgenSupport.GLOBAL);
	CgenSupport.emitInitRef(TreeConstants.Int, str);
	str.println("");
	str.print(CgenSupport.GLOBAL);
	CgenSupport.emitInitRef(TreeConstants.Str, str);
	str.println("");
	str.print(CgenSupport.GLOBAL);
	CgenSupport.emitInitRef(TreeConstants.Bool, str);
	str.println("");
	str.print(CgenSupport.GLOBAL);
	CgenSupport.emitMethodRef(TreeConstants.Main, TreeConstants.main_meth, str);
	str.println("");
    }

    /** Emits code definitions for boolean constants. */
    private void codeBools(int classtag) {
	BoolConst.falsebool.codeDef(classtag, str);
	BoolConst.truebool.codeDef(classtag, str);
    }

    /** Generates GC choice constants (pointers to GC functions) */
    private void codeSelectGc() {
	str.println(CgenSupport.GLOBAL + "_MemMgr_INITIALIZER");
	str.println("_MemMgr_INITIALIZER:");
	str.println(CgenSupport.WORD 
		    + CgenSupport.gcInitNames[Flags.cgen_Memmgr]);

	str.println(CgenSupport.GLOBAL + "_MemMgr_COLLECTOR");
	str.println("_MemMgr_COLLECTOR:");
	str.println(CgenSupport.WORD 
		    + CgenSupport.gcCollectNames[Flags.cgen_Memmgr]);

	str.println(CgenSupport.GLOBAL + "_MemMgr_TEST");
	str.println("_MemMgr_TEST:");
	str.println(CgenSupport.WORD 
		    + ((Flags.cgen_Memmgr_Test == Flags.GC_TEST) ? "1" : "0"));
    }

    /** Emits code to reserve space for and initialize all of the
     * constants.  Class names should have been added to the string
     * table (in the supplied code, is is done during the construction
     * of the inheritance graph), and code for emitting string constants
     * as a side effect adds the string's length to the integer table.
     * The constants are emmitted by running through the stringtable and
     * inttable and producing code for each entry. */
    private void codeConstants() {
	// Add constants that are required by the code generator.
	AbstractTable.stringtable.addString("");
	AbstractTable.inttable.addString("0");

	AbstractTable.stringtable.codeStringTable(stringclasstag, str);
	AbstractTable.inttable.codeStringTable(intclasstag, str);
	codeBools(boolclasstag);
    }


    /** Creates data structures representing basic Cool classes (Object,
     * IO, Int, Bool, String).  Please note: as is this method does not
     * do anything useful; you will need to edit it to make if do what
     * you want.
     * */
    private void installBasicClasses() {
	AbstractSymbol filename 
	    = AbstractTable.stringtable.addString("<basic class>");
	
	// A few special class names are installed in the lookup table
	// but not the class list.  Thus, these classes exist, but are
	// not part of the inheritance hierarchy.  No_class serves as
	// the parent of Object and the other special classes.
	// SELF_TYPE is the self class; it cannot be redefined or
	// inherited.  prim_slot is a class known to the code generator.

	addId(TreeConstants.No_class,
	      new CgenNode(new class_(0,
				      TreeConstants.No_class,
				      TreeConstants.No_class,
				      new Features(0),
				      filename),
			   CgenNode.Basic, this));

	addId(TreeConstants.SELF_TYPE,
	      new CgenNode(new class_(0,
				      TreeConstants.SELF_TYPE,
				      TreeConstants.No_class,
				      new Features(0),
				      filename),
			   CgenNode.Basic, this));
	
	addId(TreeConstants.prim_slot,
	      new CgenNode(new class_(0,
				      TreeConstants.prim_slot,
				      TreeConstants.No_class,
				      new Features(0),
				      filename),
			   CgenNode.Basic, this));

	// The Object class has no parent class. Its methods are
	//        cool_abort() : Object    aborts the program
	//        type_name() : Str        returns a string representation 
	//                                 of class name
	//        copy() : SELF_TYPE       returns a copy of the object

	class_ Object_class = 
	    new class_(0, 
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

	installClass(new CgenNode(Object_class, CgenNode.Basic, this));
	
	// The IO class inherits from Object. Its methods are
	//        out_string(Str) : SELF_TYPE  writes a string to the output
	//        out_int(Int) : SELF_TYPE      "    an int    "  "     "
	//        in_string() : Str            reads a string from the input
	//        in_int() : Int                "   an int     "  "     "

	class_ IO_class = 
	    new class_(0,
		       TreeConstants.IO,
		       TreeConstants.Object_,
		       new Features(0)
			   .appendElement(new method(0,
					      TreeConstants.out_string,
					      new Formals(0)
						  .appendElement(new formal(0,
								     TreeConstants.arg,
								     TreeConstants.Str)),
					      TreeConstants.SELF_TYPE,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.out_int,
					      new Formals(0)
						  .appendElement(new formal(0,
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

	installClass(new CgenNode(IO_class, CgenNode.Basic, this));

	// The Int class has no methods and only a single attribute, the
	// "val" for the integer.

	class_ Int_class = 
	    new class_(0,
		       TreeConstants.Int,
		       TreeConstants.Object_,
		       new Features(0)
			   .appendElement(new attr(0,
					    TreeConstants.val,
					    TreeConstants.prim_slot,
					    new no_expr(0))),
		       filename);

	installClass(new CgenNode(Int_class, CgenNode.Basic, this));

	// Bool also has only the "val" slot.
	class_ Bool_class = 
	    new class_(0,
		       TreeConstants.Bool,
		       TreeConstants.Object_,
		       new Features(0)
			   .appendElement(new attr(0,
					    TreeConstants.val,
					    TreeConstants.prim_slot,
					    new no_expr(0))),
		       filename);

	installClass(new CgenNode(Bool_class, CgenNode.Basic, this));

	// The class Str has a number of slots and operations:
	//       val                              the length of the string
	//       str_field                        the string itself
	//       length() : Int                   returns length of the string
	//       concat(arg: Str) : Str           performs string concatenation
	//       substr(arg: Int, arg2: Int): Str substring selection

	class_ Str_class =
	    new class_(0,
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
						  .appendElement(new formal(0,
								     TreeConstants.arg, 
								     TreeConstants.Str)),
					      TreeConstants.Str,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.substr,
					      new Formals(0)
						  .appendElement(new formal(0,
								     TreeConstants.arg,
								     TreeConstants.Int))
						  .appendElement(new formal(0,
								     TreeConstants.arg2,
								     TreeConstants.Int)),
					      TreeConstants.Str,
					      new no_expr(0))),
		       filename);

	installClass(new CgenNode(Str_class, CgenNode.Basic, this));
    }
	
    // The following creates an inheritance graph from
    // a list of classes.  The graph is implemented as
    // a tree of `CgenNode', and class names are placed
    // in the base class symbol table.
    
    private void installClass(CgenNode nd) {
	AbstractSymbol name = nd.getName();
	if (probe(name) != null) return;
	nds.addElement(nd);
	addId(name, nd);
    }

    private void installClasses(Classes cs) {
        for (Enumeration e = cs.getElements(); e.hasMoreElements(); ) {
	    installClass(new CgenNode((Class_)e.nextElement(), 
				       CgenNode.NotBasic, this));
        }
    }

    private void buildInheritanceTree() {
    	//int classTag = 0;
    	for (Enumeration e = nds.elements(); e.hasMoreElements(); ) {
			CgenNode node = (CgenNode)e.nextElement();
		    setRelations(node);
		    //setClassTag(node, classTag);
		    //classTag++;
		}
    }

    private void setRelations(CgenNode nd) {
	CgenNode parent = (CgenNode)probe(nd.getParent());
	nd.setParentNd(parent);
	parent.addChild(nd);
    }

    /** Constructs a new class table and invokes the code generator */
    public CgenClassTable(Classes cls, PrintStream str) {
	nds = new Vector();

	this.str = str;

	stringclasstag = 0 /* Change to your String class tag here */;
	intclasstag =    0 /* Change to your Int class tag here */;
	boolclasstag =   0 /* Change to your Bool class tag here */;

	enterScope();
	if (Flags.cgen_debug) System.out.println("Building CgenClassTable");
	
	installBasicClasses();
	installClasses(cls);
	buildInheritanceTree();
	setClassTags();
	
	code();

	exitScope();
    }

    /** This method is the meat of the code generator.  It is to be
        filled in programming assignment 5 */
    public void code() {
	if (Flags.cgen_debug) System.out.println("coding global data");
	codeGlobalData();

	if (Flags.cgen_debug) System.out.println("choosing gc");
	codeSelectGc();

	if (Flags.cgen_debug) System.out.println("coding constants");
	codeConstants();

	//                 Add your code to emit
	//                   - prototype objects
	//                   - class_nameTab
	//                   - dispatch tables

	codeClassTables();
	codeDispatchTables();
	codePrototypeObjects();
	
	if (Flags.cgen_debug) System.out.println("coding global text");
	codeGlobalText();

	//                 Add your code to emit
	//                   - object initializer
	//                   - the class methods
	//                   - etc...
	
	codeClassInits();
	codeMethodsForClasses();
    }

    /** Gets the root of the inheritance tree */
    public CgenNode root() {
	return (CgenNode)probe(TreeConstants.Object_);
    }
    
    private int classTag;
    private void setClassTags() {
    	classTag = 0;
    	
    	CgenNode root = this.root();
    	setClassTag(root, classTag);
    }
    
    private void setClassTag(CgenNode node, Integer classTagNumber) {
    	node.setClassTag(classTagNumber);
    	
    	String nodeName = node.name.getString();
    	if (nodeName == TreeConstants.Int.getString()) {
    		this.intclasstag = classTagNumber;
    	} else if (nodeName == TreeConstants.Bool.getString()) {
    		this.boolclasstag = classTagNumber;
    	} else if (nodeName == TreeConstants.Str.getString()) {
    		this.stringclasstag = classTagNumber;
    	}
    	
    	// set classTag values for child nodes
    	classTag++;
    	//for (Integer i = node.getNumChildren() - 1; i >= 0; i--) {
    	Integer numChildren = node.getNumChildren();
    	for (Integer i = 0; i < numChildren; i++) {
    		CgenNode childNode = node.getNthChild(i);
    		setClassTag(childNode, classTag);
    	}
    }
    
    private void codeClassTables() {
    	Integer vectorSize = nds.size();
    	IdSymbol[] classNames = new IdSymbol[vectorSize];
    	
    	for (Enumeration e = nds.elements(); e.hasMoreElements(); ) {
			CgenNode node = (CgenNode)e.nextElement();
			Integer classTag = node.getClassTag();
			IdSymbol className = (IdSymbol)node.getName();
			classNames[classTag] = className;
    	}
    	
    	codeClassNameTable(classNames);
    	codeClassObjectTable(classNames);
    }
    
    private void codeClassNameTable(IdSymbol[] classNames) {
//    	for (Integer i = 0; i < classNames.length; i++)
//    	{
//    		System.out.print("ClassTag = " + i + " ClassName = ");
//    		if (classNames[i] == null)
//    			System.out.println("ClassName not set");
//    		else
//    			System.out.println(classNames[i]);
//    	}
    	
    	str.print(CgenSupport.CLASSNAMETAB + CgenSupport.LABEL);

    	for (IdSymbol className: classNames) {
        	str.print(CgenSupport.WORD);
        	StringSymbol symbol = (StringSymbol)AbstractTable.stringtable.lookup(className.getString());
    		symbol.codeRef(str);
    		str.println("");
    	}
    	
    }
    
    private void codeClassObjectTable(IdSymbol[] classNames) {
    	str.print(CgenSupport.CLASSOBJTAB + CgenSupport.LABEL);
    	
    	for (IdSymbol className: classNames) {
    		String name = className.getString();
    		str.print(CgenSupport.WORD);
    		str.println(name + CgenSupport.PROTOBJ_SUFFIX);
    		str.print(CgenSupport.WORD);
    		str.println(name + CgenSupport.CLASSINIT_SUFFIX);
    	}
    }
    
    private void codeDispatchTables() {
    	CgenNode root = this.root();
    	codeDispatchTable(root);
    }
 
    private void codeDispatchTable(CgenNode node) {
    	// build the node's dispatch table starting with parent's
    	CgenNode parentNode = node.getParentNd();
		Vector<DispatchTableEntry> dispatchTable = new Vector<DispatchTableEntry>();
		if (parentNode != null) {
			Vector<DispatchTableEntry> parentDispatchTable = parentNode.getDispatchTable();
			if (parentDispatchTable != null) {
				for (DispatchTableEntry entry: parentDispatchTable) {
					dispatchTable.add(new DispatchTableEntry(entry.getMethodName(), entry.getClassName(), entry.getFormalParms()));
				}
			}
		}
		
    	// code the current node
    	String name = node.name.getString();
    	str.print(name + CgenSupport.DISPTAB_SUFFIX + CgenSupport.LABEL);
    	
    	for(Enumeration e = node.features.getElements(); e.hasMoreElements(); ) {
    		Feature feature = (Feature)e.nextElement();
    		if (feature instanceof method) {
    			method methodFeature = (method)feature; 
    			String methodName = methodFeature.name.getString();
    			
    			Boolean methodFound = false;
    			for(DispatchTableEntry entry: dispatchTable) {
    				if (entry.getMethodName().equals(methodName)) {
    					entry.setClassName(name);
    					methodFound = true;
    				}
    			}
    			if (!methodFound) {
    				Vector<FormalParmEntry> methodParms = new Vector<FormalParmEntry>();
    				for (Enumeration f = methodFeature.formals.getElements(); f.hasMoreElements(); ) {
    					formal formalParm = (formal) f.nextElement();
    					FormalParmEntry formalParmEntry = new FormalParmEntry(formalParm.name, formalParm.type_decl);
    					methodParms.add(formalParmEntry);
    				}
    				DispatchTableEntry entry = new DispatchTableEntry(methodName, name, methodParms);
    				dispatchTable.add(entry);
    			}
    		}
    	}
    	node.setDispatchTable(dispatchTable);
    	
    	for (DispatchTableEntry entry: dispatchTable) {
    		str.print(CgenSupport.WORD);
    		str.println(entry.getClassName() + "." + entry.getMethodName());
    	}
    	
    	for (Integer i = node.getNumChildren() - 1; i >= 0; i--) {
    		CgenNode nd = node.getNthChild(i);
    		codeDispatchTable(nd);
    	}
    }
    
    private void codePrototypeObjects() {
    	CgenNode root = this.root();
    	codePrototypeObject(root);
    }
    
    private void codePrototypeObject(CgenNode node) {
    	// build the node's attribute list starting with parent's
    	Integer objectOffset = 3; // 1st 3 words are the class tag, object size, and class dispatch pointer
    	CgenNode parentNode = node.getParentNd();
    	Vector<AttributeEntry> attributes = new Vector<AttributeEntry>();
    	if (parentNode != null) {
//    		for (Enumeration e = parentNode.features.getElements(); e.hasMoreElements(); ) {
//    			Feature parentFeature = (Feature)e.nextElement();
//    			if (parentFeature instanceof attr) {
//    				attr attrFeature = (attr) parentFeature;
//    				attrFeature.object_offset = objectOffset;
//    				objectOffset += 4;
//    			}
//    		}
    		Vector<AttributeEntry> parentAttributes = parentNode.getAttributes();
    		if (parentAttributes != null) {
    			for (AttributeEntry entry: parentAttributes) {
    				attributes.add(new AttributeEntry(entry.getName(), entry.getDataType(), entry.getDefaultValue(), objectOffset));
    				objectOffset++;
    			}
    		}
    	}
    	
    	// add current node's attributes to attributes list
    	for (Enumeration e = node.features.getElements(); e.hasMoreElements(); ) {
    		Feature feature = (Feature)e.nextElement();
    		if (feature instanceof attr) {
    			attr attrFeature = (attr)feature;
    			AbstractSymbol attrName = attrFeature.name;
    			AbstractSymbol dataType = attrFeature.type_decl;
    			String defaultValue = null;
    			if (dataType.getString().equals(TreeConstants.Str.getString())) {
    				defaultValue = "";
    			} else if (dataType.getString().equals(TreeConstants.Int.getString())) {
    				defaultValue = "0";
    			} else if (dataType.getString().equals(TreeConstants.Bool.getString())) {
    				defaultValue = "0";
    			}
    			AttributeEntry entry = new AttributeEntry(attrName, dataType, defaultValue, objectOffset);
    			attributes.add(entry);
    			
    			attrFeature.default_value = defaultValue;
    			attrFeature.object_offset = objectOffset;
    			objectOffset++;
    		}
    	}
    	node.setAttributes(attributes);
    	
    	// code the current node
    	String name = node.name.getString();
    	str.println(CgenSupport.WORD + "-1");
    	str.print(name + CgenSupport.PROTOBJ_SUFFIX + CgenSupport.LABEL);
    	Integer objectSize = 3 + attributes.size();
    	str.println(CgenSupport.WORD + node.getClassTag());
    	str.println(CgenSupport.WORD + objectSize);
    	str.println(CgenSupport.WORD + name + CgenSupport.DISPTAB_SUFFIX);
    	for (AttributeEntry entry: attributes) {
    		str.print(CgenSupport.WORD);
    		if (entry.getDataType().getString().equals(TreeConstants.Str.getString())) {
    			StringSymbol symbol = (StringSymbol)AbstractTable.stringtable.lookup(entry.getDefaultValue());
    			symbol.codeRef(str);
    			str.println("");
    		} else if (entry.getDataType().getString().equals(TreeConstants.Int.getString())) {
    			IntSymbol symbol = (IntSymbol)AbstractTable.inttable.lookup(entry.getDefaultValue());
    			symbol.codeRef(str);
    			str.println("");
    		} else if (entry.getDataType().getString().equals(TreeConstants.Bool.getString())) {
    			BoolConst boolValue = new BoolConst(entry.getDefaultValue().equals("0") ? false : true);
    			boolValue.codeRef(str);
    			str.println("");
    		} else {
    			str.println("0");
    		}
    	}
    	
    	for (Integer i = node.getNumChildren() - 1; i >= 0; i--) {
    		CgenNode nd = node.getNthChild(i);
    		codePrototypeObject(nd);
    	}
    }
    
    private void codeClassInits() {
    	CgenNode root = this.root();
    	codeClassInit(root);
    }
    
    private void codeClassInit(CgenNode node) {
    	// emit init method label
    	str.print(node.getName() + CgenSupport.CLASSINIT_SUFFIX + CgenSupport.LABEL);
    	
		node.calculateAttrMaxLocalVariableCountStart();
    	for (Enumeration e = node.features.getElements(); e.hasMoreElements(); ) {
    		Feature feature = (Feature)e.nextElement();
    		if (feature instanceof attr) {
    			attr attrFeature = (attr)feature;
    			if (!(attrFeature.init instanceof no_expr)) {
    				attrFeature.init.calculateAttrMaxLocalVariableCount(node);
    			}
    		}
    	}
    	Integer numLocalVariables = node.getAttrMaxLocalVariableCount();

    	// add class attributes to symbol table at current scope
    	node.resetCurrentLocalVariableCount();
    	enterScope();
    	
    	Vector<AttributeEntry> classAttributes = node.getAttributes();
    	for (AttributeEntry attributeEntry: classAttributes) {
    		AbstractSymbol name = attributeEntry.getName();
    		SymbolTableEntry entry = new SymbolTableEntry(attributeEntry.getObjectOffset(), 
    				OffsetBaseType.Object, attributeEntry.getDataType());
    		this.addId(name, entry);
    	}
    	
    	// emit init method startup code
    	codeMethodStart(numLocalVariables);
    	
    	codeParentInit(node);
    	
    	// emit code for each attribute with an initializer
    	for (Enumeration e = node.features.getElements(); e.hasMoreElements(); ) {
    		Feature feature = (Feature)e.nextElement();
    		if (feature instanceof attr) {
    			attr attrFeature = (attr)feature;
    			
    			// only generate code for attributes that have an initializer
    			if (!(attrFeature.init instanceof no_expr)) {
    				attrFeature.init.code(node, this, str);
    				
    				// assign object of the attribute initializer expression to the attribute of the class
        			CgenSupport.emitStore("$a0", attrFeature.object_offset, "$s0", str);
    			}
    		}
    	}
    	
    	// emit init method cleanup code
    	codeMethodEnd(0, numLocalVariables, true);
    	
    	exitScope();
    	
    	// create class inits for the class children
    	for (Integer i = node.getNumChildren() - 1; i >= 0; i--) {
    		CgenNode nd = node.getNthChild(i);
    		codeClassInit(nd);
    	}
    }
    
    private void codeParentInit(CgenNode node) {
    	CgenNode parent = node.getParentNd();
    	if (!(parent.getName().equals(TreeConstants.No_class))) {
        	CgenSupport.emitJal(parent.getName() + CgenSupport.CLASSINIT_SUFFIX, str);
    	}
    }
    
    private void codeMethodStart(Integer numLocalVars) {
    	Integer calleeActivationRecordSize = (numLocalVars + 3) * 4;
    	CgenSupport.emitAddiu("$sp", "$sp", -calleeActivationRecordSize, str);
    	CgenSupport.emitStore("$fp", 3 + numLocalVars, "$sp", str);
    	CgenSupport.emitStore("$s0", 2 + numLocalVars, "$sp", str);
    	CgenSupport.emitStore("$ra", 1 + numLocalVars, "$sp", str);
    	CgenSupport.emitAddiu("$fp", "$sp", 4, str);
    	CgenSupport.emitMove("$s0", "$a0", str);
    }
    
    private void codeMethodEnd(Integer numFormalParms, Integer numLocalVars, Boolean restoreObjectReference) {
    	Integer calleeActivationRecordSize = (numFormalParms + numLocalVars + 3) * 4;
    	if (restoreObjectReference) {
        	CgenSupport.emitMove("$a0", "$s0", str);
    	}
    	CgenSupport.emitLoad("$fp", 3 + numLocalVars, "$sp", str);
    	CgenSupport.emitLoad("$s0", 2 + numLocalVars, "$sp", str);
    	CgenSupport.emitLoad("$ra", 1 + numLocalVars, "$sp", str);
    	CgenSupport.emitAddiu("$sp", "$sp", calleeActivationRecordSize, str);
    	CgenSupport.emitJr("$ra", str);
    }
    
    private void codeMethodsForClasses() {
    	CgenNode root = this.root();
    	codeMethodsForClass(root);
    }
    
    private void codeMethodsForClass(CgenNode node) {
    	// create new scope and add class attributes to it
    	enterScope();
    	
    	Vector<AttributeEntry> classAttributes = node.getAttributes();
    	for(AttributeEntry attributeEntry: classAttributes) {
    		AbstractSymbol name = attributeEntry.getName();
    		SymbolTableEntry entry = new SymbolTableEntry(attributeEntry.getObjectOffset(),
    				OffsetBaseType.Object, attributeEntry.getDataType());
    		this.addId(name, entry);
    	}
    	
    	for (Enumeration e = node.features.getElements(); e.hasMoreElements(); ) {
    		Feature feature = (Feature)e.nextElement();
    		if (feature instanceof method) {
    			method methodFeature = (method)feature;
    			if (!(methodFeature.expr instanceof no_expr)) {
        			// emit method label
        			str.print(node.getName() + "." + methodFeature.name + CgenSupport.LABEL);
        			
        			node.calculateAttrMaxLocalVariableCountStart();
        			methodFeature.expr.calculateAttrMaxLocalVariableCount(node);
        			Integer numLocalVariables = node.getAttrMaxLocalVariableCount();
        			
        			node.resetCurrentLocalVariableCount();
        			enterScope();
        			
        			// add formal parameters into the current scope
        			Integer numFormals = methodFeature.formals.getLength();
        			for (Integer i = numFormals - 1; i >= 0; i--) {
        				formal methodFormal = (formal)methodFeature.formals.getNth(i);
        				Integer offset = numLocalVariables + numFormals - i + 2;
        				SymbolTableEntry entry = new SymbolTableEntry(offset, OffsetBaseType.ActivationRecord, methodFormal.type_decl);
        				this.addId(methodFormal.name, entry);
        			}
        			
        			// emit method startup code
        			codeMethodStart(numLocalVariables);
        			
        			// emit method expression code
        			methodFeature.expr.code(node, this, str);
        			
        			// emit method cleanup code
        			codeMethodEnd(numFormals, numLocalVariables, false);
        			
        			exitScope();
    			}
    		}
    	}
    	
    	exitScope();
    	
    	// create methods for class children
     	for (Integer i = node.getNumChildren() - 1; i >= 0; i--) {
    		CgenNode nd = node.getNthChild(i);
    		codeMethodsForClass(nd);
    	}
    }
}
			  
    
