package com.aconex.gedcom;

import java.io.InputStream;

/**
 * Base interface to represent data in a given format. The formats may be XML, JSON, XPATH, HTML etc.
 * 
 * @author tmishr
 */
public interface Generatable {
	/**
	 * generates file at the specified path.
	 * 
	 * @param 	filePath
	 * 			path of the output file.
	 * @param	data
	 * 			data to be formatted.
	 */
	void create(final String filePath, final InputStream data);
}
