package com.aconex.gedcom.xml.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.InputStream;

import org.junit.Test;

import com.aconex.gedcom.xml.XMLGenerator;

/**
 * Test class for the methods in the class  {@link com.aconex.gedcom.xml.XMLGenerator}.
 * 
 * @author tmishr
 */
public class XMLGeneratorTest {

	/**
	 * Test method for {@link com.aconex.gedcom.xml.XMLGenerator#create(String, InputStream)}.<p/>
	 * 
	 * Condition - with output filepath and data as stream <p/>
	 * expected	-  creates an xml <p/>
	 * actual	- created an xml <p/>
	 */
	@Test
	public void testCreate() {
		InputStream inputStream = XMLGeneratorTest.class.getResourceAsStream("/data/gedcom.txt");
		XMLGenerator amlGenerator = new XMLGenerator();
		String path = "src/test/resources/gedcom.xml";
		amlGenerator.create(path, inputStream);
		File file = new File(path);
		assertTrue("file must exist.", file.exists());
		file.delete();
	}

}
