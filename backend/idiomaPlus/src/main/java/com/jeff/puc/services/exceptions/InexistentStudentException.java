/*
 * 
 */
package com.jeff.puc.services.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class InexistentStudentException.
 */
public class InexistentStudentException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new inexistent student exception.
	 *
	 * @param msg the msg
	 */
	public InexistentStudentException(String msg) {
		super(msg);
	}

	/**
	 * Instantiates a new inexistent student exception.
	 *
	 * @param msg the msg
	 * @param cause the cause
	 */
	public InexistentStudentException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
