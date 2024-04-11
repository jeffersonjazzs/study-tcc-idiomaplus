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
 * The Class FieldMessage.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FieldMessage implements Serializable {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The field name. */
    private String fieldName;
    
    /** The message. */
    private String message;



}
