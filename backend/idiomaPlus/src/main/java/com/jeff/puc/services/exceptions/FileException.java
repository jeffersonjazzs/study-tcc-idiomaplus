/*
 * 
 */
package com.jeff.puc.services.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class FileException.
 */
public class FileException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new file exception.
	 *
	 * @param msg the msg
	 */
	public FileException(String msg) {
		super(msg);
	}

	/**
	 * Instantiates a new file exception.
	 *
	 * @param msg the msg
	 * @param cause the cause
	 */
	public FileException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
