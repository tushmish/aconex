package com.aconex.gedcom.xml;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.aconex.gedcom.BusinessException;
import com.aconex.gedcom.TreeNode;
import com.aconex.gedcom.util.StringUtil;

/**
 * Class to format data at level 2. No validations apply on data as the raw data is already passed through validation at the time of reading and
 * transforming it into a TreeNode.
 *
 * For validation refer (@link com.aconex.gedcom.node.SubChildNodeCreator#validate())
 *
 * @author tmishr
 */
public class Level2ElementFormatter implements XMLFormattable {

	// -------- class variables -----------
	/** sub child node. **/
	private final TreeNode node;

	/** logger. **/
	private static final Logger LOGGER = Logger.getLogger(Level2ElementFormatter.class.getName());

	// -------- constructor -----------

	/**
	 * Creates an instance.
	 *
	 * @param 	node
	 * 			node.
	 */
	public Level2ElementFormatter(final TreeNode node) {
		this.node = node;
	}

	// -------- methods -----------
	/**
	 * formats node data as below.
	 *
	 * <pre>
	 * {@code
	 * 	<surn>Buck</surn>
	 * }
	 * </pre>
	 *
	 * @param	writer
	 * 			XML writer.
	 * @throws XMLStreamException
	 * 			in case of error.
	 */
	@Override
	public final void format(final XMLStreamWriter writer) throws XMLStreamException {
		final String elementName = getNode().getElementName().toLowerCase();
		if (StringUtil.isBlank(elementName)) {
			final String errorMessage = "malformed node. Element name is missing. " + node;
			LOGGER.log(Level.FINER, errorMessage);
			throw new BusinessException(errorMessage);
		}
		writer.writeStartElement(elementName);
		writer.writeCharacters(getNode().getValue());
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
