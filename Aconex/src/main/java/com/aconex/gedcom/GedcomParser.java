package com.aconex.gedcom;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.aconex.gedcom.util.FileUtil;

/**
 * Main class to represent Genealogical data in a specific file format. At present, only XML is supported.
 * But the application may be extended to represent data as JSON, XPATH or in other file formats.
 *
 * It expects only one argument.
 * arg0	represents the file containing configuration details.
 * 		optional
 * 		if not specified, loads default configuration as mentioned below.
 * 			1. reads the default Genealogical data file as present in the application classpath.
 * 			{@link com.aconex.gedcom.Configuration#DEFAULT_GEDCOM_DATA_FILE}
 * 			2. formats the data as XML
 * 			3. generates file with the name 'gedcom.xml'.
 * 			{@link com.aconex.gedcom.xml.XMLGenerator#DEFAULT_OUTPUT_FILE}
 *
 * @author tmishr
 */
public final class GedcomParser {

	// -------- class variables -----------

	/** log config. **/
	private static final String LOG_PROPERTIES_CONFIG = "/log.properties";

	/** logger. **/
	private static final Logger LOGGER = Logger.getLogger(GedcomParser.class.getName());

	// -------- constructor -----------

	/**
	 * Prevent instantiation as it is a utility class.
	 */
	private GedcomParser() {
	}

	// -------- methods -----------

	/**
	 * main method to initiate generating xml data.
	 * It expects only one argument.
	 *
	 * @param 	args
	 * 			arg0	represents the file containing configuration details.
	 */
	public static void main(final String[] args) {
		initLog();
		if (args != null && args.length > 1) {
			LOGGER.log(
					Level.SEVERE,
					"Only 1 argument is allowed. The argument must be the path to the config file. Terminating program now...");
			System.exit(-1);
		}

		try {
			final Configuration config = Configuration.getInstance();
			if (args != null && args.length == 0) {
				config.loadDefaultConfig();
			} else if (args != null && args.length == 1) {
				config.load(args[0]);
			}

			final InputStream gedcomDataStream = config.getInputStream();
			final FileFormat fileFormat = Enum.valueOf(FileFormat.class, config.getFileFormat());
			final Generatable generator = fileFormat.getInstance();
			generator.create(config.getOutputFile(), gedcomDataStream);
			FileUtil.close(gedcomDataStream);
		} catch (final Exception e) {
			LOGGER.log(Level.FINER, e.getMessage(), e);
			throw new BusinessException(e.getMessage(), e);
		}
	}

	/**
	 * Loads logging configuration from the properties file present in the classpath.
	 */
	private static void initLog() {
		try {
			final InputStream inputStream = GedcomParser.class
					.getResourceAsStream(LOG_PROPERTIES_CONFIG);
			LogManager.getLogManager().readConfiguration(inputStream);
		} catch (SecurityException | IOException ex) {
			LOGGER.log(Level.FINER, ex.getMessage(), ex);
			throw new BusinessException(ex.getMessage(), ex);
		}
	}
}
