package com.aconex.gedcom.node;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.aconex.gedcom.BusinessException;
import com.aconex.gedcom.DataMiner;
import com.aconex.gedcom.TreeNode;
import com.aconex.gedcom.util.StringUtil;

/**
 * Creates a node from the raw data of the format.
 *
 * <pre>
 * 	2 SURN Buck
 * </pre>
 *
 * @see com.aconex.gedcom.DataMiner
 * @author tmishr
 */
public final class SubChildNodeCreator implements NodeCreatable {

	// -------- class variables -----------
	/** raw data. **/
	private final DataMiner dataMiner;

	/** logger. **/
	private static final Logger LOGGER = Logger.getLogger(SubChildNodeCreator.class.getName());

	// -------- constructor -----------
	/**
	 * Validates data before creating an instance.
	 *
	 * @param	data
	 * 			raw data representing an element.
	 * @throws	BusinessException
	 * 			if the tag is blank.
	 */
	public SubChildNodeCreator(final String data) {
		dataMiner = new DataMiner(data);
		validate(data);
	}

	// -------- methods -----------
	/**
	 * creates a child TreeNode (level 2) representation of a single line of data as string
	 * from the file containing Genealogical data.The properties set are
	 * elementName as tag {@link com.aconex.gedcom.DataMiner#parseTag()}
	 * and attribute name "value" with tagValue as value. {@link com.aconex.gedcom.DataMiner#parseTagValue(String)()}.
	 *
	 * @return	tree node.
	 * @author 	tmishr
	 */
	@Override
	public TreeNode create() {
		final TreeNode node = new TreeNode();
		node.setLevel(getDataMiner().parseLevel());
		final String tag = getDataMiner().parseTag();
		node.setElementName(tag);
		node.setValue(getDataMiner().parseTagValue(tag));
		return node;
	}

	/**
	 * Validates raw data before creating a node.
	 *
	 * @param 	data
	 * 			the raw data.
	 * @throws	BusinessException
	 * 			if the tag is blank.
	 */
	private void validate(final String data) {
		final String tag = getDataMiner().parseTag();
		if (StringUtil.isBlank(tag)) {
			final String errorMessage = "malformed node. Tag is empty. Raw data:" + data;
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
