/*
 * 
 */
package com.jeff.puc.resources.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class StandardError.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StandardError implements Serializable {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The timestamp. */
    private Long timestamp;
    
    /** The status. */
    private Integer status;
    
    /** The error. */
    private String error;
    
    /** The message. */
    private String message;
    
    /** The path. */
    private String path;

}
