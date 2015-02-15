package com.aconex.gedcom.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.aconex.gedcom.node.test.ChildNodeCreatorTest;
import com.aconex.gedcom.node.test.NodeFactoryTest;
import com.aconex.gedcom.node.test.ParentNodeCreatorTest;
import com.aconex.gedcom.node.test.SubChildNodeCreatorTest;
import com.aconex.gedcom.util.test.StringUtilTest;
import com.aconex.gedcom.xml.test.FormatterFactoryTest;
import com.aconex.gedcom.xml.test.Level0ElementFormatterTest;
import com.aconex.gedcom.xml.test.Level1ElementFormatterTest;
import com.aconex.gedcom.xml.test.Level2ElementFormatterTest;
import com.aconex.gedcom.xml.test.XMLGeneratorTest;

/**
 * Test suite for Gedcom Parser.
 * 
 * @author tmishr
 */
@RunWith(Suite.class)
@SuiteClasses({ DataMinerTest.class, StringUtilTest.class, NodeFactoryTest.class,
		ParentNodeCreatorTest.class, ChildNodeCreatorTest.class, SubChildNodeCreatorTest.class,
		XMLGeneratorTest.class, FormatterFactoryTest.class, Level0ElementFormatterTest.class,
		Level1ElementFormatterTest.class, Level2ElementFormatterTest.class, FileFormatTest.class,
		TreeNodeTest.class, ConfigurationTest.class, GedcomParserTest.class })
public class GedcomTestSuite {

}
