// -*- mode: java -*- 
//
// file: cool-tree.m4
//
// This file defines the AST
//
//////////////////////////////////////////////////////////



import java.util.ArrayList;
import java.util.Enumeration;
import java.io.PrintStream;
import java.util.Vector;


/** Defines simple phylum Program */
abstract class Program extends TreeNode {
    protected Program(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    public abstract void semant();
    public abstract void cgen(PrintStream s);

}


/** Defines simple phylum Class_ */
abstract class Class_ extends TreeNode {
    protected Class_(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    public abstract AbstractSymbol getName();
    public abstract AbstractSymbol getParent();
    public abstract AbstractSymbol getFilename();
    public abstract Features getFeatures();

}


/** Defines list phylum Classes
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Classes extends ListNode {
    public final static Class elementClass = Class_.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Classes(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Classes" list */
    public Classes(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Class_" element to this list */
    public Classes appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Classes(lineNumber, copyElements());
    }
}


/** Defines simple phylum Feature */
abstract class Feature extends TreeNode {
    protected Feature(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);

}


/** Defines list phylum Features
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Features extends ListNode {
    public final static Class elementClass = Feature.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Features(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Features" list */
    public Features(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Feature" element to this list */
    public Features appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Features(lineNumber, copyElements());
    }
}


/** Defines simple phylum Formal */
abstract class Formal extends TreeNode {
    protected Formal(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);

}


/** Defines list phylum Formals
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Formals extends ListNode {
    public final static Class elementClass = Formal.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Formals(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Formals" list */
    public Formals(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Formal" element to this list */
    public Formals appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Formals(lineNumber, copyElements());
    }
}


/** Defines simple phylum Expression */
abstract class Expression extends TreeNode {
    protected Expression(int lineNumber) {
        super(lineNumber);
    }
    private AbstractSymbol type = null;                                 
    public AbstractSymbol get_type() { return type; }           
    public Expression set_type(AbstractSymbol s) { type = s; return this; } 
    public abstract void dump_with_types(PrintStream out, int n);
    public void dump_type(PrintStream out, int n) {
        if (type != null)
            { out.println(Utilities.pad(n) + ": " + type.getString()); }
        else
            { out.println(Utilities.pad(n) + ": _no_type"); }
    }
    public abstract void code(CgenNode node, SymbolTable symbolTable, PrintStream s);
    public abstract void calculateAttrMaxLocalVariableCount(CgenNode node);
}


/** Defines list phylum Expressions
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Expressions extends ListNode {
    public final static Class elementClass = Expression.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Expressions(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Expressions" list */
    public Expressions(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Expression" element to this list */
    public Expressions appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Expressions(lineNumber, copyElements());
    }
}


/** Defines simple phylum Case */
abstract class Case extends TreeNode {
    protected Case(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);

}


/** Defines list phylum Cases
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Cases extends ListNode {
    public final static Class elementClass = Case.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Cases(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Cases" list */
    public Cases(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Case" element to this list */
    public Cases appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Cases(lineNumber, copyElements());
    }
}


/** Defines AST constructor 'program'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class program extends Program {
    public Classes classes;
    /** Creates "program" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for classes
      */
    public program(int lineNumber, Classes a1) {
        super(lineNumber);
        classes = a1;
    }
    public TreeNode copy() {
        return new program(lineNumber, (Classes)classes.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "program\n");
        classes.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_program");
        for (Enumeration e = classes.getElements(); e.hasMoreElements(); ) {
	    ((Class_)e.nextElement()).dump_with_types(out, n + 1);
        }
    }
    /** This method is the entry point to the semantic checker.  You will
        need to complete it in programming assignment 4.
	<p>
        Your checker should do the following two things:
	<ol>
	<li>Check that the program is semantically correct
	<li>Decorate the abstract syntax tree with type information
        by setting the type field in each Expression node.
        (see tree.h)
	</ol>
	<p>
	You are free to first do (1) and make sure you catch all semantic
    	errors. Part (2) can be done in a second stage when you want
	to test the complete compiler.
    */
    public void semant() {
	/* ClassTable constructor may do some semantic analysis */
	ClassTable classTable = new ClassTable(classes);
	
	/* some semantic analysis code may go here */

	if (classTable.errors()) {
	    System.err.println("Compilation halted due to static semantic errors.");
	    System.exit(1);
	}
    }
    /** This method is the entry point to the code generator.  All of the work
      * of the code generator takes place within CgenClassTable constructor.
      * @param s the output stream 
      * @see CgenClassTable
      * */
    public void cgen(PrintStream s) 
    {
        // spim wants comments to start with '#'
        s.print("# start of generated code\n");

	CgenClassTable codegen_classtable = new CgenClassTable(classes, s);

	s.print("\n# end of generated code\n");
    }

}


/** Defines AST constructor 'class_'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class class_ extends Class_ {
    public AbstractSymbol name;
    public AbstractSymbol parent;
    public Features features;
    public AbstractSymbol filename;
    /** Creates "class_" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for parent
      * @param a2 initial value for features
      * @param a3 initial value for filename
      */
    public class_(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Features a3, AbstractSymbol a4) {
        super(lineNumber);
        name = a1;
        parent = a2;
        features = a3;
        filename = a4;
    }
    public TreeNode copy() {
        return new class_(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(parent), (Features)features.copy(), copy_AbstractSymbol(filename));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "class_\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, parent);
        features.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, filename);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_class");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, parent);
        out.print(Utilities.pad(n + 2) + "\"");
        Utilities.printEscapedString(out, filename.getString());
        out.println("\"\n" + Utilities.pad(n + 2) + "(");
        for (Enumeration e = features.getElements(); e.hasMoreElements();) {
	    ((Feature)e.nextElement()).dump_with_types(out, n + 2);
        }
        out.println(Utilities.pad(n + 2) + ")");
    }
    public AbstractSymbol getName()     { return name; }
    public AbstractSymbol getParent()   { return parent; }
    public AbstractSymbol getFilename() { return filename; }
    public Features getFeatures()       { return features; }

}


/** Defines AST constructor 'method'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class method extends Feature {
    public AbstractSymbol name;
    public Formals formals;
    public AbstractSymbol return_type;
    public Expression expr;
    /** Creates "method" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for formals
      * @param a2 initial value for return_type
      * @param a3 initial value for expr
      */
    public method(int lineNumber, AbstractSymbol a1, Formals a2, AbstractSymbol a3, Expression a4) {
        super(lineNumber);
        name = a1;
        formals = a2;
        return_type = a3;
        expr = a4;
    }
    public TreeNode copy() {
        return new method(lineNumber, copy_AbstractSymbol(name), (Formals)formals.copy(), copy_AbstractSymbol(return_type), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "method\n");
        dump_AbstractSymbol(out, n+2, name);
        formals.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, return_type);
        expr.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_method");
        dump_AbstractSymbol(out, n + 2, name);
        for (Enumeration e = formals.getElements(); e.hasMoreElements();) {
	    ((Formal)e.nextElement()).dump_with_types(out, n + 2);
        }
        dump_AbstractSymbol(out, n + 2, return_type);
	expr.dump_with_types(out, n + 2);
    }

    public void code(CgenNode node, PrintStream s) {
    }
}


/** Defines AST constructor 'attr'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class attr extends Feature {
    public AbstractSymbol name;
    public AbstractSymbol type_decl;
    public Expression init;
    public String default_value;
    public Integer object_offset;
    
    /** Creates "attr" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for type_decl
      * @param a2 initial value for init
      */
    public attr(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Expression a3) {
        super(lineNumber);
        name = a1;
        type_decl = a2;
        init = a3;
    }
    public TreeNode copy() {
        return new attr(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl), (Expression)init.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "attr\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, type_decl);
        init.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_attr");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, type_decl);
	init.dump_with_types(out, n + 2);
    }

    public void code(CgenNode node, PrintStream s) {
    	
    }
}


/** Defines AST constructor 'formal'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class formal extends Formal {
    public AbstractSymbol name;
    public AbstractSymbol type_decl;
    /** Creates "formal" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for type_decl
      */
    public formal(int lineNumber, AbstractSymbol a1, AbstractSymbol a2) {
        super(lineNumber);
        name = a1;
        type_decl = a2;
    }
    public TreeNode copy() {
        return new formal(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "formal\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, type_decl);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_formal");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, type_decl);
    }

}


/** Defines AST constructor 'branch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class branch extends Case {
    public AbstractSymbol name;
    public AbstractSymbol type_decl;
    public Expression expr;
    /** Creates "branch" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for type_decl
      * @param a2 initial value for expr
      */
    public branch(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Expression a3) {
        super(lineNumber);
        name = a1;
        type_decl = a2;
        expr = a3;
    }
    public TreeNode copy() {
        return new branch(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "branch\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, type_decl);
        expr.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_branch");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, type_decl);
	expr.dump_with_types(out, n + 2);
    }

}


/** Defines AST constructor 'assign'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class assign extends Expression {
    public AbstractSymbol name;
    public Expression expr;
    /** Creates "assign" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for expr
      */
    public assign(int lineNumber, AbstractSymbol a1, Expression a2) {
        super(lineNumber);
        name = a1;
        expr = a2;
    }
    public TreeNode copy() {
        return new assign(lineNumber, copy_AbstractSymbol(name), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "assign\n");
        dump_AbstractSymbol(out, n+2, name);
        expr.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_assign");
        dump_AbstractSymbol(out, n + 2, name);
	expr.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenNode node, SymbolTable symbolTable, PrintStream s) {
    	// find class attribute being assigned to
    	//AttributeEntry attribute = node.findAttribute(this.name);
    	
    	// generate code for both the expression and the assignment
    	// the assignment assumes that the value/object of the expression
    	// is in the $a0 register and $so points to the object whose
    	// attribute is being assigned to
    	//this.expr.code(node, symbolTable, s);
    	//Integer objectOffset = attribute.getObjectOffset();
    	//CgenSupport.emitStore("$a0", objectOffset, "$s0", s);

    	this.expr.code(node, symbolTable, s);
    	
    	SymbolTableEntry entry = (SymbolTableEntry)symbolTable.lookup(this.name);
    	if (entry == null) return;
    	
    	Integer offset = entry.getOffset();
    	if (entry.getOffsetBaseType().equals(OffsetBaseType.Object)) {
        	//Integer objectOffset = attribute.getObjectOffset();
        	CgenSupport.emitStore("$a0", offset, "$s0", s);
    	} else if (entry.getOffsetBaseType().equals(OffsetBaseType.ActivationRecord)) {
    		CgenSupport.emitStore("$a0", offset, "$fp", s);
    	}
    }
    
    public void calculateAttrMaxLocalVariableCount(CgenNode node) {
    	this.expr.calculateAttrMaxLocalVariableCount(node);
    }
}


/** Defines AST constructor 'static_dispatch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class static_dispatch extends Expression {
    public Expression expr;
    public AbstractSymbol type_name;
    public AbstractSymbol name;
    public Expressions actual;
    /** Creates "static_dispatch" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for expr
      * @param a1 initial value for type_name
      * @param a2 initial value for name
      * @param a3 initial value for actual
      */
    public static_dispatch(int lineNumber, Expression a1, AbstractSymbol a2, AbstractSymbol a3, Expressions a4) {
        super(lineNumber);
        expr = a1;
        type_name = a2;
        name = a3;
        actual = a4;
    }
    public TreeNode copy() {
        return new static_dispatch(lineNumber, (Expression)expr.copy(), copy_AbstractSymbol(type_name), copy_AbstractSymbol(name), (Expressions)actual.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "static_dispatch\n");
        expr.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, type_name);
        dump_AbstractSymbol(out, n+2, name);
        actual.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_static_dispatch");
	expr.dump_with_types(out, n + 2);
        dump_AbstractSymbol(out, n + 2, type_name);
        dump_AbstractSymbol(out, n + 2, name);
        out.println(Utilities.pad(n + 2) + "(");
        for (Enumeration e = actual.getElements(); e.hasMoreElements();) {
	    ((Expression)e.nextElement()).dump_with_types(out, n + 2);
        }
        out.println(Utilities.pad(n + 2) + ")");
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenNode node, SymbolTable symbolTable, PrintStream s) {
    	// code each actual argument expression and push onto stack
    	for(Enumeration e = actual.getElements(); e.hasMoreElements(); ) {
    		Expression actualExpr = (Expression) e.nextElement();
    		actualExpr.code(node, symbolTable, s);
    		
    		// push onto stack
    		CgenSupport.emitStore("$a0", 0, "$sp", s);
    		CgenSupport.emitAddiu("$sp", "$sp", -4, s);
    	}
    	
    	// code dispatch expression
    	this.expr.code(node, symbolTable, s);
    	
    	// check to see if dispatch expression is void and call _dispatch_abort if so.
    	Integer continueLabelNumber = LabelSequencer.getInstance().getLabelNumber();
    	CgenSupport.emitBne("$a0", "$zero", continueLabelNumber, s);
    	StringSymbol fileNameSymbol = (StringSymbol)AbstractTable.stringtable.lookup(node.filename.getString());
    	CgenSupport.emitLoadString("$a0", fileNameSymbol, s);
    	CgenSupport.emitLoadImm("$t1", this.lineNumber, s);
    	CgenSupport.emitJal("_dispatch_abort", s);
    	CgenSupport.emitLabelDef(continueLabelNumber, s);
    	
    	// if not void, then get dispatch address from object's dispatch table for 
    	//     the static class referenced
    	//     register $a0 will contain reference to dispatch object
    	CgenSupport.emitLoadAddress("$t1", this.type_name + CgenSupport.DISPTAB_SUFFIX, s);
    	//CgenSupport.emitLoadAddress("$a0", this.type_name + CgenSupport.PROTOBJ_SUFFIX, s);    	
    	//CgenSupport.emitLoad("$t1", 2, "$a0", s); // get pointer to object's dispatch table

    	CgenNode staticClassNode = (CgenNode)symbolTable.lookup(this.type_name);
    	Vector<DispatchTableEntry> dispatchTable = staticClassNode.getDispatchTable();
    	Integer dispatchTableIndex = -1;
    	Integer dispatchTableSize = dispatchTable.size();
    	for (Integer i = 0; i < dispatchTableSize; i++) {
    		DispatchTableEntry entry = dispatchTable.get(i);
    		if (entry.getMethodName().equals(this.name.getString())) {
    			dispatchTableIndex = i;
    			break;
    		}
    	}
    	CgenSupport.emitLoad("$t1", dispatchTableIndex, "$t1", s);
    	
    	// jump to dispatch address (call method)
    	CgenSupport.emitJalr("$t1", s);
    }
    
    public void calculateAttrMaxLocalVariableCount(CgenNode node) {
    	this.expr.calculateAttrMaxLocalVariableCount(node);
    	for(Enumeration e = this.actual.getElements(); e.hasMoreElements(); ) {
    		Expression actualExpr = (Expression) e.nextElement();
    		actualExpr.calculateAttrMaxLocalVariableCount(node);
    	}
    }
}


/** Defines AST constructor 'dispatch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class dispatch extends Expression {
    public Expression expr;
    public AbstractSymbol name;
    public Expressions actual;
    /** Creates "dispatch" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for expr
      * @param a1 initial value for name
      * @param a2 initial value for actual
      */
    public dispatch(int lineNumber, Expression a1, AbstractSymbol a2, Expressions a3) {
        super(lineNumber);
        expr = a1;
        name = a2;
        actual = a3;
    }
    public TreeNode copy() {
        return new dispatch(lineNumber, (Expression)expr.copy(), copy_AbstractSymbol(name), (Expressions)actual.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "dispatch\n");
        expr.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, name);
        actual.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_dispatch");
	expr.dump_with_types(out, n + 2);
        dump_AbstractSymbol(out, n + 2, name);
        out.println(Utilities.pad(n + 2) + "(");
        for (Enumeration e = actual.getElements(); e.hasMoreElements();) {
	    ((Expression)e.nextElement()).dump_with_types(out, n + 2);
        }
        out.println(Utilities.pad(n + 2) + ")");
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenNode node, SymbolTable symbolTable, PrintStream s) {
    	// code each actual argument expression and push onto stack
    	for(Enumeration e = actual.getElements(); e.hasMoreElements(); ) {
    		Expression actualExpr = (Expression) e.nextElement();
    		actualExpr.code(node, symbolTable, s);
    		
    		// push onto stack
    		CgenSupport.emitStore("$a0", 0, "$sp", s);
    		CgenSupport.emitAddiu("$sp", "$sp", -4, s);
    	}
    	
    	// code dispatch expression
    	this.expr.code(node, symbolTable, s);
    	
    	// check to see if dispatch expression is void and call _dispatch_abort if so.
    	Integer continueLabelNumber = LabelSequencer.getInstance().getLabelNumber();
    	CgenSupport.emitBne("$a0", "$zero", continueLabelNumber, s);
    	StringSymbol fileNameSymbol = (StringSymbol)AbstractTable.stringtable.lookup(node.filename.getString());
    	CgenSupport.emitLoadString("$a0", fileNameSymbol, s);
    	CgenSupport.emitLoadImm("$t1", this.lineNumber, s);
    	CgenSupport.emitJal("_dispatch_abort", s);
    	CgenSupport.emitLabelDef(continueLabelNumber, s);
    	
    	// if not void, then get dispatch address from object's dispatch table for 
    	//     the class of the dispatch expression
    	//     register $a0 will contain reference to dispatch object
    	CgenSupport.emitLoad("$t1", 2, "$a0", s); // get pointer to object's dispatch table
    	Vector<DispatchTableEntry> dispatchTable = null;
    	if (this.expr.get_type().getString().equals(TreeConstants.SELF_TYPE.getString())) {
    		dispatchTable = node.getDispatchTable();
    	}
    	else {
    		CgenNode dispatchObject = (CgenNode)symbolTable.lookup(this.expr.get_type());   
    		dispatchTable = dispatchObject.getDispatchTable();
    	}
    	//Vector<DispatchTableEntry> dispatchTable = node.getDispatchTable();
    	Integer dispatchTableIndex = -1;
    	Integer dispatchTableSize = dispatchTable.size();
    	for (Integer i = 0; i < dispatchTableSize; i++) {
    		DispatchTableEntry entry = dispatchTable.get(i);
    		if (entry.getMethodName().equals(this.name.getString())) {
    			dispatchTableIndex = i;
    			break;
    		}
    	}
    	CgenSupport.emitLoad("$t1", dispatchTableIndex, "$t1", s);
    	
    	// jump to dispatch address (call method)
    	CgenSupport.emitJalr("$t1", s);
    }
    
    public void calculateAttrMaxLocalVariableCount(CgenNode node) {
    	this.expr.calculateAttrMaxLocalVariableCount(node);
    	for(Enumeration e = actual.getElements(); e.hasMoreElements(); ) {
    		Expression actualExpr = (Expression) e.nextElement();
    		actualExpr.calculateAttrMaxLocalVariableCount(node);
    	}
    }
}


/** Defines AST constructor 'cond'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class cond extends Expression {
    public Expression pred;
    public Expression then_exp;
    public Expression else_exp;
    /** Creates "cond" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for pred
      * @param a1 initial value for then_exp
      * @param a2 initial value for else_exp
      */
    public cond(int lineNumber, Expression a1, Expression a2, Expression a3) {
        super(lineNumber);
        pred = a1;
        then_exp = a2;
        else_exp = a3;
    }
    public TreeNode copy() {
        return new cond(lineNumber, (Expression)pred.copy(), (Expression)then_exp.copy(), (Expression)else_exp.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "cond\n");
        pred.dump(out, n+2);
        then_exp.dump(out, n+2);
        else_exp.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_cond");
	pred.dump_with_types(out, n + 2);
	then_exp.dump_with_types(out, n + 2);
	else_exp.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenNode node, SymbolTable symbolTable, PrintStream s) {
    	Integer elseLabelNumber = LabelSequencer.getInstance().getLabelNumber();
    	Integer exitLabelNumber = LabelSequencer.getInstance().getLabelNumber();
    	
    	// code the predicate
    	pred.code(node,  symbolTable, s);
    	
    	// if true, then code the then expression
    	CgenSupport.emitLoad("$t1", 3, "$a0", s);
    	CgenSupport.emitBeqz("$t1", elseLabelNumber, s);
    	
    	then_exp.code(node, symbolTable, s);
    	
    	CgenSupport.emitBranch(exitLabelNumber, s);
    	
    	// else, then code the else expression
    	CgenSupport.emitLabelDef(elseLabelNumber, s);
    	
    	else_exp.code(node, symbolTable, s);
    	
    	// generate label definition for exit
    	CgenSupport.emitLabelDef(exitLabelNumber, s);
    }
    
    public void calculateAttrMaxLocalVariableCount(CgenNode node) {
    	this.pred.calculateAttrMaxLocalVariableCount(node);
    	this.then_exp.calculateAttrMaxLocalVariableCount(node);
    	this.else_exp.calculateAttrMaxLocalVariableCount(node);
    }
}


/** Defines AST constructor 'loop'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class loop extends Expression {
    public Expression pred;
    public Expression body;
    /** Creates "loop" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for pred
      * @param a1 initial value for body
      */
    public loop(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        pred = a1;
        body = a2;
    }
    public TreeNode copy() {
        return new loop(lineNumber, (Expression)pred.copy(), (Expression)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "loop\n");
        pred.dump(out, n+2);
        body.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_loop");
	pred.dump_with_types(out, n + 2);
	body.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenNode node, SymbolTable symbolTable, PrintStream s) {
    	// generate label for start of loop
    	Integer startLoopLabelNumber = LabelSequencer.getInstance().getLabelNumber();    	
    	CgenSupport.emitLabelDef(startLoopLabelNumber, s);
    	
    	// code the predicate, which returns 
    	// a boolean object reference in register $a0
    	this.pred.code(node, symbolTable, s);
    	
    	// check to see if predicate is false, if so, then jump to exit of loop
    	Integer exitLoopLabelNumber = LabelSequencer.getInstance().getLabelNumber();
    	CgenSupport.emitLoad("$t1", 3, "$a0", s);
    	CgenSupport.emitBeq("$t1", "$zero", exitLoopLabelNumber, s);
    	
    	// code the body of the loop
    	this.body.code(node, symbolTable, s);
    	
    	// jump to start of loop
    	CgenSupport.emitBranch(startLoopLabelNumber, s);
    	
    	// generate label for exit of loop
    	CgenSupport.emitLabelDef(exitLoopLabelNumber, s);
    	
    	CgenSupport.emitMove("$a0", "$zero", s);
    }
    
    public void calculateAttrMaxLocalVariableCount(CgenNode node) {
    	this.pred.calculateAttrMaxLocalVariableCount(node);
    	this.body.calculateAttrMaxLocalVariableCount(node);
    }
}


/** Defines AST constructor 'typcase'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class typcase extends Expression {
    public Expression expr;
    public Cases cases;
    /** Creates "typcase" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for expr
      * @param a1 initial value for cases
      */
    public typcase(int lineNumber, Expression a1, Cases a2) {
        super(lineNumber);
        expr = a1;
        cases = a2;
    }
    public TreeNode copy() {
        return new typcase(lineNumber, (Expression)expr.copy(), (Cases)cases.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "typcase\n");
        expr.dump(out, n+2);
        cases.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_typcase");
	expr.dump_with_types(out, n + 2);
        for (Enumeration e = cases.getElements(); e.hasMoreElements();) {
	    ((Case)e.nextElement()).dump_with_types(out, n + 2);
        }
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenNode node, SymbolTable symbolTable, PrintStream s) {
    	// get label for exit of case expression
    	Integer exitLabelNumber = LabelSequencer.getInstance().getLabelNumber();

    	// get label for start of branch checks
    	Integer startLabelNumber = LabelSequencer.getInstance().getLabelNumber();
    	
    	// check object value of expression for void (=0) and if so
    	//     jump to _case_abort2 ($a0=filename, $t1=linenumber)
    	this.expr.code(node, symbolTable, s);
    	CgenSupport.emitBne("$a0", "$zero", startLabelNumber, s);
    	StringSymbol fileNameSymbol = (StringSymbol)AbstractTable.stringtable.lookup(
    			node.filename.getString());
    	CgenSupport.emitLoadString("$a0", fileNameSymbol, s);
    	CgenSupport.emitLoadImm("$t1", this.lineNumber, s);
    	CgenSupport.emitJal("_case_abort2", s);
    	CgenSupport.emitLabelDef(startLabelNumber, s);
    	
    	// determine range of classtags to check based on class sub-hierarchy for each 
    	// branch in the case statement.  The order needs to be in descending order based on 
    	// minimum classTag value for the case branch
    	ArrayList<CaseBranchClassTagRange> caseBranches = new ArrayList<CaseBranchClassTagRange>();
    	for (Enumeration e = cases.getElements(); e.hasMoreElements(); ) {
    		branch caseBranch = (branch)e.nextElement();
    		Integer[] minMax = new Integer[2];
    		minMax[0] = Integer.MAX_VALUE;
    		minMax[1] = Integer.MIN_VALUE;
    		GetClassTagRangeForTypeHierarchy(minMax, caseBranch.type_decl, symbolTable);
    		
    		CaseBranchClassTagRange caseBranchClassTagRange = new CaseBranchClassTagRange(caseBranch, minMax[0], minMax[1]);
    		caseBranches.add(caseBranchClassTagRange);
    	}
    	caseBranches.sort(new CaseBranchClassTagRange.CaseBranchClassTagRangeSortByMinValueDescending());
    	
    	// for each branch, do the following:
    	node.incrementCurrentAttrLocalVariableCount(1);
    	Boolean firstCaseBranch = true;
    	for(CaseBranchClassTagRange caseBranchClassTagRange: caseBranches) {
    	//for(Enumeration e = cases.getElements(); e.hasMoreElements(); ) {
    		branch caseBranch = caseBranchClassTagRange.getCaseBranch();
    		
        	//     get label for start of next branch
        	Integer nextBranchLabelNumber = LabelSequencer.getInstance().getLabelNumber();
        	
        	//     if classtag of object value of expression not in range, jump to next branch
        	if (firstCaseBranch) {
            	CgenSupport.emitLoad("$t2", 0, "$a0", s);
            	firstCaseBranch = false;
        	}
        	CgenSupport.emitBlti("$t2", caseBranchClassTagRange.getMinClassTag(), nextBranchLabelNumber, s);
        	CgenSupport.emitBgti("$t2", caseBranchClassTagRange.getMaxClassTag(), nextBranchLabelNumber, s);
        	
        	//     if in range, then create new scope, put the branch variable in the scope,
        	//     and code the branch expression
        	symbolTable.enterScope();
        	
        	//node.incrementCurrentAttrLocalVariableCount(1);
        	
        	Integer offset = node.getAttrMaxLocalVariableCount() - node.getCurrentLocalVariableCount();
        	SymbolTableEntry entry = new SymbolTableEntry(offset, OffsetBaseType.ActivationRecord, caseBranch.type_decl);
        	symbolTable.addId(caseBranch.name, entry);
        	CgenSupport.emitStore("$a0", offset, "$fp", s); // save case object to branch variable
        	
        	caseBranch.expr.code(node, symbolTable, s);
        	
        	symbolTable.exitScope();
        	
        	//     jump to label for exit of case expression
        	CgenSupport.emitBranch(exitLabelNumber, s);
        	
        	CgenSupport.emitLabelDef(nextBranchLabelNumber, s);
    	}
    	
    	// generate code for case where no branches match, calling _case_abort
    	CgenSupport.emitJal("_case_abort", s);
    	
    	CgenSupport.emitLabelDef(exitLabelNumber, s);
    }
    
    private void GetClassTagRangeForTypeHierarchy(Integer[] minMax, AbstractSymbol typeName, SymbolTable symbolTable) {
    	CgenNode classNode = (CgenNode)symbolTable.lookup(typeName);
    	Integer classTag = classNode.getClassTag();
    	if (classTag < minMax[0]) minMax[0] = classTag;
    	if (classTag > minMax[1]) minMax[1] = classTag;
    	
    	for (Integer i = classNode.getNumChildren() - 1; i >= 0; i--) {
    		CgenNode nd = classNode.getNthChild(i);
    		GetClassTagRangeForTypeHierarchy(minMax, nd.name, symbolTable);
    	}    	
    }
    
    public void calculateAttrMaxLocalVariableCount(CgenNode node) {
    	this.expr.calculateAttrMaxLocalVariableCount(node);
    	for(Enumeration e = this.cases.getElements(); e.hasMoreElements(); ) {
    		branch caseBranch = (branch)e.nextElement();
    		node.incrementCurrentAttrLocalVariableCount(1);
    		caseBranch.expr.calculateAttrMaxLocalVariableCount(node);
    		node.decrementCurrentAttrLocalVariableCount(1);
    	}
    }
}


/** Defines AST constructor 'block'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class block extends Expression {
    public Expressions body;
    /** Creates "block" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for body
      */
    public block(int lineNumber, Expressions a1) {
        super(lineNumber);
        body = a1;
    }
    public TreeNode copy() {
        return new block(lineNumber, (Expressions)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "block\n");
        body.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_block");
        for (Enumeration e = body.getElements(); e.hasMoreElements();) {
	    ((Expression)e.nextElement()).dump_with_types(out, n + 2);
        }
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenNode node, SymbolTable symbolTable, PrintStream s) {
    	for(Enumeration e = this.body.getElements(); e.hasMoreElements(); ) {
    		Expression expr = (Expression)e.nextElement();
    		expr.code(node, symbolTable, s);
    	}
    }
    
    public void calculateAttrMaxLocalVariableCount(CgenNode node) {
    	for(Enumeration e = this.body.getElements(); e.hasMoreElements(); ) {
    		Expression expr = (Expression)e.nextElement();
    		expr.calculateAttrMaxLocalVariableCount(node);
    	}
    }
}


/** Defines AST constructor 'let'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class let extends Expression {
    public AbstractSymbol identifier;
    public AbstractSymbol type_decl;
    public Expression init;
    public Expression body;
    /** Creates "let" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for identifier
      * @param a1 initial value for type_decl
      * @param a2 initial value for init
      * @param a3 initial value for body
      */
    public let(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Expression a3, Expression a4) {
        super(lineNumber);
        identifier = a1;
        type_decl = a2;
        init = a3;
        body = a4;
    }
    public TreeNode copy() {
        return new let(lineNumber, copy_AbstractSymbol(identifier), copy_AbstractSymbol(type_decl), (Expression)init.copy(), (Expression)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "let\n");
        dump_AbstractSymbol(out, n+2, identifier);
        dump_AbstractSymbol(out, n+2, type_decl);
        init.dump(out, n+2);
        body.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_let");
	dump_AbstractSymbol(out, n + 2, identifier);
	dump_AbstractSymbol(out, n + 2, type_decl);
	init.dump_with_types(out, n + 2);
	body.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenNode node, SymbolTable symbolTable, PrintStream s) {
    	// initialize the let variable
    	if (this.init instanceof no_expr) {
    		// initialize the let variable to its default based on type declaration
    		String strTypeDecl = this.type_decl.getString();
    		if (strTypeDecl.equals(TreeConstants.Int.getString())) {
    			CgenSupport.emitLoadInt(CgenSupport.ACC, (IntSymbol)AbstractTable.inttable.lookup("0"), s);
    		} else if (strTypeDecl.equals(TreeConstants.Bool.getString())) {
    			CgenSupport.emitLoadBool(CgenSupport.ACC, new BoolConst(false), s);
    		} else if (strTypeDecl.equals(TreeConstants.Str.getString())) {
    			CgenSupport.emitLoadString(CgenSupport.ACC, (StringSymbol)AbstractTable.stringtable.lookup(""), s);
    		} else { // all other objects initialized to void
    			CgenSupport.emitMove(CgenSupport.ACC, "$zero", s);
    		}
    	}
    	else {
        	this.init.code(node, symbolTable, s);
    	}
    	
    	symbolTable.enterScope();
    	node.incrementCurrentAttrLocalVariableCount(1);
    	
    	Integer offset = node.getAttrMaxLocalVariableCount() - node.getCurrentLocalVariableCount();
    	SymbolTableEntry entry = new SymbolTableEntry(offset, OffsetBaseType.ActivationRecord, this.type_decl);
    	symbolTable.addId(this.identifier, entry);
    			
		// assign object of the attribute initializer expression to the attribute of the class
		
    	CgenSupport.emitStore("$a0", offset, "$fp", s);
    	
    	this.body.code(node,  symbolTable,  s);
    	
    	node.decrementCurrentAttrLocalVariableCount(1);
    	symbolTable.exitScope();
    }
    
    public void calculateAttrMaxLocalVariableCount(CgenNode node) {
    	node.incrementCurrentAttrLocalVariableCount(1);
    	init.calculateAttrMaxLocalVariableCount(node);
    	body.calculateAttrMaxLocalVariableCount(node);
    	node.decrementCurrentAttrLocalVariableCount(1);
    }
}


/** Defines AST constructor 'plus'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class plus extends Expression {
    public Expression e1;
    public Expression e2;
    /** Creates "plus" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public plus(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new plus(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "plus\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_plus");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenNode node, SymbolTable symbolTable, PrintStream s) {
    	e1.code(node, symbolTable, s);
    	
    	//sw $a0 0($sp) // store result on the stack, do a push
    	//addiu $sp $sp -4
    	//CgenSupport.emitLoad("$a0", 3, "$a0", s); // get value of first Int object
    	CgenSupport.emitStore("$a0", 0, "$sp", s); // store value on stack
    	CgenSupport.emitAddiu("$sp", "$sp", -4, s);
    	
    	e2.code(node, symbolTable, s);
    	
    	//lw $t1 4($sp) // load value on stack in $t1
    	//add $a0 $t1 $a0 // add values in $t1 and $a0, result in $a0
    	//addiu $sp $sp 4 // pop value off stack
    	CgenSupport.emitJal("Object.copy", s);
    	CgenSupport.emitLoad("$t1", 1, "$sp", s);  // get value of first Int object from stack
    	CgenSupport.emitLoad("$t1", 3, "$t1", s);
    	CgenSupport.emitLoad("$t2", 3, "$a0", s); // get value of second Int object
    	CgenSupport.emitAdd("$t1", "$t1", "$t2", s); // add the two values
    	CgenSupport.emitStore("$t1", 3, "$a0", s);
    	CgenSupport.emitAddiu("$sp", "$sp", 4, s); // pop the temporary value off the stack
    }
    
    public void calculateAttrMaxLocalVariableCount(CgenNode node) {
    	this.e1.calculateAttrMaxLocalVariableCount(node);
    	this.e2.calculateAttrMaxLocalVariableCount(node);
    }
}


/** Defines AST constructor 'sub'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class sub extends Expression {
    public Expression e1;
    public Expression e2;
    /** Creates "sub" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public sub(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new sub(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "sub\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_sub");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenNode node, SymbolTable symbolTable, PrintStream s) {
    	e1.code(node, symbolTable, s);
    	
    	//sw $a0 0($sp) // store result on the stack, do a push
    	//addiu $sp $sp -4
    	//CgenSupport.emitLoad("$a0", 3, "$a0", s);
    	CgenSupport.emitStore("$a0", 0, "$sp", s);
    	CgenSupport.emitAddiu("$sp", "$sp", -4, s);
    	
    	e2.code(node, symbolTable, s);
    	
    	//lw $t1 4($sp) // load value on stack in $t1
    	//add $a0 $t1 $a0 // add values in $t1 and $a0, result in $a0
    	//addiu $sp $sp 4 // pop value off stack
    	CgenSupport.emitJal("Object.copy", s);
    	CgenSupport.emitLoad("$t1", 1, "$sp", s);
    	CgenSupport.emitLoad("$t1", 3, "$t1", s);
    	CgenSupport.emitLoad("$t2", 3, "$a0", s);
    	CgenSupport.emitSub("$t1", "$t1", "$t2", s);
    	CgenSupport.emitStore("$t1", 3, "$a0", s);
    	CgenSupport.emitAddiu("$sp", "$sp", 4, s);
    }
    
    public void calculateAttrMaxLocalVariableCount(CgenNode node) {
    	this.e1.calculateAttrMaxLocalVariableCount(node);
    	this.e2.calculateAttrMaxLocalVariableCount(node);
    }
}


/** Defines AST constructor 'mul'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class mul extends Expression {
    public Expression e1;
    public Expression e2;
    /** Creates "mul" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public mul(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new mul(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "mul\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_mul");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenNode node, SymbolTable symbolTable, PrintStream s) {
    	e1.code(node, symbolTable, s);
    	
    	CgenSupport.emitStore("$a0", 0, "$sp", s);
    	CgenSupport.emitAddiu("$sp", "$sp", -4, s);
    	
    	e2.code(node, symbolTable, s);
    	
    	CgenSupport.emitJal("Object.copy", s);
    	CgenSupport.emitLoad("$t1", 1, "$sp", s);
    	CgenSupport.emitLoad("$t1", 3, "$t1", s);
    	CgenSupport.emitLoad("$t2", 3, "$a0", s);
    	CgenSupport.emitMul("$t1", "$t1", "$t2", s);
    	CgenSupport.emitStore("$t1", 3, "$a0", s);
    	CgenSupport.emitAddiu("$sp", "$sp", 4, s);
    }
    
    public void calculateAttrMaxLocalVariableCount(CgenNode node) {
    	this.e1.calculateAttrMaxLocalVariableCount(node);
    	this.e2.calculateAttrMaxLocalVariableCount(node);
    }
}


/** Defines AST constructor 'divide'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class divide extends Expression {
    public Expression e1;
    public Expression e2;
    /** Creates "divide" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public divide(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new divide(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "divide\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_divide");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenNode node, SymbolTable symbolTable, PrintStream s) {
    	e1.code(node, symbolTable, s);
    	
    	CgenSupport.emitStore("$a0", 0, "$sp", s);
    	CgenSupport.emitAddiu("$sp", "$sp", -4, s);
    	
    	e2.code(node, symbolTable, s);
    	
    	CgenSupport.emitJal("Object.copy", s);
    	CgenSupport.emitLoad("$t1", 1, "$sp", s);
    	CgenSupport.emitLoad("$t1", 3, "$t1", s);
    	CgenSupport.emitLoad("$t2", 3, "$a0", s);
    	CgenSupport.emitDiv("$t1", "$t1", "$t2", s);
    	CgenSupport.emitStore("$t1", 3, "$a0", s);
    	CgenSupport.emitAddiu("$sp", "$sp", 4, s);
    }
    
    public void calculateAttrMaxLocalVariableCount(CgenNode node) {
    	this.e1.calculateAttrMaxLocalVariableCount(node);
    	this.e2.calculateAttrMaxLocalVariableCount(node);
    }
}


/** Defines AST constructor 'neg'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class neg extends Expression {
    public Expression e1;
    /** Creates "neg" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      */
    public neg(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
    }
    public TreeNode copy() {
        return new neg(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "neg\n");
        e1.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_neg");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenNode node, SymbolTable symbolTable, PrintStream s) {
    	this.e1.code(node, symbolTable, s);
    	
    	CgenSupport.emitJal("Object.copy", s);
    	CgenSupport.emitLoad("$t1", 3, "$a0", s);
    	CgenSupport.emitNeg("$t1", "$t1", s);
    	CgenSupport.emitStore("$t1", 3, "$a0", s);
    }
    
    public void calculateAttrMaxLocalVariableCount(CgenNode node) {
    	e1.calculateAttrMaxLocalVariableCount(node);
    }
}


/** Defines AST constructor 'lt'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class lt extends Expression {
    public Expression e1;
    public Expression e2;
    /** Creates "lt" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public lt(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new lt(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "lt\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_lt");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenNode node, SymbolTable symbolTable, PrintStream s) {
    	Integer lessThanLabelNumber = LabelSequencer.getInstance().getLabelNumber();
    	
    	// code expression1 and store on the stack
    	this.e1.code(node, symbolTable, s);
    	CgenSupport.emitStore("$a0", 0, "$sp", s);
    	CgenSupport.emitAddiu("$sp", "$sp", -4, s);
    	
    	// code epxression2
    	this.e2.code(node, symbolTable, s);
    	
    	// test to see if the first expression value is less than second expression value
    	// register $a0 contains reference to object from expression2
    	CgenSupport.emitLoad("$t1", 1, "$sp", s);
    	CgenSupport.emitLoad("$t1", 3, "$t1", s); // get integer value for expression1
    	CgenSupport.emitLoad("$t2", 3, "$a0", s); // get integer value for expression2
    	CgenSupport.emitLoadBool("$a0", new BoolConst(true), s);
    	CgenSupport.emitAddiu("$sp", "$sp", 4, s);
    	CgenSupport.emitBlt("$t1", "$t2", lessThanLabelNumber, s);
    	CgenSupport.emitLoadBool("$a0", new BoolConst(false), s);
    	
    	CgenSupport.emitLabelDef(lessThanLabelNumber, s);
    }
    
    public void calculateAttrMaxLocalVariableCount(CgenNode node) {
    	this.e1.calculateAttrMaxLocalVariableCount(node);
    	this.e2.calculateAttrMaxLocalVariableCount(node);
    }
}


/** Defines AST constructor 'eq'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class eq extends Expression {
    public Expression e1;
    public Expression e2;
    /** Creates "eq" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public eq(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new eq(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "eq\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_eq");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenNode node, SymbolTable symbolTable, PrintStream s) {
    	Integer equalsLabelNumber = LabelSequencer.getInstance().getLabelNumber();
    	
    	// code expression1 and store on the stack
    	e1.code(node, symbolTable, s);
    	CgenSupport.emitStore("$a0", 0, "$sp", s); // store value on stack
    	CgenSupport.emitAddiu("$sp", "$sp", -4, s);
    	
    	// code expression2
    	e2.code(node, symbolTable, s);
    	
    	// test to see if the two expression objects are the same object
    	CgenSupport.emitLoad("$t1", 1, "$sp", s);
    	CgenSupport.emitMove("$t2", "$a0", s);
    	CgenSupport.emitLoadBool(CgenSupport.ACC, new BoolConst(true), s);
    	CgenSupport.emitAddiu("$sp", "$sp", 4, s);
    	CgenSupport.emitBeq("$t1", "$t2", equalsLabelNumber, s);
    	CgenSupport.emitLoadBool("$a1", new BoolConst(false), s);
    	CgenSupport.emitJal("equality_test", s);
    	
    	// generate label definition for when objects are equal
    	CgenSupport.emitLabelDef(equalsLabelNumber, s);
    }
    
    public void calculateAttrMaxLocalVariableCount(CgenNode node) {
    	this.e1.calculateAttrMaxLocalVariableCount(node);
    	this.e2.calculateAttrMaxLocalVariableCount(node);
    }
}


/** Defines AST constructor 'leq'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class leq extends Expression {
    public Expression e1;
    public Expression e2;
    /** Creates "leq" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public leq(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new leq(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "leq\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_leq");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenNode node, SymbolTable symbolTable, PrintStream s) {
    	Integer lessThanOrEqualsLabelNumber = LabelSequencer.getInstance().getLabelNumber();
    	
    	// code expression1 and store on the stack
    	this.e1.code(node, symbolTable, s);
    	CgenSupport.emitStore("$a0", 0, "$sp", s);
    	CgenSupport.emitAddiu("$sp", "$sp", -4, s);
    	
    	// code epxression2
    	this.e2.code(node, symbolTable, s);
    	
    	// test to see if the first expression value is less than second expression value
    	// register $a0 contains reference to object from expression2
    	CgenSupport.emitLoad("$t1", 1, "$sp", s);
    	CgenSupport.emitLoad("$t1", 3, "$t1", s); // get integer value for expression1
    	CgenSupport.emitLoad("$t2", 3, "$a0", s); // get integer value for expression2
    	CgenSupport.emitLoadBool("$a0", new BoolConst(true), s);
    	CgenSupport.emitAddiu("$sp", "$sp", 4, s);
    	CgenSupport.emitBleq("$t1", "$t2", lessThanOrEqualsLabelNumber, s);
    	CgenSupport.emitLoadBool("$a0", new BoolConst(false), s);
    	
    	CgenSupport.emitLabelDef(lessThanOrEqualsLabelNumber, s);
    }
    
    public void calculateAttrMaxLocalVariableCount(CgenNode node) {
    	this.e1.calculateAttrMaxLocalVariableCount(node);
    	this.e2.calculateAttrMaxLocalVariableCount(node);
    }
}


/** Defines AST constructor 'comp'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class comp extends Expression {
    public Expression e1;
    /** Creates "comp" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      */
    public comp(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
    }
    public TreeNode copy() {
        return new comp(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "comp\n");
        e1.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_comp");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenNode node, SymbolTable symbolTable, PrintStream s) {
    	Integer falseLabelNumber = LabelSequencer.getInstance().getLabelNumber();
    	
    	this.e1.code(node, symbolTable, s);
    	
    	CgenSupport.emitLoad("$t1", 3, "$a0", s);
    	CgenSupport.emitLoadBool("$a0", new BoolConst(true), s);
    	CgenSupport.emitBeqz("$t1", falseLabelNumber, s);
    	CgenSupport.emitLoadBool("$a0", new BoolConst(false), s);
    	CgenSupport.emitLabelDef(falseLabelNumber, s);
    }
    
    public void calculateAttrMaxLocalVariableCount(CgenNode node) {
    	this.e1.calculateAttrMaxLocalVariableCount(node);
    }
}


/** Defines AST constructor 'int_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class int_const extends Expression {
    public AbstractSymbol token;
    /** Creates "int_const" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for token
      */
    public int_const(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        token = a1;
    }
    public TreeNode copy() {
        return new int_const(lineNumber, copy_AbstractSymbol(token));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "int_const\n");
        dump_AbstractSymbol(out, n+2, token);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_int");
	dump_AbstractSymbol(out, n + 2, token);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method method is provided
      * to you as an example of code generation.
      * @param s the output stream 
      * */
    public void code(CgenNode node, SymbolTable symbolTable, PrintStream s) {
    	CgenSupport.emitLoadInt(CgenSupport.ACC,
                                (IntSymbol)AbstractTable.inttable.lookup(token.getString()), s);
    }
    
    public void calculateAttrMaxLocalVariableCount(CgenNode node) {
    	
    }
}


/** Defines AST constructor 'bool_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class bool_const extends Expression {
    public Boolean val;
    /** Creates "bool_const" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for val
      */
    public bool_const(int lineNumber, Boolean a1) {
        super(lineNumber);
        val = a1;
    }
    public TreeNode copy() {
        return new bool_const(lineNumber, copy_Boolean(val));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "bool_const\n");
        dump_Boolean(out, n+2, val);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_bool");
	dump_Boolean(out, n + 2, val);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method method is provided
      * to you as an example of code generation.
      * @param s the output stream 
      * */
    public void code(CgenNode node, SymbolTable symbolTable, PrintStream s) {
	CgenSupport.emitLoadBool(CgenSupport.ACC, new BoolConst(val), s);
    }
    
    public void calculateAttrMaxLocalVariableCount(CgenNode node) {
    	
    }
}


/** Defines AST constructor 'string_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class string_const extends Expression {
    public AbstractSymbol token;
    /** Creates "string_const" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for token
      */
    public string_const(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        token = a1;
    }
    public TreeNode copy() {
        return new string_const(lineNumber, copy_AbstractSymbol(token));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "string_const\n");
        dump_AbstractSymbol(out, n+2, token);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_string");
	out.print(Utilities.pad(n + 2) + "\"");
	Utilities.printEscapedString(out, token.getString());
	out.println("\"");
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method method is provided
      * to you as an example of code generation.
      * @param s the output stream 
      * */
    public void code(CgenNode node, SymbolTable symbolTable, PrintStream s) {
	CgenSupport.emitLoadString(CgenSupport.ACC,
                                   (StringSymbol)AbstractTable.stringtable.lookup(token.getString()), s);
    }
    
    public void calculateAttrMaxLocalVariableCount(CgenNode node) {
    	
    }
}


/** Defines AST constructor 'new_'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class new_ extends Expression {
    public AbstractSymbol type_name;
    /** Creates "new_" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for type_name
      */
    public new_(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        type_name = a1;
    }
    public TreeNode copy() {
        return new new_(lineNumber, copy_AbstractSymbol(type_name));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "new_\n");
        dump_AbstractSymbol(out, n+2, type_name);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_new");
	dump_AbstractSymbol(out, n + 2, type_name);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenNode node, SymbolTable symbolTable, PrintStream s) {
    	if (this.type_name.getString().equals(TreeConstants.SELF_TYPE.getString())) {
    		CgenSupport.emitLoadAddress("$t1", CgenSupport.CLASSOBJTAB, s);
    		CgenSupport.emitLoad("$t2", 0, "$s0", s);
    		CgenSupport.emitSll("$t2", "$t2", 3, s);
    		CgenSupport.emitAddu("$t1", "$t1", "$t2", s);
    		CgenSupport.emitStore("$t1", 0, "$sp", s);
    		CgenSupport.emitAddiu("$sp", "$sp", -4, s);
    		CgenSupport.emitLoad("$a0", 0, "$t1", s);
    		CgenSupport.emitJal("Object.copy", s);
    		CgenSupport.emitLoad("$t1", 1, "$sp", s);
    		CgenSupport.emitLoad("$t1", 1, "$t1", s);
    		CgenSupport.emitJalr("$t1", s);
    		CgenSupport.emitAddiu("$sp", "$sp", 4, s);
    		
    	} else {
        	CgenSupport.emitLoadAddress("$a0", this.type_name + CgenSupport.PROTOBJ_SUFFIX, s);
        	CgenSupport.emitJal("Object.copy", s);
        	CgenSupport.emitJal(this.type_name + CgenSupport.CLASSINIT_SUFFIX, s);
    	}
    }
    
    public void calculateAttrMaxLocalVariableCount(CgenNode node) {
    	
    }
}


/** Defines AST constructor 'isvoid'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class isvoid extends Expression {
    public Expression e1;
    /** Creates "isvoid" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      */
    public isvoid(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
    }
    public TreeNode copy() {
        return new isvoid(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "isvoid\n");
        e1.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_isvoid");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenNode node, SymbolTable symbolTable, PrintStream s) {
    	Integer trueLabelNumber = LabelSequencer.getInstance().getLabelNumber();
    	
    	this.e1.code(node, symbolTable, s);
    	
    	CgenSupport.emitMove("$t1", "$a0", s);
    	CgenSupport.emitLoadBool(CgenSupport.ACC, new BoolConst(true), s);
    	CgenSupport.emitBeqz("$t1", trueLabelNumber, s);
    	CgenSupport.emitLoadBool(CgenSupport.ACC, new BoolConst(false), s);
    	CgenSupport.emitLabelDef(trueLabelNumber, s);
    }
    
    public void calculateAttrMaxLocalVariableCount(CgenNode node) {
    	this.e1.calculateAttrMaxLocalVariableCount(node);
    }
}


/** Defines AST constructor 'no_expr'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class no_expr extends Expression {
    /** Creates "no_expr" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      */
    public no_expr(int lineNumber) {
        super(lineNumber);
    }
    public TreeNode copy() {
        return new no_expr(lineNumber);
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "no_expr\n");
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_no_expr");
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenNode node, SymbolTable symbolTable, PrintStream s) {
    }
    
    public void calculateAttrMaxLocalVariableCount(CgenNode node) {
    	
    }
}


/** Defines AST constructor 'object'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class object extends Expression {
    public AbstractSymbol name;
    /** Creates "object" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      */
    public object(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        name = a1;
    }
    public TreeNode copy() {
        return new object(lineNumber, copy_AbstractSymbol(name));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "object\n");
        dump_AbstractSymbol(out, n+2, name);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_object");
	dump_AbstractSymbol(out, n + 2, name);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(CgenNode node, SymbolTable symbolTable, PrintStream s) {
    	//AttributeEntry attribute = node.findAttribute(this.name);
    	if (this.name.equals(TreeConstants.self)) {
    		CgenSupport.emitMove("$a0", "$s0", s); // self object always in register $s0
    	}
    	else {
        	SymbolTableEntry entry = (SymbolTableEntry)symbolTable.lookup(this.name);
        	if (entry == null) return;
        	
        	Integer offset = entry.getOffset();
        	if (entry.getOffsetBaseType().equals(OffsetBaseType.Object)) {
            	//Integer objectOffset = attribute.getObjectOffset();
            	CgenSupport.emitLoad("$a0", offset, "$s0", s);
        	} else if (entry.getOffsetBaseType().equals(OffsetBaseType.ActivationRecord)) {
        		CgenSupport.emitLoad("$a0", offset, "$fp", s);
        	}
    	}
    }
    
    public void calculateAttrMaxLocalVariableCount(CgenNode node) {
    	
    }
}


