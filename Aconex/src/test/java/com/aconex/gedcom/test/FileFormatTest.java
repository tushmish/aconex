package com.aconex.gedcom.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.aconex.gedcom.FileFormat;

/**
 * Test class for the methods in the class  {@link com.aconex.gedcom.node.FileFormat}.
 * 
 * @author tmishr
 */
public class FileFormatTest {

	/**
	 * Test method for {@link com.aconex.gedcom.FileFormat#isValidValue(java.lang.String)}.
	 * 
	 * Condition - valid format <p/>
	 * expected	-  true <p/>
	 * actual	- true. <p/>
	 */
	@Test
	public final void testIsValidValue() {
		assertTrue("formats must be valid.", FileFormat.isValidValue("xml"));
	}

	/**
	 * Test method for {@link com.aconex.gedcom.FileFormat#isValidValue(java.lang.String)}.
	 * 
	 * Condition - valid format, case insensitive<p/>
	 * expected	-  true <p/>
	 * actual	- true. <p/>
	 */
	@Test
	public final void testIsValidValueCaseInsensitive() {
		assertTrue("formats must be valid.", FileFormat.isValidValue("XML"));
	}

	/**
	 * Test method for {@link com.aconex.gedcom.FileFormat#isValidValue(java.lang.String)}.
	 * 
	 * Condition - invalid value e<p/>
	 * expected	-  false <p/>
	 * actual	- false. <p/>
	 */
	@Test
	public final void testIsValidValueUsingInvaliValue() {
		assertFalse("formats must be valid.", FileFormat.isValidValue("invalidValue"));
	}

	/**
	 * Test method for {@link com.aconex.gedcom.FileFormat#isValidValue(java.lang.String)}.
	 * 
	 * Condition - empty value<p/>
	 * expected	-  false <p/>
	 * actual	- false. <p/>
	 */
	@Test
	public final void testIsValidValueEmptyValue() {
		assertFalse("formats must be valid.", FileFormat.isValidValue(""));
	}

	/**
	 * Test method for {@link com.aconex.gedcom.FileFormat#isValidValue(java.lang.String)}.
	 * 
	 * Condition - null value<p/>
	 * expected	-  false <p/>
	 * actual	- false. <p/>
	 */
	@Test
	public final void testIsValidValueWhenNull() {
		assertFalse("formats must be valid.", FileFormat.isValidValue(null));
	}
}
