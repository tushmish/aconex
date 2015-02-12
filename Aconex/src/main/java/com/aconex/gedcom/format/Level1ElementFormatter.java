package com.aconex.gedcom.format;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.aconex.gedcom.TreeNode;

/**
 * Class to format data at level 1. No validations apply on data as the raw data is already passed through validation at the time of reading and 
 * transforming it into a TreeNode.
 * 
 * For validation refer (@link com.aconex.gedcom.node.ChildNodeCreator#validate())
 *  
 * @author tmishr
 */
public class Level1ElementFormatter implements XMLFormattable {

	// -------- class variables -----------
	/** child node. **/
	private TreeNode node;

	// -------- constructor -----------

	/**
	 * Creates an instance.
	 * 
	 * @param 	node
	 * 			node.
	 */
	public Level1ElementFormatter(final TreeNode node) {
		this.node = node;
	}

	/**
	 * formats node data as below.
	 * 
	 * <pre>
	 * {@code
	 * 	<name value="Jamis Gordon /Buck/">
	 * }
	 * </pre>
	 * 
	 * @return 	formatted parent node.
	 * @throws 	XMLStreamException
	 * 			in case of error. 
	 */
	@Override
	public void format(XMLStreamWriter writer) throws XMLStreamException {
		writer.writeStartElement(getNode().getElementName());
		writer.writeAttribute(TreeNode.ATTRIBUTE_VALUE, getNode()
				.getValue(TreeNode.ATTRIBUTE_VALUE));
	}

	// -------- getters and setters -----------

	/**
	 * returns the child node.
	 * 
	 * @return the child node.
	 */
	private TreeNode getNode() {
		return node;
	}

}
