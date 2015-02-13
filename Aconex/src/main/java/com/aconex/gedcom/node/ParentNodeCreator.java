package com.aconex.gedcom.node;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.aconex.gedcom.BusinessException;
import com.aconex.gedcom.DataMiner;
import com.aconex.gedcom.TreeNode;
import com.aconex.gedcom.util.StringUtil;

/**
 * Creates a node from the raw data of the format.
 * Sample data.
 * <pre>
 * 	0 @I1@ INDI
 * </pre>
 *
 * @see com.aconex.gedcom.DataMiner
 * @author tmishr
 */
public final class ParentNodeCreator implements NodeCreatable {

	// -------- class variables -----------
	/** raw data miner. **/
	private final DataMiner dataMiner;

	/** logger. **/
	private static final Logger LOGGER = Logger.getLogger(ParentNodeCreator.class.getName());

	// -------- constructor -----------
	/**
	 * Validates data before creating an instance.
	 *
	 * @param	data
	 * 			raw data representing an element.
	 * @throws	BusinessException
	 * 			if the tag and id are blank.
	 */
	public ParentNodeCreator(final String data) {
		dataMiner = new DataMiner(data);
		validate(data);
	}

	// -------- methods -----------
	/**
	 * creates a parent TreeNode (level 0) representation of a single line of data as string
	 * from the file containing Genealogical data.The properties set are
	 * attribute named "id" with value idValue {@link com.aconex.gedcom.DataMiner#parseId()}
	 * and elementName as idValue {@link com.aconex.gedcom.DataMiner#parseIdValue(String)()}.
	 *
	 * @return	a parent node
	 *
	 * @author tmishr
	 */
	@Override
	public TreeNode create() {
		final TreeNode node = new TreeNode();
		node.setLevel(getDataMiner().parseLevel()); // parent node
		final String id = getDataMiner().parseId();
		String value = "";
		if (!StringUtil.isBlank(id)) {
			node.addAttribute(TreeNode.ATTRIBUTE_ID, id);
			value = getDataMiner().parseIdValue(id);
			node.setElementName(value); // If an ID is given, the DATA is the type of the subtree that is identified.
		} else {
			final String tag = getDataMiner().parseTag();
			value = getDataMiner().parseTagValue(tag);
			node.setElementName(tag);
		}
		return node;
	}

	/**
	 * Validates raw data before creating a parent node.
	 *
	 * @param 	data
	 * 			the raw data.
	 * @throws	BusinessException
	 * 			if the tag and id are blank.
	 */
	private void validate(final String data) {
		final String id = getDataMiner().parseId();
		final String tag = getDataMiner().parseTag();
		if (StringUtil.isBlank(id) && StringUtil.isBlank(tag)) {
			final String errorMessage = "malformed node. both id and tag name are empty. Raw data:"
					+ data;
			LOGGER.log(Level.FINER, errorMessage);
			throw new BusinessException(errorMessage);
		}
	}

	// -------- getters and setters -----------

	/**
	 * returns data miner.
	 *
	 * @return data miner.
	 */
	private DataMiner getDataMiner() {
		return dataMiner;
	}

}
