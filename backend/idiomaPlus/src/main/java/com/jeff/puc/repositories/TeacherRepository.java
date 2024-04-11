/*
 * 
 */
package com.jeff.puc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeff.puc.domain.Teacher;

// TODO: Auto-generated Javadoc
/**
 * The Interface TeacherRepository.
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

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
	Page<Teacher> findAllByNameContainsIgnoreCase(String name, Pageable pageable);

}
