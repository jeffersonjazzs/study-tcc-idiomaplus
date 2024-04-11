/*
 * 
 */
package com.jeff.puc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.jeff.puc.dto.PersonDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class Person.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	/** The name. */
	protected String name;
	
	/** The phone. */
	protected String phone;
	
	/** The email. */
	protected String email;
	
	/** The cep. */
	protected String cep;
	
	/** The street. */
	protected String street;
	
	/** The number. */
	protected Integer number;
	
	/** The district. */
	protected String district;
	
	/** The city. */
	protected String city;
	
	/** The state. */
	protected String state;
	
	/** The country. */
	protected String country;

	/**
	 * Instantiates a new person.
	 *
	 * @param personDTO the person DTO
	 */
	public Person(PersonDTO personDTO) {
		this.id = personDTO.getId();
		this.name = personDTO.getName();
		this.phone = personDTO.getPhone();
		this.email = personDTO.getEmail();
		this.cep = personDTO.getCep();
		this.street = personDTO.getStreet();
		this.number = personDTO.getNumber();
		this.district = personDTO.getDistrict();
		this.city = personDTO.getCity();
		this.state = personDTO.getState();
		this.country = personDTO.getCountry();
	}
}
