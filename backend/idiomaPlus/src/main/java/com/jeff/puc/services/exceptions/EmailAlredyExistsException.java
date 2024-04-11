/*
 * 
 */
package com.jeff.puc.services.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class EmailAlredyExistsException.
 */
public class EmailAlredyExistsException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new email alredy exists exception.
	 *
	 * @param msg the msg
	 */
	public EmailAlredyExistsException(String msg) {
		super(msg);
	}

	/**
	 * Instantiates a new email alredy exists exception.
	 *
	 * @param msg the msg
	 * @param cause the cause
	 */
	public EmailAlredyExistsException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
