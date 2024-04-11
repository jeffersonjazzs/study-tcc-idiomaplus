/*
 * 
 */
package com.jeff.puc.services.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class ObjectNotFoundException.
 */
public class ObjectNotFoundException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new object not found exception.
     *
     * @param msg the msg
     */
    public ObjectNotFoundException(String msg) {
        super(msg);
    }

    /**
     * Instantiates a new object not found exception.
     *
     * @param msg the msg
     * @param cause the cause
     */
    public ObjectNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
