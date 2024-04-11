/*
 * 
 */
package com.jeff.puc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeff.puc.domain.User;

import java.util.Optional;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserRepository.
 */
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Find by username.
	 *
	 * @param userName the user name
	 * @return the optional
	 */
	Optional<User> findByUsername(String userName);

	/**
	 * Exists by email.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	boolean existsByEmail(String email);

	/**
	 * Exists by username.
	 *
	 * @param username the username
	 * @return true, if successful
	 */
	boolean existsByUsername(String username);

}
