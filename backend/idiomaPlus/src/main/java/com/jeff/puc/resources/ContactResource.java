/*
 * 
 */
package com.jeff.puc.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jeff.puc.dto.ContactDTO;
import com.jeff.puc.services.impl.ContactService;

import javax.mail.MessagingException;
import java.net.URI;

// TODO: Auto-generated Javadoc
/**
 * The Class ContactResource.
 */
@RestController
@RequestMapping(value = "/contacts")
public class ContactResource {
	
	/** The Contact service. */
	private final ContactService ContactService;

	/**
	 * Instantiates a new contact resource.
	 *
	 * @param ContactService the contact service
	 */
	public ContactResource(ContactService ContactService) {
		this.ContactService = ContactService;
	}

	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the response entity
	 */
	@GetMapping
	public ResponseEntity<Page<ContactDTO>> findAll(Pageable pageable) {
		return ResponseEntity.ok(this.ContactService.findAll(pageable));
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ContactDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(this.ContactService.findById(id));
	}

	/**
	 * Update.
	 *
	 * @param id the id
	 * @param ContactDTO the contact DTO
	 * @return the response entity
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ContactDTO> update(@PathVariable Long id, @RequestBody ContactDTO ContactDTO) {
		return ResponseEntity.accepted().body(this.ContactService.update(id, ContactDTO));
	}

	/**
	 * Insert.
	 *
	 * @param newContactDTO the new contact DTO
	 * @return the response entity
	 * @throws MessagingException the messaging exception
	 */
	@PostMapping
	public ResponseEntity<ContactDTO> insert(@RequestBody ContactDTO newContactDTO) throws MessagingException {
		ContactDTO contactDTO = this.ContactService.insert(newContactDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contactDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(contactDTO);
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.ContactService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
