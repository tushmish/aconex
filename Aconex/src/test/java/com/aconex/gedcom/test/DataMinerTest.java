package com.aconex.gedcom.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.aconex.gedcom.BusinessException;
import com.aconex.gedcom.DataMiner;

public class DataMinerTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// -------- level -----------

	@Test
	public void testParseLevel() {
		String data = "0 @I0001@ INDI";
		DataMiner dataMiner = new DataMiner(data);
		assertEquals("levels must be same.", 0, dataMiner.parseLevel());
	}

	@Test
	public void testParseLevelWhenLevelDoesNotExist() {
		thrown.expect(BusinessException.class);
		String data = " @I0001@ INDI";
		DataMiner dataMiner = new DataMiner(data);
		dataMiner.parseLevel();
	}

	// -------- id -----------

	@Test
	public void testParseId() {
		String data = "0 @I0001@ INDI";
		DataMiner dataMiner = new DataMiner(data);
		assertEquals("ids must be same.", "@I0001@", dataMiner.parseId());
	}

	@Test
	public void testParseIdWhenIdDoesNotExist() {
		String data = "1 TITL Duke of Edinburgh";
		DataMiner dataMiner = new DataMiner(data);
		assertNull("id must be null.", dataMiner.parseId());
	}

	// -------- tag -----------

	@Test
	public void testParseTagLength1Char() {
		String data = "1 T Duke of Edinburgh";
		DataMiner dataMiner = new DataMiner(data);
		assertNull("tag must be null.", dataMiner.parseTag());
	}

	@Test
	public void testParseTagLength2Char() {
		String data = "1 TI Duke of Edinburgh";
		DataMiner dataMiner = new DataMiner(data);
		assertNull("tag must be null.", dataMiner.parseTag());
	}

	@Test
	public void testParseTagLength3Char() {
		String data = "1 TIT Duke of Edinburgh";
		DataMiner dataMiner = new DataMiner(data);
		assertEquals("tags must be same.", "TIT", dataMiner.parseTag());
	}

	@Test
	public void testParseTagLength4Char() {
		String data = "1 TITL Duke of Edinburgh";
		DataMiner dataMiner = new DataMiner(data);
		assertEquals("tags must be same.", "TITL", dataMiner.parseTag());
	}

	@Test
	public void testParseTagLength5Char() {
		String data = "1 TITLE Duke of Edinburgh";
		DataMiner dataMiner = new DataMiner(data);
		assertNull("tag must be null.", dataMiner.parseTag());
	}

	// -------- tag value -----------

	@Test
	public void testParseTagValueWhenTagLength2Chars() {
		thrown.expect(BusinessException.class);
		String data = "1 TI Duke of Edinburgh";
		DataMiner dataMiner = new DataMiner(data);
		dataMiner.parseTagValue("");
	}

	@Test
	public void testParseTagValueWhenTagLength3Chars() {
		String data = "1 TIT Duke of Edinburgh";
		DataMiner dataMiner = new DataMiner(data);
		assertEquals("tags must be same.", "Duke of Edinburgh", dataMiner.parseTagValue(""));
	}

	@Test
	public void testParseTagValueWhenTagLength3CharsWithTag() {
		String data = "1 TIT Duke of Edinburgh";
		DataMiner dataMiner = new DataMiner(data);
		String tag = dataMiner.parseTag();
		assertEquals("tags must be same.", "Duke of Edinburgh", dataMiner.parseTagValue(tag));
	}

	@Test
	public void testParseTagValueWhenTagLength4Chars() {
		String data = "1 TITL Duke of Edinburgh";
		DataMiner dataMiner = new DataMiner(data);
		assertEquals("tags must be same.", "Duke of Edinburgh", dataMiner.parseTagValue(""));
	}

	@Test
	public void testParseTagValueWhenTagLength4CharsWithTag() {
		String data = "1 TITL Duke of Edinburgh";
		DataMiner dataMiner = new DataMiner(data);
		String tag = dataMiner.parseTag();
		assertEquals("tags must be same.", "Duke of Edinburgh", dataMiner.parseTagValue(tag));
	}

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
