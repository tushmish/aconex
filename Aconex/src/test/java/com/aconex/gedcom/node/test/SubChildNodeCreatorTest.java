package com.aconex.gedcom.node.test;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.aconex.gedcom.BusinessException;
import com.aconex.gedcom.TreeNode;
import com.aconex.gedcom.node.SubChildNodeCreator;

/**
 * Test class for the methods in the class  {@link com.aconex.gedcom.node.SubChildNodeCreator}.
 * 
 * @author tmishr
 */
public class SubChildNodeCreatorTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * Test method for {@link com.aconex.gedcom.node.SubChildNodeCreator#create()}.
	 * <p />
	 * Condition - with tag name and value <p/>
	 * expected	- level, element name and attribute value must be same <p/>
	 * actual	- level, element name and attribute value are same <p/>
	 */
	@Test
	public void testCreate() {
		String data = "2 SURN Buck";
		SubChildNodeCreator creator = new SubChildNodeCreator(data);
		TreeNode node = creator.create();
		assertEquals("levels must be same.", 2, node.getLevel());
		assertEquals("levels must be same.", "SURN", node.getElementName());
		assertEquals("levels must be same.", "Buck", node.getValue());
	}

	/**
	 * Test method for {@link com.aconex.gedcom.node.SubChildNodeCreator#create()}.
	 * <p />
	 * Condition - without tag <p/>
	 * expected	- must throw BusinessException <p/>
	 * actual	- throws BusinessException <p/>
	 */
	@Test
	public void testSubChildNodeCreatorWithoutTag() {
		thrown.expect(BusinessException.class);
		String data = "2 ";
		new SubChildNodeCreator(data);
	}

	/**
	 * Test method for {@link com.aconex.gedcom.node.SubChildNodeCreator#create()}.
	 * <p />
	 * Condition - without value <p/>
	 * expected	- level, element name and attribute value must be same <p/>
	 * actual	- level, element name and attribute value are same <p/>
	 */
	@Test
	public void testSubChildNodeCreatorWithoutValue() {
		String data = "2 SURN";
		SubChildNodeCreator creator = new SubChildNodeCreator(data);
		TreeNode node = creator.create();
		assertEquals("levels must be same.", 2, node.getLevel());
		assertEquals("levels must be same.", "SURN", node.getElementName());
		assertEquals("levels must be same.", "", node.getValue());
	}

}
