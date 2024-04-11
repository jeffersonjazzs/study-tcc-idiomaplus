/*
 * 
 */
package com.jeff.puc.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeff.puc.domain.Student;
import com.jeff.puc.dto.StudentDTO;
import com.jeff.puc.email.SendEmailService;
import com.jeff.puc.messages.EmailMessages;
import com.jeff.puc.repositories.StudentRepository;
import com.jeff.puc.services.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentService.
 */
@Service
public class StudentService {
	
	/** The student repository. */
	private final StudentRepository studentRepository;

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
	 * Instantiates a new student service.
	 *
	 * @param studentRepository the student repository
	 * @param sendEmailService the send email service
	 */
	public StudentService(StudentRepository studentRepository, SendEmailService sendEmailService) {
		this.studentRepository = studentRepository;
		this.sendEmailService = sendEmailService;
	}

	/**
	 * Insert.
	 *
	 * @param newStudentDTO the new student DTO
	 * @return the student DTO
	 */
	@Transactional(rollbackFor = Exception.class)
	public StudentDTO insert(StudentDTO newStudentDTO) {
		String email = newStudentDTO.getEmail();
		if (this.studentRepository.existsByEmail(email)) {
			throw new RuntimeException("Email already exists");
		}

		Student model = new Student(newStudentDTO);
		model = this.studentRepository.save(model);
		newStudentDTO.setId(model.getId());

		try {
			this.getSendEmailService().enviarEmailComAnexo(newStudentDTO.getEmail(),
					EmailMessages.createTitle(newStudentDTO), EmailMessages.messageToNewUserLogoStudent(newStudentDTO),
					"/logo/logo-white.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newStudentDTO;
	}

	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	@Transactional(readOnly = true)
	public Page<StudentDTO> findAll(Pageable pageable) {
		return this.studentRepository.findAll(pageable).map(StudentDTO::new);
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the student DTO
	 */
	@Transactional(readOnly = true)
	public StudentDTO findById(Long id) {
		return new StudentDTO(this.findModel(id));
	}

	/**
	 * Update.
	 *
	 * @param id the id
	 * @param studentDTO the student DTO
	 * @return the student DTO
	 */
	@Transactional(rollbackFor = Exception.class)
	public StudentDTO update(Long id, StudentDTO studentDTO) {
		StudentDTO fromDatabase = this.findById(id);
		Util.myCopyProperties(studentDTO, fromDatabase);
		this.studentRepository.save(new Student(fromDatabase));
		return studentDTO;
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) {
		this.studentRepository.delete(this.findModel(id));
	}

	/**
	 * Find model.
	 *
	 * @param id the id
	 * @return the student
	 */
	protected Student findModel(Long id) {
		return this.studentRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
	}

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @param pageable the pageable
	 * @return the page
	 */
	public Page<StudentDTO> findByName(String name, Pageable pageable) {
		return this.studentRepository.findAllByNameContainsIgnoreCase(name, pageable).map(StudentDTO::new);
	}
}
