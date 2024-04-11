/*
 * 
 */
package com.jeff.puc.dto;

import com.jeff.puc.domain.Contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class ContactDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {
    
    /** The id. */
    protected Long id;
    
    /** The name. */
    protected String name;
    
    /** The email. */
    protected String email;
    
    /** The subject. */
    protected String subject;
    
    /** The message. */
    protected String message;

    /**
     * Instantiates a new contact DTO.
     *
     * @param contact the contact
     */
    public ContactDTO(Contact contact) {
        this.id = contact.getId();
        this.name = contact.getName();
        this.email = contact.getEmail();
        this.subject = contact.getSubject();
        this.message = contact.getMessage();
    }
}
