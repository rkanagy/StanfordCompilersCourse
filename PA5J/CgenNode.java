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
import java.util.Enumeration;

class CgenNode extends class_ {
    /** The parent of this node in the inheritance tree */
    private CgenNode parent;

    /** The children of this node in the inheritance tree */
    private Vector children;

    /** Indicates a basic class */
    final static int Basic = 0;

    /** Indicates a class that came from a Cool program */
    final static int NotBasic = 1;
    
    /** Does this node correspond to a basic class? */
    private int basic_status;

    private int classTag;
    
    private Vector<DispatchTableEntry> dispatchTable;
    private Vector<AttributeEntry> attributes;
    private Integer maxAttrLocalVariableCount = 0;
    private Integer currentAttrLocalVariableCount = 0;
    
	/** Constructs a new CgenNode to represent class "c".
     * @param c the class
     * @param basic_status is this class basic or not
     * @param table the class table
     * */
    CgenNode(Class_ c, int basic_status, CgenClassTable table) {
	super(0, c.getName(), c.getParent(), c.getFeatures(), c.getFilename());
	this.parent = null;
	this.children = new Vector();
	this.basic_status = basic_status;
	AbstractTable.stringtable.addString(name.getString());
    }

    void addChild(CgenNode child) {
	children.addElement(child);
    }

    /** Gets the children of this class
     * @return the children
     * */
    Enumeration getChildren() {
	return children.elements(); 
    }

    Integer getNumChildren() {
    	return children.size();
    }
    
    CgenNode getNthChild(Integer n) {
    	return (CgenNode)children.get(n);
    }
    
    /** Sets the parent of this class.
     * @param parent the parent
     * */
    void setParentNd(CgenNode parent) {
	if (this.parent != null) {
	    Utilities.fatalError("parent already set in CgenNode.setParent()");
	}
	if (parent == null) {
	    Utilities.fatalError("null parent in CgenNode.setParent()");
	}
	this.parent = parent;
    }    
	

    /** Gets the parent of this class
     * @return the parent
     * */
    CgenNode getParentNd() {
	return parent; 
    }

    /** Returns true is this is a basic class.
     * @return true or false
     * */
    boolean basic() { 
	return basic_status == Basic; 
    }
    
    public int getClassTag() {
    	return this.classTag;
    }
    public void setClassTag(int classTag) {
    	this.classTag = classTag;
    }

	public Vector<DispatchTableEntry> getDispatchTable() {
		return dispatchTable;
	}

	public void setDispatchTable(Vector<DispatchTableEntry> dispatchTable) {
		this.dispatchTable = dispatchTable;
	}
    
    public Vector<AttributeEntry> getAttributes() {
		return attributes;
	}

	public void setAttributes(Vector<AttributeEntry> attributes) {
		this.attributes = attributes;
	}
	
	public AttributeEntry findAttribute(AbstractSymbol name) {
    	Vector<AttributeEntry> attributes = this.getAttributes();
    	AttributeEntry attribute = null;
    	for (Enumeration e = attributes.elements(); e.hasMoreElements(); ) {
    		attribute = (AttributeEntry)e.nextElement();
    		if (attribute.getName().equals(name)) {
    			break;
    		}
    	}
    	return attribute;
	}
	
	public void calculateAttrMaxLocalVariableCountStart() {
		this.maxAttrLocalVariableCount = 0;
		this.currentAttrLocalVariableCount = 0;
	}
	public void resetCurrentLocalVariableCount() {
		this.currentAttrLocalVariableCount = 0;
	}
	public void incrementCurrentAttrLocalVariableCount(Integer increment) {
		this.currentAttrLocalVariableCount += increment;
		if (this.currentAttrLocalVariableCount > this.maxAttrLocalVariableCount)
			this.maxAttrLocalVariableCount = this.currentAttrLocalVariableCount;
	}
	public void decrementCurrentAttrLocalVariableCount(Integer decrement) {
		this.currentAttrLocalVariableCount -= decrement;
	}
	public Integer getAttrMaxLocalVariableCount() {
		return this.maxAttrLocalVariableCount;
	}
	public Integer getCurrentLocalVariableCount() {
		return this.currentAttrLocalVariableCount;
	}
}
    

    
