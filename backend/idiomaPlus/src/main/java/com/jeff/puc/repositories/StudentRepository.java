/*
 * 
 */
package com.jeff.puc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeff.puc.domain.Student;

// TODO: Auto-generated Javadoc
/**
 * The Interface StudentRepository.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	/**
	 * Exists by email.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	boolean existsByEmail(String email);

	/**
	 * Find all by name contains ignore case.
	 *
	 * @param name the name
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<Student> findAllByNameContainsIgnoreCase(String name, Pageable pageable);

}