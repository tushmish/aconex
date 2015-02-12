package com.aconex.gedcom.format;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Renders a TreeNode data as an XML element in a specific format.  
 *  
 * @author tmishr
 */
public interface XMLFormattable {
	/**
	 * Formats data as an XML element.
	 *  
	 * @param 	writer
	 * 			xml writer.
	 * @throws 	XMLStreamException
	 * 			in case of failure.
	 */
	void format(final XMLStreamWriter writer) throws XMLStreamException;
}
