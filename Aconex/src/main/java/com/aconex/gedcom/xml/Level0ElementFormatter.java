package com.aconex.gedcom.xml;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.aconex.gedcom.TreeNode;
import com.aconex.gedcom.util.StringUtil;

/**
 * Class to format data at level 0. No validations apply on data as the raw data is already passed through validation at the time of reading and
 * transforming it into a TreeNode.
 *
 * For validation refer (@link com.aconex.gedcom.node.ParentNodeCreator#validate())
 *
 * @author tmishr
 */
public class Level0ElementFormatter implements XMLFormattable {

	// -------- class variables -----------
	/** parent node with id.. **/
	private final TreeNode node;

	// -------- constructor -----------

	/**
	 * Creates an instance.
	 *
	 * @param 	node
	 * 			node.
	 */
	public Level0ElementFormatter(final TreeNode node) {
		this.node = node;
	}

	// -------- methods -----------

	/**
	 * formats node data as below.
	 *
	 * <pre>
	 * {@code
	 * 	<indi id="@I1@">
	 * }
	 * </pre>
	 * @param	writer
	 * 			XML writer.
	 * @throws XMLStreamException
	 * 			in case of error.
	 */
	@Override
	public final void format(final XMLStreamWriter writer) throws XMLStreamException {
		writer.writeStartElement(getNode().getElementName().toLowerCase());
		final String value = getNode().getValue(TreeNode.ATTRIBUTE_ID);
		if (!StringUtil.isBlank(value)) {
			writer.writeAttribute(TreeNode.ATTRIBUTE_ID, getNode().getValue(TreeNode.ATTRIBUTE_ID));
		}
	}

	// -------- getters and setters -----------

	/**
	 * returns the parent node.
	 *
	 * @return the parent node with id.
	 */
	private TreeNode getNode() {
		return node;
	}

}
