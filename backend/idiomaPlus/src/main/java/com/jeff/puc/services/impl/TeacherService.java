/*
 * 
 */
package com.jeff.puc.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeff.puc.domain.Teacher;
import com.jeff.puc.dto.TeacherDTO;
import com.jeff.puc.email.SendEmailService;
import com.jeff.puc.messages.EmailMessages;
import com.jeff.puc.repositories.TeacherRepository;
import com.jeff.puc.services.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class TeacherService.
 */
@Service
public class TeacherService {
	
	/** The teacher repository. */
	private final TeacherRepository teacherRepository;

	/** The send email service. */
	private final SendEmailService sendEmailService;

	/**
	 * Gets the send email service.
	 *
	 * @return the send email service
	 */
	public SendEmailService getSendEmailService() {
		return sendEmailService;
	}

	/**
	 * Instantiates a new teacher service.
	 *
	 * @param teacherRepository the teacher repository
	 * @param sendEmailService the send email service
	 */
	public TeacherService(TeacherRepository teacherRepository, SendEmailService sendEmailService) {
		this.teacherRepository = teacherRepository;
		this.sendEmailService = sendEmailService;
	}

	/**
	 * Insert.
	 *
	 * @param newTeacherDTO the new teacher DTO
	 * @return the teacher DTO
	 */
	@Transactional(rollbackFor = Exception.class)
	public TeacherDTO insert(TeacherDTO newTeacherDTO) {
		String email = newTeacherDTO.getEmail();
		if (this.teacherRepository.existsByEmail(email)) {
			throw new RuntimeException("Email already exists");
		}
		Teacher model = new Teacher(newTeacherDTO);
		model = this.teacherRepository.save(model);
		newTeacherDTO.setId(model.getId());
		try {

			this.getSendEmailService().enviarEmailComAnexo(newTeacherDTO.getEmail(),
					EmailMessages.createTitle(newTeacherDTO), EmailMessages.messageToNewUserLogoTeacher(newTeacherDTO),
					"/logo/logo-white.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newTeacherDTO;
	}

	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	@Transactional(readOnly = true)
	public Page<TeacherDTO> findAll(Pageable pageable) {
		return this.teacherRepository.findAll(pageable).map(TeacherDTO::new);
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the teacher DTO
	 */
	@Transactional(readOnly = true)
	public TeacherDTO findById(Long id) {
		return new TeacherDTO(this.findModel(id));
	}

	/**
	 * Update.
	 *
	 * @param id the id
	 * @param teacherDTO the teacher DTO
	 * @return the teacher DTO
	 */
	@Transactional(rollbackFor = Exception.class)
	public TeacherDTO update(Long id, TeacherDTO teacherDTO) {
		TeacherDTO fromDatabase = this.findById(id);
		Util.myCopyProperties(teacherDTO, fromDatabase);
		this.teacherRepository.save(new Teacher(fromDatabase));
		return teacherDTO;
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) {
		this.teacherRepository.delete(this.findModel(id));
	}

	/**
	 * Find model.
	 *
	 * @param id the id
	 * @return the teacher
	 */
	protected Teacher findModel(Long id) {
		return this.teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
	}

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @param pageable the pageable
	 * @return the page
	 */
	public Page<TeacherDTO> findByName(String name, Pageable pageable) {
		return this.teacherRepository.findAllByNameContainsIgnoreCase(name, pageable).map(TeacherDTO::new);
	}
}
