/*
 * 
 */
package com.jeff.puc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.jeff.puc.dto.ContactDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class Contact.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	 * Instantiates a new contact.
	 *
	 * @param contactDTO the contact DTO
	 */
	public Contact(ContactDTO contactDTO) {
		this.id = contactDTO.getId();
		this.name = contactDTO.getName();
		this.email = contactDTO.getEmail();
		this.subject = contactDTO.getSubject();
		this.message = contactDTO.getMessage();
	}
}
