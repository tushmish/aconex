package com.aconex.gedcom.util;

/**
 * Utility class for String.
 * 
 * @author tmishr
 */
public final class StringUtil {

	// -------- constructor -----------

	/**
	 * Prevent instantiation as it is a utility class.
	 */
	private StringUtil() {
	}

	// -------- methods -----------

	/**
	 * Checks if the string is empty "", null or whitespace " ".
	 * 
	 * @param 	cs
	 * 			the string to be evaluated.
	 * @return	true, if any of the above is met.
	 * 			false, otherwise.
	 */
	public static boolean isBlank(final CharSequence cs) {
		if (cs == null || (cs.length() == 0)) {
			return true;
		}
		return isWhitespace(cs);
	}

	/**
	 * Checks if the string is a whitespace " ".
	 * 
	 * @param 	cs
	 * 			the string to be evaluated.
	 * @return	true, if the all characters in the string are white spaces.
	 * 			false, otherwise.
	 */
	public static boolean isWhitespace(final CharSequence cs) {
		int strLen = cs.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(cs.charAt(i))) {
				return false;
			}
		}
		return true;
	}

}
