package com.aconex.gedcom.util.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.aconex.gedcom.util.StringUtil;

public class StringUtilTest {

	@Test
	public void testIsBlankWithEmptyString() {
		String str = "";
		assertTrue("must be an empty string.", StringUtil.isBlank(str));
	}

	@Test
	public void testIsBlankWithNull() {
		String str = null;
		assertTrue("must be null.", StringUtil.isBlank(str));
	}

	@Test
	public void testIsBlankWithNonEmptyString() {
		String str = "someValue";
		assertFalse("must not be blank.", StringUtil.isBlank(str));
	}

	@Test
	public void testIsWhitespace() {
		String str = "   ";
		assertTrue("must be true.", StringUtil.isWhitespace(str));
	}

	@Test
	public void testIsWhitespaceUsingNonEmptyString() {
		String str = "someValue";
		assertFalse("must be false.", StringUtil.isWhitespace(str));

		str = "  V ";
		assertFalse("must be false.", StringUtil.isWhitespace(str));
	}

}
