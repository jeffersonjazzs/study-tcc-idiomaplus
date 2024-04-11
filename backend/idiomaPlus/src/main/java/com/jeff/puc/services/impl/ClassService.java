/*
 * 
 */
package com.jeff.puc.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeff.puc.domain.Class;
import com.jeff.puc.dto.ClassDTO;
import com.jeff.puc.repositories.ClassRepository;
import com.jeff.puc.services.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class ClassService.
 */
@Service
public class ClassService {
	
	/** The class repository. */
	private final ClassRepository classRepository;

	/**
	 * Instantiates a new class service.
	 *
	 * @param classRepository the class repository
	 */
	public ClassService(ClassRepository classRepository) {
		this.classRepository = classRepository;
	}

	/**
	 * Insert.
	 *
	 * @param classDTO the class DTO
	 * @return the class DTO
	 */
	@Transactional(rollbackFor = Exception.class)
	public ClassDTO insert(ClassDTO classDTO) {
		Class model = new Class(classDTO);
		model = this.classRepository.save(model);
		classDTO.setId(model.getId());
		return classDTO;
	}

	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	@Transactional(readOnly = true)
	public Page<ClassDTO> findAll(Pageable pageable) {
		return this.classRepository.findAll(pageable).map(ClassDTO::new);
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the class DTO
	 */
	@Transactional(readOnly = true)
	public ClassDTO findById(Long id) {
		return new ClassDTO(this.findModel(id));
	}

	/**
	 * Update.
	 *
	 * @param id the id
	 * @param classDTO the class DTO
	 * @return the class DTO
	 */
	@Transactional(rollbackFor = Exception.class)
	public ClassDTO update(Long id, ClassDTO classDTO) {
		ClassDTO fromDatabase = this.findById(id);
		Util.myCopyProperties(classDTO, fromDatabase);
		this.classRepository.save(new Class(fromDatabase));
		return classDTO;
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) {
		this.classRepository.delete(this.findModel(id));
	}

	/**
	 * Find model.
	 *
	 * @param id the id
	 * @return the class
	 */
	protected Class findModel(Long id) {
		return this.classRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
	}

}
