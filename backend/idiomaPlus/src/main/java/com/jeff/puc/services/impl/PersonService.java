/*
 * 
 */
package com.jeff.puc.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeff.puc.domain.Person;
import com.jeff.puc.dto.PersonDTO;
import com.jeff.puc.repositories.PersonRepository;
import com.jeff.puc.services.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonService.
 */
@Service
public class PersonService {
	
	/** The person repository. */
	private final PersonRepository personRepository;

	/**
	 * Instantiates a new person service.
	 *
	 * @param addressRepository the address repository
	 */
	public PersonService(PersonRepository addressRepository) {
		this.personRepository = addressRepository;
	}

	/**
	 * Insert.
	 *
	 * @param personDTO the person DTO
	 * @return the person DTO
	 */
	@Transactional(rollbackFor = Exception.class)
	public PersonDTO insert(PersonDTO personDTO) {
		String email = personDTO.getEmail();
		if (this.personRepository.existsByEmail(email)) {
			throw new RuntimeException("Email already exists");
		}
		Person model = new Person(personDTO);
		model = this.personRepository.save(model);
		personDTO.setId(model.getId());
		return personDTO;
	}

	/**
	 * Update.
	 *
	 * @param id the id
	 * @param personDTO the person DTO
	 * @return the person DTO
	 */
	@Transactional(rollbackFor = Exception.class)
	public PersonDTO update(Long id, PersonDTO personDTO) {
		PersonDTO fromDatabase = this.findById(id);
		Util.myCopyProperties(personDTO, fromDatabase);
		this.personRepository.save(new Person(fromDatabase));
		return personDTO;
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the person DTO
	 */
	@Transactional(readOnly = true)
	public PersonDTO findById(Long id) {
		Person model = this.personRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
		return new PersonDTO(model);
	}

}
