/**
 *
 */
package com.aconex.gedcom.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.rules.ExpectedException;

import com.aconex.gedcom.BusinessException;
import com.aconex.gedcom.GedcomParser;

/**
 * Test class for the methods in the class  {@link com.aconex.gedcom.GedcomParser}.
 *
 * @author tmishr
 */
public class GedcomParserTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();

	/**
	 * Test method for {@link com.aconex.gedcom.GedcomParser#main(java.lang.String[])}.<p/>
	 *
	 * Condition - generates xml using default configuration. <p/>
	 * expected	-  xml file named 'gedcom.xml' <p/>
	 * actual	- generated file name 'gedcom.xml'. <p/>
	 */
	@Test
	public final void testMainWithoutConfigFile() {
		GedcomParser.main(new String[] {});
		final File file = new File("gedcom.xml");
		assertTrue("file must exist.", file.exists());
		file.delete();
	}

	/**
	 * Test method for {@link com.aconex.gedcom.GedcomParser#main(java.lang.String[])}.<p/>
	 *
	 * Condition - load external config file. <p/>
	 * expected	-  xml file as mentioned in the config file <p/>
	 * actual	- generated xml file as mentioned in the config file. <p/>
	 */
	@Test
	public final void testMainWithConfigFile() throws IOException {
		final String configFilepath = "src/test/resources/test-config.properties";
		final String outputFilepath = "src/test/resources/output-data.xml";
		GedcomParser.main(new String[] { configFilepath });
		final File file = new File(outputFilepath);
		file.createNewFile();
		assertTrue("file must exist.", file.exists());
		file.delete();
	}

	/**
	 * Test method for {@link com.aconex.gedcom.GedcomParser#main(java.lang.String[])}.
	 *
	 * Condition - execute with >1 arguments. <p/>
	 * expected	-  system failure <p/>
	 * actual	- system failure. <p/>
	 */
	@Test
	public final void testMainWith2Arguments() {
		exit.expectSystemExitWithStatus(-1);
		GedcomParser.main(new String[] { "arg1", "arg2" });
	}

	/**
	 * Test method for {@link com.aconex.gedcom.GedcomParser#main(java.lang.String[])}.
	 *
	 * Condition - argument is not a file path. its just a string. <p/>
	 * expected	-  BusinessException <p/>
	 * actual	- BusinessException. <p/>
	 */
	@Test
	public final void testMainWithArgumentThatIsNotFilePath() {
		thrown.expect(BusinessException.class);
		GedcomParser.main(new String[] { "arg1" });
	}

	/**
	 * Test method for {@link com.aconex.gedcom.GedcomParser#main(java.lang.String[])}.
	 *
	 * Condition - unsupported file format. <p/>
	 * expected	-  BusinessException <p/>
	 * actual	- BusinessException. <p/>
	 */
	@Test
	public final void testMainWithUnsupportedFileFormat() {
		thrown.expect(BusinessException.class);
		final String path = "/Users/tmishr/dev/workspace_luna/Aconex/src/test/resources/test-config-json.properties";
		GedcomParser.main(new String[] { path });
	}

}
