/*
 * 
 */
package com.jeff.puc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeff.puc.domain.Person;

// TODO: Auto-generated Javadoc
/**
 * The Interface PersonRepository.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

	/**
	 * Exists by email.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	boolean existsByEmail(String email);

}
