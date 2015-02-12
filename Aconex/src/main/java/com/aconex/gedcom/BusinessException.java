package com.aconex.gedcom;

/**
 * Base exception that is thrown throughout the execution of the application.
 *  
 * @author tmishr
 */
public class BusinessException extends RuntimeException {

	// -------- class variables -----------

	/** serialization id. **/
	private static final long serialVersionUID = 3098800025184410070L;

	// -------- constructors -----------

	/**
	 * Creates exception using a message.
	 * 
	 * @param 	errorMessage
	 * 			error message.
	 */
	public BusinessException(final String errorMessage) {
		super(errorMessage);
	}

	/**
	 * Creates exception using a message and cause.
	 * 
	 * @param 	errorMessage
	 * 			error message.
	 * @param	cause
	 * 			cause of exception.
	 */
	public BusinessException(final String errorMessage, final Throwable cause) {
		super(errorMessage, cause);
	}

}
