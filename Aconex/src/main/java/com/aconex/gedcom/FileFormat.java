package com.aconex.gedcom;

import com.aconex.gedcom.format.Generatable;
import com.aconex.gedcom.format.XMLGenerator;
import com.aconex.gedcom.util.StringUtil;

/**
 * represents the file format for the output data representation.
 *
 * @author tushar
 */
public enum FileFormat {

	// -------- enum types ----------
	/**
	 * file format types.
	 */
	XML("xml") {

		/**
		 * Returns an instance of an XML Generator.
		 *
		 * @return an instance of a XML Generator.
		 */
		@Override
		public Generatable getInstance() {
			return new XMLGenerator();
		}
	};

	// -------- class variables ----------

	/** display name of an enum.**/
	private String displayName;

	// -------- constructor --------------

	/**
	 * sets the display name of a file format.
	 *
	 * @param 	displayName
	 * 			the display name of a file format.
	 */
	private FileFormat(final String displayName) {
		this.displayName = displayName;
	}

	// -------- methods ------------------

	/**
	 * Returns the display name of a file format.
	 *
	 * @return the display name of a file format.
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Returns an instance of a Generator.
	 *
	 * @return an instance of a Generator.
	 */
	public abstract Generatable getInstance();

	/**
	 * Validates if the value belongs to one of the file format types.
	 *
	 * @param  	value
	 * 			the field value.
	 * @return 	true, if the value is one of the file format types' display names.
	 * 			false, otherwise.
	 */
	public static final boolean isValidValue(final String value) {
		boolean status = false;

		if (StringUtil.isBlank(value)) {
			return status;
		}
		FileFormat[] enumtypes = FileFormat.values();
		for (FileFormat workflow : enumtypes) {
			if (workflow.getDisplayName().equalsIgnoreCase(value)) {
				status = true;
			}
		}
		return status;
	}

}
