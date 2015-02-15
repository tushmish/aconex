package com.aconex.gedcom.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.aconex.gedcom.BusinessException;
import com.aconex.gedcom.DataMiner;

/**
 * Test class for the methods in the class  {@link com.aconex.gedcom.DataMiner}.
 * 
 * @author tmishr
 */
public class DataMinerTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// -------- level -----------

	/**
	 * Test method for {@link com.aconex.gedcom.DataMiner#parseLevel()}. <p/>
	 * 
	 * Condition - level is the first character. <p/>
	 * expected	-  0 <p/>
	 * actual	- 0. <p/>
	 */
	@Test
	public void testParseLevel() {
		String data = "0 @I0001@ INDI";
		DataMiner dataMiner = new DataMiner(data);
		assertEquals("levels must be same.", 0, dataMiner.parseLevel());
	}

	/**
	 * Test method for {@link com.aconex.gedcom.DataMiner#parseLevel()}. <p/>
	 * 
	 * Condition - missing level <p/>
	 * expected	-  BusinessException <p/>
	 * actual	- BusinessException <p/>
	 */
	@Test
	public void testParseLevelWhenLevelDoesNotExist() {
		thrown.expect(BusinessException.class);
		String data = " @I0001@ INDI";
		DataMiner dataMiner = new DataMiner(data);
		dataMiner.parseLevel();
	}

	// -------- id -----------

	/**
	 * Test method for {@link com.aconex.gedcom.DataMiner#parseId()}. <p/>
	 * 
	 * Condition - id is present <p/>
	 * expected	-  @I0001@ <p/>
	 * actual	- @I0001@ <p/>
	 */
	@Test
	public void testParseId() {
		String data = "0 @I0001@ INDI";
		DataMiner dataMiner = new DataMiner(data);
		assertEquals("ids must be same.", "@I0001@", dataMiner.parseId());
	}

	/**
	 * Test method for {@link com.aconex.gedcom.DataMiner#parseId()}. <p/>
	 * 
	 * Condition - id does not exist <p/>
	 * expected	-  null <p/>
	 * actual	- null <p/>
	 */
	@Test
	public void testParseIdWhenIdDoesNotExist() {
		String data = "1 TITL Duke of Edinburgh";
		DataMiner dataMiner = new DataMiner(data);
		assertNull("id must be null.", dataMiner.parseId());
	}

	// -------- tag -----------

	/**
	 * Test method for {@link com.aconex.gedcom.DataMiner#parseTag()}. <p/>
	 * 
	 * Condition - tag length 1 <p/>
	 * expected	-  null <p/>
	 * actual	- null <p/>
	 */
	@Test
	public void testParseTagLength1Char() {
		String data = "1 T Duke of Edinburgh";
		DataMiner dataMiner = new DataMiner(data);
		assertNull("tag must be null.", dataMiner.parseTag());
	}

	/**
	 * Test method for {@link com.aconex.gedcom.DataMiner#parseTag()}. <p/>
	 * 
	 * Condition - tag length 2 <p/>
	 * expected	-  null <p/>
	 * actual	- null <p/>
	 */
	@Test
	public void testParseTagLength2Char() {
		String data = "1 TI Duke of Edinburgh";
		DataMiner dataMiner = new DataMiner(data);
		assertNull("tag must be null.", dataMiner.parseTag());
	}

	/**
	 * Test method for {@link com.aconex.gedcom.DataMiner#parseTag()}. <p/>
	 * 
	 * Condition - tag length 3 <p/>
	 * expected	-  TIT <p/>
	 * actual	- TIT <p/>
	 */
	@Test
	public void testParseTagLength3Char() {
		String data = "1 TIT Duke of Edinburgh";
		DataMiner dataMiner = new DataMiner(data);
		assertEquals("tags must be same.", "TIT", dataMiner.parseTag());
	}

	/**
	 * Test method for {@link com.aconex.gedcom.DataMiner#parseTag()}. <p/>
	 * 
	 * Condition - tag length 4 <p/>
	 * expected	-  TITL <p/>
	 * actual	- TITL <p/>
	 */
	@Test
	public void testParseTagLength4Char() {
		String data = "1 TITL Duke of Edinburgh";
		DataMiner dataMiner = new DataMiner(data);
		assertEquals("tags must be same.", "TITL", dataMiner.parseTag());
	}

	/**
	 * Test method for {@link com.aconex.gedcom.DataMiner#parseTag()}. <p/>
	 * 
	 * Condition - tag length 5 <p/>
	 * expected	-  null <p/>
	 * actual	- null <p/>
	 */
	@Test
	public void testParseTagLength5Char() {
		String data = "1 TITLE Duke of Edinburgh";
		DataMiner dataMiner = new DataMiner(data);
		assertNull("tag must be null.", dataMiner.parseTag());
	}

	// -------- tag value -----------

	/**
	 * Test method for {@link com.aconex.gedcom.DataMiner#parseTagValue()}. <p/>
	 * 
	 * Condition - tag length 2 chars, without tag<p/>
	 * expected	-  null <p/>
	 * actual	- null <p/>
	 */
	@Test
	public void testParseTagValueWhenTagLength2Chars() {
		thrown.expect(BusinessException.class);
		String data = "1 TI Duke of Edinburgh";
		DataMiner dataMiner = new DataMiner(data);
		dataMiner.parseTagValue("");
	}

	/**
	 * Test method for {@link com.aconex.gedcom.DataMiner#parseTagValue()}. <p/>
	 * 
	 * Condition - tag length 3 chars, without tag<p/>
	 * expected	-  "Duke of Edinburgh" <p/>
	 * actual	- "Duke of Edinburgh" <p/>
	 */
	@Test
	public void testParseTagValueWhenTagLength3Chars() {
		String data = "1 TIT Duke of Edinburgh";
		DataMiner dataMiner = new DataMiner(data);
		assertEquals("tags must be same.", "Duke of Edinburgh", dataMiner.parseTagValue(""));
	}

	/**
	 * Test method for {@link com.aconex.gedcom.DataMiner#parseTagValue(String))}. <p/>
	 * 
	 * Condition - tag length 3 chars with tag<p/>
	 * expected	-  "Duke of Edinburgh" <p/>
	 * actual	- "Duke of Edinburgh" <p/>
	 */
	@Test
	public void testParseTagValueWhenTagLength3CharsWithTag() {
		String data = "1 TIT Duke of Edinburgh";
		DataMiner dataMiner = new DataMiner(data);
		String tag = dataMiner.parseTag();
		assertEquals("tags must be same.", "Duke of Edinburgh", dataMiner.parseTagValue(tag));
	}

	/**
	 * Test method for {@link com.aconex.gedcom.DataMiner#parseTagValue()}. <p/>
	 * 
	 * Condition - tag length 4 chars, without tag<p/>
	 * expected	-  "Duke of Edinburgh" <p/>
	 * actual	- "Duke of Edinburgh" <p/>
	 */
	@Test
	public void testParseTagValueWhenTagLength4Chars() {
		String data = "1 TITL Duke of Edinburgh";
		DataMiner dataMiner = new DataMiner(data);
		assertEquals("tags must be same.", "Duke of Edinburgh", dataMiner.parseTagValue(""));
	}

	/**
	 * Test method for {@link com.aconex.gedcom.DataMiner#parseTagValue()}. <p/>
	 * 
	 * Condition - tag length 4 chars, with tag<p/>
	 * expected	-  "Duke of Edinburgh" <p/>
	 * actual	- "Duke of Edinburgh" <p/>
	 */
	@Test
	public void testParseTagValueWhenTagLength4CharsWithTag() {
		String data = "1 TITL Duke of Edinburgh";
		DataMiner dataMiner = new DataMiner(data);
		String tag = dataMiner.parseTag();
		assertEquals("tags must be same.", "Duke of Edinburgh", dataMiner.parseTagValue(tag));
	}

	/**
	 * Test method for {@link com.aconex.gedcom.DataMiner#parseTagValue()}. <p/>
	 * 
	 * Condition - tag length 5 chars, without tag<p/>
	 * expected	-  BusinessException <p/>
	 * actual	- BusinessException <p/>
	 */
	@Test
	public void testParseTagValueWhenTagLength5Chars() {
		thrown.expect(BusinessException.class);
		String data = "1 TITLE Duke of Edinburgh";
		DataMiner dataMiner = new DataMiner(data);
		dataMiner.parseTagValue("");
	}

	// -------- id value -----------

	@Test
	public void testParseIdValueWhenIdIsKnown() {
		String data = "0 @I0001@ INDI";
		DataMiner dataMiner = new DataMiner(data);
		String id = dataMiner.parseId();
		assertEquals("ids must be same.", "INDI", dataMiner.parseIdValue(id));
	}

	@Test
	public void testParseIdValueWhenIdIsNotKnown() {
		String data = "0 @I0001@ INDI";
		DataMiner dataMiner = new DataMiner(data);
		assertEquals("ids must be same.", "INDI", dataMiner.parseIdValue(""));
	}

	@Test
	public void testParseIdValueWhenIdDoesNotExist() {
		thrown.expect(BusinessException.class);
		String data = "0 INDI";
		DataMiner dataMiner = new DataMiner(data);
		dataMiner.parseIdValue("");
	}

}
