package com.aconex.gedcom.node.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.aconex.gedcom.BusinessException;
import com.aconex.gedcom.TreeNode;
import com.aconex.gedcom.node.ParentNodeCreator;

/**
 * Test class for the methods in the class  {@link com.aconex.gedcom.node.ParentNodeCreator}.
 * 
 * @author tmishr
 */
public class ParentNodeCreatorTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * Test method for {@link com.aconex.gedcom.node.ParentNodeCreator#create()}.
	 * <p />
	 * Condition - with id <p/>
	 * expected	- level, element name and attribute value must be same <p/>
	 * actual	- level, element name and attribute value are same <p/>
	 */
	@Test
	public void testCreate() {
		String rawData = "0 @I0001@ INDI";
		ParentNodeCreator creator = new ParentNodeCreator(rawData);
		TreeNode node = creator.create();
		assertEquals("levels must be same.", 0, node.getLevel());
		assertEquals("levels must be same.", "INDI", node.getElementName());
		assertEquals("levels must be same.", "@I0001@", node.getValue(TreeNode.ATTRIBUTE_ID));
	}

	/**
	 * Test method for {@link com.aconex.gedcom.node.ParentNodeCreator#create()}.
	 * <p />
	 * Condition - without id <p/>
	 * expected	- level, element name and attribute value must be same <p/>
	 * actual	- level, element name and attribute value are same <p/>
	 */
	@Test
	public void testParentNodeCreatorWithoutId() {
		String rawData = "0  INDI";
		ParentNodeCreator creator = new ParentNodeCreator(rawData);
		TreeNode node = creator.create();
		assertEquals("levels must be same.", 0, node.getLevel());
		assertEquals("levels must be same.", "INDI", node.getElementName());
		assertNull("levels must be null.", node.getValue(TreeNode.ATTRIBUTE_ID));
	}

	/**
	 * Test method for {@link com.aconex.gedcom.node.ParentNodeCreator#create()}.
	 * <p />
	 * Condition - without id and tag <p/>
	 * expected	- must throw BusinessException <p/>
	 * actual	- throws BusinessException <p/>
	 */
	@Test
	public void testParentNodeCreatorWithoutIdAndTag() {
		thrown.expect(BusinessException.class);
		String data = "1 ";
		new ParentNodeCreator(data);
	}

}
