package com.aconex.gedcom.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.aconex.gedcom.Configuration;

/**
 * Test class for the methods in the class  {@link com.aconex.gedcom.Configuration}.
 * 
 * @author tmishr
 */
public class ConfigurationTest {

	/**
	 * Test method for {@link com.aconex.gedcom.Configuration#getInstance()}.
	 * 
	 * Condition - create an instance. <p/>
	 * expected	-  creates an instance <p/>
	 * actual	- created an instance. <p/>
	 */
	@Test
	public final void testGetInstance() {
		assertNotNull("instance must be created.", Configuration.getInstance());
	}

	/**
	 * Test method for {@link com.aconex.gedcom.Configuration#load(java.lang.String)}.
	 * 
	 * Condition - 	load default config. <p/>
	 * expected	-  	file format = xml, <p/>
	 * 				uses sample data file = gedcom.txt <p/>
	 * 				output file names must be same
	 * actual	- 	file format = xml, <p/>
	 * 				data stream is not null, default file loaded. <p/>
	 * 				output file names are same
	 */
	@Test
	public final void testLoad() {
		Configuration instance = Configuration.getInstance();
		instance.load("/Users/tmishr/dev/workspace_luna/Aconex/src/test/resources/test-config.properties");
		assertEquals("file format must be xml.", "XML", instance.getFileFormat());
		assertNotNull("data stream must not be null.", instance.getInputStream());
		assertEquals("file format must be xml.",
				"/Users/tmishr/dev/workspace_luna/Aconex/src/test/resources/output-data.xml",
				instance.getOutputFile());
	}

	/**
	 * Test method for {@link com.aconex.gedcom.Configuration#loadDefaultConfig()}.
	 * 
	 * Condition - 	load default config. <p/>
	 * expected	-  	file format = xml, <p/>
	 * 				uses sample data file = gedcom.txt <p/>
	 * actual	- 	file format = xml, <p/>
	 * 				data stream is not null, default file loaded. <p/>
	 */
	@Test
	public final void testLoadDefaultConfig() {
		Configuration instance = Configuration.getInstance();
		instance.loadDefaultConfig();
		assertEquals("file format must be xml.", "XML", instance.getFileFormat());
		assertNotNull("data stream must not be null.", instance.getInputStream());
	}

}
