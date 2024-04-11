/*
 * 
 */
package com.jeff.puc.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jeff.puc.dto.TeacherDTO;
import com.jeff.puc.services.impl.TeacherService;

import java.net.URI;

// TODO: Auto-generated Javadoc
/**
 * The Class TeacherResource.
 */
@RestController
@RequestMapping("/teacher")
public class TeacherResource {
	
	/** The teacher service. */
	private final TeacherService teacherService;

	/**
	 * Instantiates a new teacher resource.
	 *
	 * @param teacherService the teacher service
	 */
	public TeacherResource(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the response entity
	 */
	@GetMapping
	public ResponseEntity<Page<TeacherDTO>> findAll(Pageable pageable) {
		return ResponseEntity.ok(this.teacherService.findAll(pageable));
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/{id}")
	public ResponseEntity<TeacherDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(this.teacherService.findById(id));
	}

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @param pageable the pageable
	 * @return the response entity
	 */
	@GetMapping("/name/{name}")
	public ResponseEntity<Page<TeacherDTO>> findByName(@PathVariable String name, Pageable pageable) {
		return ResponseEntity.ok(this.teacherService.findByName(name, pageable));
	}

	/**
	 * Update.
	 *
	 * @param id the id
	 * @param teacherDTO the teacher DTO
	 * @return the response entity
	 */
	@PutMapping("/{id}")
	public ResponseEntity<TeacherDTO> update(@PathVariable Long id, @RequestBody TeacherDTO teacherDTO) {
		return ResponseEntity.accepted().body(this.teacherService.update(id, teacherDTO));
	}

	/**
	 * Insert.
	 *
	 * @param newTeacherDTO the new teacher DTO
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<TeacherDTO> insert(@RequestBody TeacherDTO newTeacherDTO) {
		TeacherDTO teacherDTO = this.teacherService.insert(newTeacherDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(teacherDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(teacherDTO);
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.teacherService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
