/*
 * 
 */
package com.jeff.puc.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jeff.puc.dto.ClassDTO;
import com.jeff.puc.services.impl.ClassService;

import java.net.URI;

// TODO: Auto-generated Javadoc
/**
 * The Class ClassResource.
 */
@RestController
@RequestMapping("/class")
public class ClassResource {
	
	/** The class service. */
	private final ClassService classService;

	/**
	 * Instantiates a new class resource.
	 *
	 * @param classService the class service
	 */
	public ClassResource(ClassService classService) {
		this.classService = classService;
	}

	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the response entity
	 */
	@GetMapping
	public ResponseEntity<Page<ClassDTO>> findAll(Pageable pageable) {
		return ResponseEntity.ok(this.classService.findAll(pageable));
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ClassDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(this.classService.findById(id));
	}

	/**
	 * Update.
	 *
	 * @param id the id
	 * @param classDTO the class DTO
	 * @return the response entity
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ClassDTO> update(@PathVariable Long id, @RequestBody ClassDTO classDTO) {
		return ResponseEntity.accepted().body(this.classService.update(id, classDTO));
	}

	/**
	 * Insert.
	 *
	 * @param classDTO the class DTO
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<ClassDTO> insert(@RequestBody ClassDTO classDTO) {
		classDTO = this.classService.insert(classDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(classDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(classDTO);
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.classService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
