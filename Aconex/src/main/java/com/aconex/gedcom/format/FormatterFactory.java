package com.aconex.gedcom.format;

import com.aconex.gedcom.TreeNode;

/**
 * Factory to create an XML formatter.
 * 
 * @author tmishr
 */
public class FormatterFactory {

	/**
	 * Creates a formatter based on the node level.
	 * 
	 * @param 	node
	 * 			data as node.
	 * @return	a xml formatter.
	 */
	public static XMLFormattable getFormatter(final TreeNode node) {
		switch (node.getLevel()) {
		case 0:
			return new Level0ElementFormatter(node);
		case 1:
			return new Level1ElementFormatter(node);
		default:
			return new Level2ElementFormatter(node);
		}
	}

}
