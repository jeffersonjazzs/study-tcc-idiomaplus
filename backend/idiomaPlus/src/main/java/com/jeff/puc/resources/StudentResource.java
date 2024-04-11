/*
 * 
 */
package com.jeff.puc.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jeff.puc.dto.StudentDTO;
import com.jeff.puc.services.impl.StudentService;

import java.net.URI;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentResource.
 */
@RestController
@RequestMapping("/student")
public class StudentResource {
	
	/** The student service. */
	private final StudentService studentService;

	/**
	 * Instantiates a new student resource.
	 *
	 * @param studentService the student service
	 */
	public StudentResource(StudentService studentService) {
		this.studentService = studentService;
	}

	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the response entity
	 */
	@GetMapping
	public ResponseEntity<Page<StudentDTO>> findAll(Pageable pageable) {
		return ResponseEntity.ok(this.studentService.findAll(pageable));
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(this.studentService.findById(id));
	}

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @param pageable the pageable
	 * @return the response entity
	 */
	@GetMapping("/name/{name}")
	public ResponseEntity<Page<StudentDTO>> findByName(@PathVariable String name, Pageable pageable) {
		return ResponseEntity.ok(this.studentService.findByName(name, pageable));
	}

	/**
	 * Update.
	 *
	 * @param id the id
	 * @param studentDTO the student DTO
	 * @return the response entity
	 */
	@PutMapping("/{id}")
	public ResponseEntity<StudentDTO> update(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
		return ResponseEntity.accepted().body(this.studentService.update(id, studentDTO));
	}

	/**
	 * Insert.
	 *
	 * @param newStudentDTO the new student DTO
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<StudentDTO> insert(@RequestBody StudentDTO newStudentDTO) {
		StudentDTO studentDTO = this.studentService.insert(newStudentDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(studentDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(studentDTO);
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.studentService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
