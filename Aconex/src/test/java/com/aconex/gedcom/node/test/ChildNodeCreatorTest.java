package com.aconex.gedcom.node.test;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.aconex.gedcom.BusinessException;
import com.aconex.gedcom.TreeNode;
import com.aconex.gedcom.node.ChildNodeCreator;

/**
 * Test class for the methods in the class  {@link com.aconex.gedcom.node.ChildNodeCreator}.
 * 
 * @author tmishr
 */
public class ChildNodeCreatorTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * Test method for {@link com.aconex.gedcom.node.ChildNodeCreator#create()}.
	 * <p />
	 * Condition - Tag Name 3 Chars <p/>
	 * expected	- level, element name and attribute value must be same <p/>
	 * actual	- level, element name and attribute value are same <p/>
	 */
	@Test
	public void testCreateWhenTagNameLengthIs3Chars() {
		String data = "1 TIT Duke of Edinburgh";
		ChildNodeCreator creator = new ChildNodeCreator(data);
		TreeNode node = creator.create();
		assertEquals("levels must be same.", 1, node.getLevel());
		assertEquals("levels must be same.", "TIT", node.getElementName());
		assertEquals("levels must be same.", "Duke of Edinburgh",
				node.getValue(TreeNode.ATTRIBUTE_VALUE));
	}

	/**
	 * Test method for {@link com.aconex.gedcom.node.ChildNodeCreator#create()}.
	 * <p />
	 * Condition - Tag Name 4 Chars <p/>
	 * expected	- level, element name and attribute value must be same <p/>
	 * actual	- level, element name and attribute value are same <p/>
	 */
	@Test
	public void testCreateWhenTagNameLengthIs4Chars() {
		String data = "1 TITL Duke of Edinburgh";
		ChildNodeCreator creator = new ChildNodeCreator(data);
		TreeNode node = creator.create();
		assertEquals("levels must be same.", 1, node.getLevel());
		assertEquals("levels must be same.", "TITL", node.getElementName());
		assertEquals("levels must be same.", "Duke of Edinburgh",
				node.getValue(TreeNode.ATTRIBUTE_VALUE));
	}

	/**
	 * Test method for {@link com.aconex.gedcom.node.ChildNodeCreator#create()}.
	 * <p />
	 * Condition - Tag Name 2 Chars <p/>
	 * expected	- must throw BusinessException <p/>
	 * actual	- throws BusinessException <p/>
	 */
	@Test
	public void testChildNodeCreatorWhenTagNameLengthIs2Chars() {
		thrown.expect(BusinessException.class);
		String data = "1 TI Duke of Edinburgh";
		new ChildNodeCreator(data);
	}

	/**
	 * Test method for {@link com.aconex.gedcom.node.ChildNodeCreator#create()}.
	 * <p />
	 * Condition - Tag Name 5 Chars <p/>
	 * expected	- must throw BusinessException <p/>
	 * actual	- throws BusinessException <p/>
	 */
	@Test
	public void testChildNodeCreatorWhenTagNameLengthIs5Chars() {
		thrown.expect(BusinessException.class);
		String data = "1 TITLE Duke of Edinburgh";
		new ChildNodeCreator(data);
	}

}
