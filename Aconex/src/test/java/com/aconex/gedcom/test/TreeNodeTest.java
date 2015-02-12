/**
 * 
 */
package com.aconex.gedcom.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.aconex.gedcom.TreeNode;

/**
 * Test class for the methods in the class  {@link com.aconex.gedcom.TreeNode}.
 * 
 * @author tmishr
 */
public class TreeNodeTest {

	// -------- hasChildNodes -----------

	/**
	 * Test method for {@link com.aconex.gedcom.TreeNode#hasChildNodes()}. <p/>
	 * 
	 * Condition - has child nodes. <p/>
	 * expected	-  true <p/>
	 * actual	- true. <p/>
	 */
	@Test
	public final void testHasChildNodes() {
		TreeNode parentNode = new TreeNode();
		parentNode.addChild(new TreeNode());
		parentNode.addChild(new TreeNode());
		assertTrue("child nodes must exist.", parentNode.hasChildNodes());
	}

	/**
	 * Test method for {@link com.aconex.gedcom.TreeNode#hasChildNodes()}. <p/>
	 * 
	 * Condition - has child nodes. <p/>
	 * expected	-  false <p/>
	 * actual	- false. <p/>
	 */
	@Test
	public final void testHasChildNodesWhenNoChildNodes() {
		TreeNode parentNode = new TreeNode();
		assertFalse("child nodes must exist.", parentNode.hasChildNodes());
	}

	// -------- addChild -----------

	/**
	 * Test method for {@link com.aconex.gedcom.TreeNode#addChild(com.aconex.gedcom.TreeNode)}.
	 * 
	 * Condition - add 1 child node. <p/>
	 * expected	-  size = 1 <p/>
	 * actual	- size  = 1. <p/>
	 */
	@Test
	public final void testAddChild() {
		TreeNode parentNode = new TreeNode();
		parentNode.addChild(new TreeNode());
		assertEquals("size must be 1.", 1, parentNode.getChildNodes().size());
	}

	// -------- addAttribute / getValue -----------

	/**
	 * Test method for {@link com.aconex.gedcom.TreeNode#addAttribute(java.lang.String, java.lang.String)}.
	 * 
	 * Condition - add attribute key value. <p/>
	 * expected	-  value must get added,  <p/>
	 * actual	- value gets added. <p/>
	 */
	@Test
	public final void testAddAttribute() {
		TreeNode node = new TreeNode();
		node.addAttribute("key", "value");
		assertEquals("value must be same.", "value", node.getValue("key"));
	}

	/**
	 * Test method for {@link com.aconex.gedcom.TreeNode#addAttribute(java.lang.String, java.lang.String)}.
	 * 
	 * Condition - add attribute with empty value. <p/>
	 * expected	-  value must get added, value must be empty  <p/>
	 * actual	- value is be empty. <p/>
	 */
	@Test
	public final void testAddAttributeWithEmptyValue() {
		TreeNode node = new TreeNode();
		node.addAttribute("key", "");
		assertEquals("value must be same.", "", node.getValue("key"));
	}

}
