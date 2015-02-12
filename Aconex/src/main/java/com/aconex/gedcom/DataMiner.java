package com.aconex.gedcom;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aconex.gedcom.util.StringUtil;

/**
 * extracts elemental data from the genealogical raw data file.
 * 
 * @author tmishr
 */
public final class DataMiner {

	// -------- class variables -----------
	/** raw data. **/
	private String data;

	/** logger. **/
	private final static Logger LOGGER = Logger.getLogger(DataMiner.class.getName());

	// -------- constructor -----------
	/**
	 * Creates an instance.
	 * 
	 * @param 	rawData
	 * 			raw genealogical data 
	 */
	public DataMiner(final String rawData) {
		this.data = rawData;
	}

	// -------- methods -----------

	/**
	 * As per the problem statement, the first character is a digit. It refers to the current depth in the tree.
	 * In the example below, level is 0.
	 * <pre>
	 * 	0 @I1@ INDI
	 * </pre>
	 *  
	 * @return 	depth of the node in the tree.
	 * @throws	BusinessException
	 * 			if the level is blank.
	 */
	public int parseLevel() {
		String level = String.valueOf(data.charAt(0));
		if (StringUtil.isBlank(level)) {
			String errorMessage = "improper raw data. Missing level. " + data;
			LOGGER.log(Level.FINER, errorMessage);
			throw new BusinessException(errorMessage);
		}
		return Integer.valueOf(level);
	}

	/**
	 * Id is a unique identifier. The unique identifiers are always text surrounded by "@" characters (i.e., "@I54@").
	 * In the example below, id is INDI.
	 * <pre>
	 * 	0 @I1@ INDI
	 * </pre>
	 * 
	 * @return 	id.
	 * 			null, if no matches found.
	 */
	public String parseId() {
		Pattern pattern = Pattern.compile("@(.*?)@");
		Matcher matcher = pattern.matcher(data);
		if (matcher.find()) {
			return matcher.group();
		}
		return null;
	}

	/**
	 * Tags are 3 or 4-letter words in uppercase. Variable whitespace is allowed between the level and the tag.
	 * In the example below, tag is NAME.
	 * <pre>
	 * 	1 NAME Jamis Gordon /Buck/
	 * </pre>
	 * 
	 * @return 	tag name.
	 * 			null, if no matches found.
	 */
	public String parseTag() {
		Pattern pattern = Pattern.compile("(\\s+[A-Z]{4}\\b)|(\\s+[A-Z]{3}\\b)");
		Matcher matcher = pattern.matcher(data);

		while (matcher.find()) {
			String tag = matcher.group();
			if (!StringUtil.isBlank(tag)) {
				return tag.trim();
			}
		}
		return null;
	}

	/**
	 * parses node value when id is present.
	 * In the example below, id value is '@I1@'.
	 * <pre>
	 * 	0 @I1@ INDI
	 * </pre>
	 * 
	 * @return 	the element value.
	 */
	public String parseIdValue(String id) {
		if (StringUtil.isBlank(id)) {
			id = parseId();
		}
		if (StringUtil.isBlank(id)) {
			String errorMessage = "Improper data. Id not found.";
			LOGGER.log(Level.FINER, errorMessage);
			throw new BusinessException(errorMessage);
		}
		int index = data.indexOf(id);
		return data.substring(index + id.length()).trim();
	}

	/**
	 * parses node value when tag is present.
	 * In the example below, tag value is 'Jamis Gordon /Buck/'.
	 * <pre>
	 * 	1 NAME Jamis Gordon /Buck/
	 * </pre>
	 * 
	 * @return 	the element value.
	 */
	public String parseTagValue(String tag) {
		if (StringUtil.isBlank(tag)) {
			tag = parseTag();
		}

		if (StringUtil.isBlank(tag)) {
			String errorMessage = "Improper data. Tag name of length 3 to 4 chars not found.";
			LOGGER.log(Level.FINER, errorMessage);
			throw new BusinessException(errorMessage);
		}
		int index = data.indexOf(tag);
		return data.substring(index + tag.length()).trim();
	}
}
