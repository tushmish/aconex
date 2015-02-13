package com.aconex.gedcom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Node representing a Genealogical data. Acts as a placeholder to render data in XML format.
 *
 * @author tmishr
 */
public final class TreeNode {

	// -------- class variables -----------

	/** represents the current depth in the tree. **/
	private int level = -1;

	/** element name. **/
	private String elementName = "";

	/** element value. **/
	private String value = "";

	/** attributes key value pair. **/
	private final Map<String, String> attributeKeyValues = new HashMap<String, String>();

	/** child nodes for this element. **/
	private List<TreeNode> childNodes = new ArrayList<TreeNode>();

	/** attribute id. **/
	public static final String ATTRIBUTE_ID = "id";

	/** attribute value. **/
	public static final String ATTRIBUTE_VALUE = "value";

	// -------- methods -----------

	/**
	 * checks if the node has child nodes.
	 *
	 * @return 	true, if yes
	 * 			false, otherwise
	 */
	public boolean hasChildNodes() {
		if (childNodes != null && childNodes.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * adds a node as a child.
	 *
	 * @param 	childNode
	 * 			child node.
	 */
	public void addChild(final TreeNode childNode) {
		childNodes.add(childNode);
	}

	/**
	 * adds an attribute name and value pair that can be used in xml.
	 *
	 * @param 	key
	 * 			attribute name
	 * @param 	value
	 * 			attribute value
	 */
	public void addAttribute(final String key, final String value) {
		getAttributeKeyValues().put(key, value);
	}

	/**
	 * gets attribute value.
	 *
	 * @param 	key
	 * 			attribute name
	 * @return	attribute value.
	 */
	public String getValue(final String key) {
		if (!getAttributeKeyValues().containsKey(key)) {
			return null;
		}
		return getAttributeKeyValues().get(key);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getAttributeKeyValues() == null) ? 0 : getAttributeKeyValues().hashCode());
		result = prime * result + ((childNodes == null) ? 0 : childNodes.hashCode());
		result = prime * result + ((elementName == null) ? 0 : elementName.hashCode());
		result = prime * result + level;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final TreeNode other = (TreeNode) obj;
		if (getAttributeKeyValues() == null) {
			if (other.getAttributeKeyValues() != null) {
				return false;
			}
		} else if (!getAttributeKeyValues().equals(other.getAttributeKeyValues())) {
			return false;
		}
		if (childNodes == null) {
			if (other.childNodes != null) {
				return false;
			}
		} else if (!childNodes.equals(other.childNodes)) {
			return false;
		}
		if (elementName == null) {
			if (other.elementName != null) {
				return false;
			}
		} else if (!elementName.equals(other.elementName)) {
			return false;
		}
		if (level != other.level) {
			return false;
		}
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

	/**
	 * String representation of a node.
	 *
	 * @return node as string.
	 */
	@Override
	public String toString() {
		return "TreeNode [level=" + level + ", elementName=" + elementName + ", value=" + value
				+ ", attributeKeyValues=" + getAttributeKeyValues() + "]";
	}

	// -------- getters and setters -----------

	/**
	 * gets level.
	 *
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * sets the level.
	 *
	 * @param 	level
	 *          the level to set
	 */
	public void setLevel(final int level) {
		this.level = level;
	}

	/**
	 * gets element name.
	 *
	 * @return the element name.
	 */
	public String getElementName() {
		return elementName;
	}

	/**
	 * sets the element name.
	 *
	 * @param 	elementName
	 *          element name
	 */
	public void setElementName(final String elementName) {
		this.elementName = elementName;
	}

	/**
	 * gets node value.
	 *
	 * @return the node value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * sets the node value.
	 *
	 * @param 	value
	 *          node value
	 */
	public void setValue(final String value) {
		this.value = value;
	}

	/**
	 * gets child nodes.
	 *
	 * @return the child nodes
	 */
	public List<TreeNode> getChildNodes() {
		return childNodes;
	}

	/**
	 * sets the child nodes.
	 *
	 * @param 	childNodes
	 *          child nodes.
	 */
	public void setChildNodes(final List<TreeNode> childNodes) {
		this.childNodes = childNodes;
	}

	/**
	 * gets attribute key value pairs.
	 *
	 * @returns	attributes
	 */
	private Map<String, String> getAttributeKeyValues() {
		return attributeKeyValues;
	}

}
