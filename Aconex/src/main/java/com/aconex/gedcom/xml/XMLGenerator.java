package com.aconex.gedcom.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.aconex.gedcom.BusinessException;
import com.aconex.gedcom.DataMiner;
import com.aconex.gedcom.Generatable;
import com.aconex.gedcom.TreeNode;
import com.aconex.gedcom.node.NodeFactory;
import com.aconex.gedcom.util.StringUtil;

/**
 * Class to generate XML file. The data is represented as XML.
 *
 * @author tmishr
 */
public class XMLGenerator implements Generatable {

	// -------- class variables -----------

	/** default output file containing Genealogical data. **/
	public static final String DEFAULT_OUTPUT_FILE = "gedcom.xml";

	/** root element. **/
	public static final String DOCUMENT_ROOT = "gedcom";

	/** logger. **/
	private static final Logger LOGGER = Logger.getLogger(XMLGenerator.class.getName());

	// -------- methods -----------

	/**
	 * generates XML file at the specified path.The data is read and written in chunks. A single chunk refers to block
	 * of data as shown below.
	 * <pre>
	 * 	0 @I0001@ INDI
	 *	1 NAME Elizabeth Alexandra Mary /Windsor/
	 *	2 DATE 21 Apr 1926
	 *	2 PLAC 17 Bruton Street, London, W1
	 *	1 FAMC @F0003@
	 * </pre>
	 *
	 * The row with Level 0 i.e. the 1st row is marked as parent node. All subsequent nodes at
	 * the same level are marked as its children. In general, level n is the parent of all nodes at level n + 1.
	 *
	 * This TreeNode acts as a parent node which is further written as XML.{@link com.aconex.gedcom.xml.XMLGenerator#writeXML}
	 *
	 * @param 	filePath
	 * 			path of the output file.
	 * @param	data
	 * 			data to be formatted.
	 * @throws	BusinessException
	 * 			in case of stream error.
	 */
	@Override
	public void create(String filePath, final InputStream data) {
		LOGGER.log(Level.INFO, "Generating XML...");
		final Map<Integer, TreeNode> mostRecentNodeMap = new HashMap<Integer, TreeNode>();
		if (StringUtil.isBlank(filePath)) {
			filePath = DEFAULT_OUTPUT_FILE;
		}

		try {
			final XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
			final File file = new File(filePath);
			file.createNewFile();
			final XMLStreamWriter writer = outputFactory
					.createXMLStreamWriter(new FileWriter(file));
			writer.writeStartDocument();
			writer.writeStartElement(DOCUMENT_ROOT);
			final BufferedReader reader = new BufferedReader(new InputStreamReader(data));
			String line = "";
			while ((line = reader.readLine()) != null) {
				if (StringUtil.isBlank(line)) {
					continue; // ignore blank lines.
				}
				if ((mostRecentNodeMap.size() > 0) && (new DataMiner(line).parseLevel() == 0)) {
					writeXML(filePath, mostRecentNodeMap.get(0), writer);
					mostRecentNodeMap.clear();
				}
				final TreeNode node = NodeFactory.getInstance(line).create();
				final int level = node.getLevel();
				final int parentNodeLevel = level - 1;
				if (parentNodeLevel >= 0 && mostRecentNodeMap.containsKey(parentNodeLevel)) {
					final TreeNode parentNode = mostRecentNodeMap.get(parentNodeLevel);
					parentNode.addChild(node);
				}
				mostRecentNodeMap.put(level, node);
			}

			// for the last node
			writeXML(filePath, mostRecentNodeMap.get(0), writer);
			writer.writeEndElement();
			writer.writeEndDocument();
			writer.flush();
			writer.close();
			LOGGER.log(Level.INFO, "XML Generated.");
		} catch (XMLStreamException | IOException ex) {
			LOGGER.log(Level.FINER, ex.getMessage(), ex);
			throw new BusinessException(ex.getMessage(), ex);
		}

	}

	/**
	 * Creates an XML elemnet.If the node has child items, creates XML element recursively for each child.
	 *
	 * @param 	filePath
	 * 			file on which the xml data needs to be created.
	 * @param 	node
	 * 			the parent node.
	 * @param 	writer
	 * 			XML writer.
	 * @throws	BusinessException
	 * 			in case of stream error.
	 */
	private void writeXML(final String filePath, final TreeNode node, final XMLStreamWriter writer) {
		try {
			FormatterFactory.getFormatter(node).format(writer);
			final List<TreeNode> childNodes = node.getChildNodes();
			for (final TreeNode child : childNodes) {
				if (node.hasChildNodes()) {
					writeXML(filePath, child, writer);
				}
			}
			writer.writeEndElement();
		} catch (final XMLStreamException ex) {
			LOGGER.log(Level.FINER, ex.getMessage(), ex);
			throw new BusinessException(ex.getMessage(), ex);
		}
	}

}
