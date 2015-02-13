package com.aconex.gedcom.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aconex.gedcom.BusinessException;

/**
 * Utility class for File I/O.
 *
 * @author tmishr
 */
public final class FileUtil {

	// -------- class variables -----------

	/** logger. **/
	private static final Logger LOGGER = Logger.getLogger(FileUtil.class.getName());

	// -------- constructor -----------
	/**
	 * Prevent instantiation as it is a utility class.
	 */
	private FileUtil() {
	}

	// -------- methods -----------

	/**
	 * Opens a file stream to read data.
	 *
	 * @param 	filePath
	 * 			file to be read.
	 * @return	file stream
	 * @throws	BusinessException
	 * 			if file path is incorrect or file is not found.
	 */
	public static InputStream readFile(final String filePath) {
		try {
			return new FileInputStream(filePath);
		} catch (final FileNotFoundException e) {
			LOGGER.log(Level.FINER, e.getMessage(), e);
			throw new BusinessException(e.getMessage(), e);
		}
	}

	/**
	 * closes file stream.
	 *
	 * @param 	stream
	 * 			file stream.
	 * @throws	BusinessException
	 * 			in case of i/o operation failure.
	 */
	public static void close(final InputStream stream) {
		try {
			stream.close();
		} catch (final IOException e) {
			LOGGER.log(Level.FINER, e.getMessage(), e);
			throw new BusinessException(e.getMessage(), e);
		}
	}
}
