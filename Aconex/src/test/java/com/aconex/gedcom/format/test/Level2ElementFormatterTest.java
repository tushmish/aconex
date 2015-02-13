package com.aconex.gedcom.format.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.aconex.gedcom.BusinessException;
import com.aconex.gedcom.TreeNode;
import com.aconex.gedcom.format.Level2ElementFormatter;

/**
 * The nodes used for formatting have already passed though validations.
 * {@link com.aconex.gedcom.node.SubChildNodeCreator#validate()} <p/>
 * 
 * for validations, please refer {@link com.aconex.gedcom.node.test.SubChildNodeCreatorTest} <p/>
 * @author tmishr
 */
public class Level2ElementFormatterTest {

	@AfterClass
	public static void tearDown() {
		new File("src/test/resources/level2data.xml").delete();
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * Condition: happy path, create XML element.
	 * 
	 * Expected	creates an XML element with tag name and value.
	 * Actual 	created an XML element with tag name and value.
	 */
	@Test
	public void testFormat() {
		TreeNode node = new TreeNode();
		node.setElementName("testElement");
		node.setValue("testValue");
		Level2ElementFormatter formatter = new Level2ElementFormatter(node);
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		try {
			// write xml
			XMLStreamWriter writer = outputFactory.createXMLStreamWriter(new FileWriter(
					"src/test/resources/level2data.xml"));
			writer.writeStartDocument();
			formatter.format(writer);
			writer.writeEndElement();
			writer.writeEndDocument();
			writer.flush();
			writer.close();

			// read xml
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			XMLStreamReader streamReader = inputFactory.createXMLStreamReader(new FileReader(
					"src/test/resources/level2data.xml"));
			while (streamReader.hasNext()) {
				streamReader.next();
				if (streamReader.getEventType() == XMLStreamReader.START_ELEMENT) {
					assertEquals("element names must be same.", "testelement",
							streamReader.getLocalName());
				}
				if (streamReader.getEventType() == XMLStreamReader.CHARACTERS) {
					assertEquals("element names must be same.", "testValue", streamReader.getText());
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
			fail("must not throw exception." + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			fail("must not throw exception." + e.getMessage());
		}
	}

	/**
	 * Condition: without element name.
	 * 
	 * Expected	must throw BusinessException
	 * Actual 	throws BusinessException
	 */
	@Test
	public void testFormatWithoutElementName() {
		thrown.expect(BusinessException.class);
		TreeNode node = new TreeNode();
		//node.setElementName("testEmement");
		node.setValue("testValue");
		Level2ElementFormatter formatter = new Level2ElementFormatter(node);
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		try {
			// write xml
			XMLStreamWriter writer = outputFactory.createXMLStreamWriter(new FileWriter(
					"src/test/resources/level2data.xml"));
			writer.writeStartDocument();
			formatter.format(writer);
			writer.writeEndElement();
			writer.writeEndDocument();
			writer.flush();
			writer.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
			fail("must not throw exception." + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			fail("must not throw exception." + e.getMessage());
		}
	}

	/**
	 * Condition: without element value.
	 * 
	 * Expected	creates xml element without value.
	 * Actual 	created xml element without value.
	 */
	@Test
	public void testFormatWithoutValue() {
		TreeNode node = new TreeNode();
		node.setElementName("testElement");
		//node.setValue("testValue");
		Level2ElementFormatter formatter = new Level2ElementFormatter(node);
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		try {
			// write xml
			XMLStreamWriter writer = outputFactory.createXMLStreamWriter(new FileWriter(
					"src/test/resources/level2data.xml"));
			writer.writeStartDocument();
			formatter.format(writer);
			writer.writeEndElement();
			writer.writeEndDocument();
			writer.flush();
			writer.close();

			// read xml
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			XMLStreamReader streamReader = inputFactory.createXMLStreamReader(new FileReader(
					"src/test/resources/level2data.xml"));
			while (streamReader.hasNext()) {
				streamReader.next();
				if (streamReader.getEventType() == XMLStreamReader.START_ELEMENT) {
					assertEquals("element names must be same.", "testelement",
							streamReader.getLocalName());
				}
				if (streamReader.getEventType() == XMLStreamReader.CHARACTERS) {
					assertNull("value must be null.", streamReader.getText());
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
			fail("must not throw exception." + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			fail("must not throw exception." + e.getMessage());
		}
	}

}
