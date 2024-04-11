/*
 * 
 */
package com.jeff.puc.services.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class DataIntegrityException.
 */
public class DataIntegrityException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new data integrity exception.
	 *
	 * @param msg the msg
	 */
	public DataIntegrityException(String msg) {
		super(msg);
	}

	/**
	 * Instantiates a new data integrity exception.
	 *
	 * @param msg the msg
	 * @param cause the cause
	 */
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
