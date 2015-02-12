/**
 * 
 */
package com.aconex.gedcom.node;

import com.aconex.gedcom.TreeNode;

/**
 * creates a TreeNode representation of a single line of data as string from the file containing Genealogical data.
 *   
 * @param 	data
 * 			represents the single line of data from the Genealogical data file.
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
