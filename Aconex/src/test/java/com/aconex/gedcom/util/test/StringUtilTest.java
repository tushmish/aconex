package com.aconex.gedcom.util.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.aconex.gedcom.util.StringUtil;

/**
 * Test class for the methods in the class  {@link com.aconex.gedcom.util.StringUtil}.
 * 
 * @author tmishr
 */
public class StringUtilTest {

	/**
	 * Test method for {@link com.aconex.gedcom.util.StringUtil#isBlank(CharSequence))}. <p/>
	 * 
	 * Condition - empty string. <p/>
	 * expected	-  true <p/>
	 * actual	- true. <p/>
	 */
	@Test
	public void testIsBlankWithEmptyString() {
		String str = "";
		assertTrue("must be an empty string.", StringUtil.isBlank(str));
	}

	/**
	 * Test method for {@link com.aconex.gedcom.util.StringUtil#isBlank(CharSequence))}. <p/>
	 * 
	 * Condition - empty string. <p/>
	 * expected	-  true <p/>
	 * actual	- true. <p/>
	 */
	@Test
	public void testIsBlankWithNull() {
		String str = null;
		assertTrue("must be null.", StringUtil.isBlank(str));
	}

	/**
	 * Test method for {@link com.aconex.gedcom.util.StringUtil#isBlank(CharSequence))}. <p/>
	 * 
	 * Condition - non empty string. <p/>
	 * expected	-  false <p/>
	 * actual	- false. <p/>
	 */
	@Test
	public void testIsBlankWithNonEmptyString() {
		String str = "someValue";
		assertFalse("must not be blank.", StringUtil.isBlank(str));
	}

	/**
	 * Test method for {@link com.aconex.gedcom.util.StringUtil#isWhitespace(CharSequence))}. <p/>
	 * 
	 * Condition - whitespaces. <p/>
	 * expected	-  true <p/>
	 * actual	- true. <p/>
	 */
	@Test
	public void testIsWhitespace() {
		String str = "   ";
		assertTrue("must be true.", StringUtil.isWhitespace(str));
	}

	/**
	 * Test method for {@link com.aconex.gedcom.util.StringUtil#isWhitespace(CharSequence))}. <p/>
	 * 
	 * Condition - no whitespace, whitespace with char. <p/>
	 * expected	-  false <p/>
	 * actual	- false. <p/>
	 */
	@Test
	public void testIsWhitespaceUsingNonEmptyString() {
		String str = "someValue";
		assertFalse("must be false.", StringUtil.isWhitespace(str));
	}

	/**
	 * Test method for {@link com.aconex.gedcom.util.StringUtil#isWhitespace(CharSequence))}. <p/>
	 * 
	 * Condition - whitespace with char. <p/>
	 * expected	-  false <p/>
	 * actual	- false. <p/>
	 */
	@Test
	public void testIsWhitespaceMixedString() {
		String str = "  V ";
		assertFalse("must be false.", StringUtil.isWhitespace(str));
	}

}
