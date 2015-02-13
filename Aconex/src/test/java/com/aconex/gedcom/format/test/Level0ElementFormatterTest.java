/**
 * 
 */
package com.aconex.gedcom.format.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.junit.Test;

import com.aconex.gedcom.TreeNode;
import com.aconex.gedcom.format.Level0ElementFormatter;

/**
 * The nodes used for formatting have already passed though validations.  
 * {@link com.aconex.gedcom.node.ParentNodeCreator#validate()} <p/>
 * 
 * for validations,  refer {@link com.aconex.gedcom.node.test.ParentNodeCreatorTest}
 * @author tmishr
 */
public class Level0ElementFormatterTest {

	/**
	 * Condition: create XML element with attribute id/value. <p/>
	 * 
	 * Expected	creates an XML element with tag name, attribute id and its value. <p/>
	 * Actual 	created an XML element with tag name, attribute id and its value. <p/>
	 */
	@Test
	public void testFormat() {
		TreeNode node = new TreeNode();
		node.setElementName("testElement");
		node.addAttribute(TreeNode.ATTRIBUTE_ID, "@10001@");
		Level0ElementFormatter formatter = new Level0ElementFormatter(node);
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		try {
			// write xml
			XMLStreamWriter writer = outputFactory.createXMLStreamWriter(new FileWriter(
					"src/test/resources/level0data.xml"));
			writer.writeStartDocument();
			formatter.format(writer);
			writer.writeEndElement();
			writer.writeEndDocument();
			writer.flush();
			writer.close();

			// read xml
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			XMLStreamReader streamReader = inputFactory.createXMLStreamReader(new FileReader(
					"src/test/resources/level0data.xml"));
			while (streamReader.hasNext()) {
				streamReader.next();
				if (streamReader.getEventType() == XMLStreamReader.START_ELEMENT) {
					assertEquals("element names must be same.", "testelement",
							streamReader.getLocalName());
				}
				if (streamReader.getEventType() == XMLStreamReader.ATTRIBUTE) {
					assertEquals("attribute names must be same.", TreeNode.ATTRIBUTE_ID,
							streamReader.getAttributeName(0));
					assertEquals("attribute values must be same.", "@10001@",
							streamReader.getAttributeValue(0));
				}
			}
			new File("src/test/resources/level0data.xml").delete();
		} catch (XMLStreamException e) {
			e.printStackTrace();
			fail("must not throw exception." + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			fail("must not throw exception." + e.getMessage());
		}
	}

}
