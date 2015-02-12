package com.aconex.gedcom.node;

import com.aconex.gedcom.DataMiner;

/**
 * Factory to create node from the raw data. Each line of the raw data represents a node. 
 * It is processed based on the level. Always returns a TreeNode. But the properties set vary 
 * depending upon the level.
 * 
 * @returns	TreeNode
 * 			node representing a line from the raw data.
 * 
 * @see	com.aconex.gedcom.node.ParentNodeCreator
 * @see	com.aconex.gedcom.node.ChildNodeCreator
 * @see	com.aconex.gedcom.node.SubChildNodeCreator
 * 
 * @author tmishr
 */
public final class NodeFactory {

	/**
	 * Creates a TreeNode from the data. The properties set vary depending upon the level.
	 * 
	 * @param 	data
	 * 			represents the single line of data from the Genealogical data file.
	 * @return	a TreeNode representing data.
	 */
	public static NodeCreatable getInstance(final String data) {
		DataMiner dataMiner = new DataMiner(data);
		switch (dataMiner.parseLevel()) {
		case 0:
			return new ParentNodeCreator(data);
		case 1:
			return new ChildNodeCreator(data);
		default:
			return new SubChildNodeCreator(data);
		}
	}
}
