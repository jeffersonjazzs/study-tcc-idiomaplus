/*
 * 
 */
package com.jeff.puc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jeff.puc.domain.Contact;
import com.jeff.puc.domain.Student;

// TODO: Auto-generated Javadoc
/**
 * The Interface ContactRepository.
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {

    /**
     * Find all by name contains ignore case.
     *
     * @param name the name
     * @param pageable the pageable
     * @return the page
     */
    Page<Contact> findAllByNameContainsIgnoreCase(String name, Pageable pageable);
}
