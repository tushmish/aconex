/**
 * 
 */
package com.aconex.gedcom.node.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.aconex.gedcom.node.ChildNodeCreator;
import com.aconex.gedcom.node.NodeCreatable;
import com.aconex.gedcom.node.NodeFactory;
import com.aconex.gedcom.node.ParentNodeCreator;
import com.aconex.gedcom.node.SubChildNodeCreator;

/**
 * Test class for the methods in the class  {@link com.aconex.gedcom.node.NodeFactory}.
 * 
 * @author tmishr
 */
public class NodeFactoryTest {

	/**
	 * Test method for {@link com.aconex.gedcom.node.NodeFactory#getInstance(java.lang.String)}. <p/>
	 * 
	 * Condition - data with level 0 <p/>
	 * expected	- an instance of ParentNodeCreator {@link com.aconex.gedcom.node.ParentNodeCreator}. <p/>
	 * actual	- an instance of ParentNodeCreator. <p/>
	 */
	@Test
	public void testGetInstanceOfLevel0() {
		String data = "0 @I0001@ INDI";
		NodeCreatable creatable = NodeFactory.getInstance(data);
		assertTrue("must be same instance.", creatable instanceof ParentNodeCreator);
	}

	/**
	 * Test method for {@link com.aconex.gedcom.node.NodeFactory#getInstance(java.lang.String)}. <p/>
	 * 
	 * Condition - data with level 1 <p/>
	 * expected	- an instance of ChildNodeCreator {@link com.aconex.gedcom.node.ChildNodeCreator}. <p/>
	 * actual	- an instance of ChildNodeCreator. <p/>
	 */
	@Test
	public void testGetInstanceOfLevel1() {
		String data = "1 @I0001@ INDI";
		NodeCreatable creatable = NodeFactory.getInstance(data);
		assertTrue("must be same instance.", creatable instanceof ChildNodeCreator);
	}

	/**
	 * Test method for {@link com.aconex.gedcom.node.NodeFactory#getInstance(java.lang.String)}. <p/>
	 * 
	 * Condition - data with level 2 <p/>
	 * expected	- an instance of SubChildNodeCreator {@link com.aconex.gedcom.node.SubChildNodeCreator}. <p/>
	 * actual	- an instance of SubChildNodeCreator. <p/>
	 */
	@Test
	public void testGetInstanceOfLevel2() {
		String data = "2 @I0001@ INDI";
		NodeCreatable creatable = NodeFactory.getInstance(data);
		assertTrue("must be same instance.", creatable instanceof SubChildNodeCreator);
	}

	/**
	 * Test method for {@link com.aconex.gedcom.node.NodeFactory#getInstance(java.lang.String)}. <p/>
	 * 
	 * Condition - data with level 3 <p/>
	 * expected	- an instance of SubChildNodeCreator {@link com.aconex.gedcom.node.SubChildNodeCreator}. <p/>
	 * actual	- an instance of SubChildNodeCreator. <p/>
	 */
	@Test
	public void testGetInstanceOfLevel3() {
		String data = "3 @I0001@ INDI";
		NodeCreatable creatable = NodeFactory.getInstance(data);
		assertTrue("must be same instance.", creatable instanceof SubChildNodeCreator);
	}
}
