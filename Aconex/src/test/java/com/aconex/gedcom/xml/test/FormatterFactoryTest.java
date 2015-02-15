/**
 * 
 */
package com.aconex.gedcom.xml.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.aconex.gedcom.TreeNode;
import com.aconex.gedcom.xml.FormatterFactory;
import com.aconex.gedcom.xml.Level0ElementFormatter;
import com.aconex.gedcom.xml.Level1ElementFormatter;
import com.aconex.gedcom.xml.Level2ElementFormatter;
import com.aconex.gedcom.xml.XMLFormattable;

/**
 * Test class for the methods in the class  {@link com.aconex.gedcom.xml.FormatterFactory}.
 * 
 * @author tmishr
 */
public class FormatterFactoryTest {

	/**
	 * Test method for {@link com.aconex.gedcom.xml.FormatterFactory#getFormatter(com.aconex.gedcom.TreeNode)}. <p/>
	 * 
	 * Condition - data with level 0 <p/>
	 * expected	- an instance of Level0ElementFormatter {@link com.aconex.gedcom.xml.Level0ElementFormatter}. <p/>
	 * actual	- an instance of Level0ElementFormatter. <p/>
	 */
	@Test
	public final void testGetFormatterForLevel0() {
		TreeNode node = new TreeNode();
		node.setLevel(0);
		XMLFormattable formatter = FormatterFactory.getFormatter(node);
		assertTrue("must be same instance.", formatter instanceof Level0ElementFormatter);
	}

	/**
	 * Test method for {@link com.aconex.gedcom.xml.FormatterFactory#getFormatter(com.aconex.gedcom.TreeNode)}. <p/>
	 * 
	 * Condition - data with level 1 <p/>
	 * expected	- an instance of Level1ElementFormatter {@link com.aconex.gedcom.xml.Level1ElementFormatter}. <p/>
	 * actual	- an instance of Level1ElementFormatter. <p/>
	 */
	@Test
	public final void testGetFormatterForLevel1() {
		TreeNode node = new TreeNode();
		node.setLevel(1);
		XMLFormattable formatter = FormatterFactory.getFormatter(node);
		assertTrue("must be same instance.", formatter instanceof Level1ElementFormatter);
	}

	/**
	 * Test method for {@link com.aconex.gedcom.xml.FormatterFactory#getFormatter(com.aconex.gedcom.TreeNode)}. <p/>
	 * 
	 * Condition - data with level 2 <p/>
	 * expected	- an instance of Level2ElementFormatter {@link com.aconex.gedcom.xml.Level2ElementFormatter}. <p/>
	 * actual	- an instance of Level2ElementFormatter. <p/>
	 */
	@Test
	public final void testGetFormatterForLevel2() {
		TreeNode node = new TreeNode();
		node.setLevel(2);
		XMLFormattable formatter = FormatterFactory.getFormatter(node);
		assertTrue("must be same instance.", formatter instanceof Level2ElementFormatter);
	}

	/**
	 * Test method for {@link com.aconex.gedcom.xml.FormatterFactory#getFormatter(com.aconex.gedcom.TreeNode)}. <p/>
	 * 
	 * Condition - data with level 3 <p/>
	 * expected	- an instance of Level2ElementFormatter {@link com.aconex.gedcom.xml.Level2ElementFormatter}. <p/>
	 * actual	- an instance of Level2ElementFormatter. <p/>
	 */
	@Test
	public final void testGetFormatterForLevel3() {
		TreeNode node = new TreeNode();
		node.setLevel(3);
		XMLFormattable formatter = FormatterFactory.getFormatter(node);
		assertTrue("must be same instance.", formatter instanceof Level2ElementFormatter);
	}

}
