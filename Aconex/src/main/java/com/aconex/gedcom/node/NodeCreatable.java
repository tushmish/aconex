/**
 *
 */
package com.aconex.gedcom.node;

import com.aconex.gedcom.TreeNode;

/**
 * creates a TreeNode representation of a single line of data as string from the file containing Genealogical data.
 *
 * @author tmishr
 */
public interface NodeCreatable {

	/**
	 * Creates a TreeNode from the data. The properties set vary depending upon the level.
	 *
	 * @return	a TreeNode representing data.
	 */
	TreeNode create();
}
