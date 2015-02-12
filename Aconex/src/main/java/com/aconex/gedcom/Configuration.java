package com.aconex.gedcom;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aconex.gedcom.util.FileUtil;
import com.aconex.gedcom.util.StringUtil;

public final class Configuration {

	// -------- class variables -----------

	/** file format. **/
	private String fileFormat = "";

	/** Genealogical data input file stream. **/
	private InputStream inputStream;

	/** Genealogical data output file. **/
	private String outputFile = "";

	/** singleton instance. **/
	private static Configuration instance = new Configuration();

	/** default file containing Genealogical data. **/
	public static final String DEFAULT_GEDCOM_DATA_FILE = "/gedcom.txt";

	/** default format of the output file to represent Genealogical data. **/
	public static final String DEFAULT_OUTPUT_FORMAT = "XML";

	/** constant to represent output file format. **/
	public static final String OUTPUT_FILE_FORMAT = "output.file.format";

	/** constant to represent path to the input file data. **/
	public static final String PATH_INPUT_FILE = "path.input.file";

	/** constant to represent path to the output file. **/
	public static final String PATH_OUTPUT_FILE = "path.output.file";

	/** logger. **/
	private final static Logger LOGGER = Logger.getLogger(Configuration.class.getName());

	// -------- methods -----------
	/**
	 * restrict creating instance.
	 */
	private Configuration() {
	}

	// -------- methods ----------
	/**
	 * returns a singleton instance of configuration.
	 * -
	 * @return configuration.
	 */
	public static Configuration getInstance() {
		return instance;
	}

	/**
	 * loads configuration from the external config file. 
	 * 1. sets file format as mentioned in the file.
	 * 2. reads genealogical data as mentioned in the file.
	 * 3. sets output file as mentioned in the file.
	 * 
	 * @param 	filePath
	 * 			config file path
	 */
	public void load(final String filePath) {

		if (StringUtil.isBlank(filePath)) {
			loadDefaultConfig();
		} else {
			InputStream configFile = null;
			try {
				configFile = FileUtil.readFile(filePath);
				Properties properties = new Properties();
				properties.load(configFile);
				setFileFormat(properties.getProperty(OUTPUT_FILE_FORMAT));
				setInputStream(FileUtil.readFile(properties.getProperty(PATH_INPUT_FILE)));
				setOutputFile(properties.getProperty(PATH_OUTPUT_FILE));
				configFile.close();
			} catch (IOException e) {
				LOGGER.log(Level.FINER, e.getMessage(), e);
				throw new BusinessException(e.getMessage(), e);
			}
		}
	}

	/**
	 * loads default configuration.This includes 
	 * 1. sets file format as XML
	 * 2. reads genealogical data from the application classpath.
	 */
	public void loadDefaultConfig() {
		LOGGER.log(Level.WARNING,
				"Configuration file path not specified. Loading default configuration.");
		setFileFormat(DEFAULT_OUTPUT_FORMAT);
		InputStream inputStream = Configuration.class.getResourceAsStream(DEFAULT_GEDCOM_DATA_FILE);
		setInputStream(inputStream);
		setOutputFile("");
	}

	// -------- getters and setters -----------
	/**
	 * gets file format.
	 * 
	 * @return file format.
	 */
	public String getFileFormat() {
		return fileFormat;
	}

	/**
	 * sets file format. Validates file format.
	 * 
	 * @param	fileFormat
	 * 			file format.	
	 */
	private void setFileFormat(final String fileFormat) {
		if (!FileFormat.isValidValue(fileFormat)) {
			String errorMessage = "Unsupported file format.";
			LOGGER.log(Level.FINER, errorMessage);
			throw new BusinessException(errorMessage);
		}
		LOGGER.log(Level.INFO, "Setting file format as " + fileFormat);
		this.fileFormat = fileFormat;
	}

	/**
	 * gets output file.
	 * 
	 * @return output file.
	 */
	public String getOutputFile() {
		return outputFile;
	}

	/**
	 * sets output file.
	 * 
	 * @param	outputFile
	 * 			file path.	
	 */
	private void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	/**
	 * gets input stream.
	 * 
	 * @return input stream.
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * sets input stream
	 * 
	 * @param	inputStream
	 * 			input Stream.	
	 */
	private void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
