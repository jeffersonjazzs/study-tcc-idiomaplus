/*
 * 
 */
package com.jeff.puc.services.impl;

import javax.mail.MessagingException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jeff.puc.dto.ContactDTO;
import com.jeff.puc.email.SendEmailService;
import com.jeff.puc.messages.EmailMessages;
import com.jeff.puc.repositories.ContactRepository;
import com.jeff.puc.services.Util;
import com.jeff.puc.domain.Contact;

// TODO: Auto-generated Javadoc
/**
 * The Class ContactService.
 */
@Service
public class ContactService {
	
	/** The contact repository. */
	private final ContactRepository contactRepository;
	
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
	 * Instantiates a new contact service.
	 *
	 * @param contactRepository the contact repository
	 * @param sendEmailService the send email service
	 */
	public ContactService(ContactRepository contactRepository, SendEmailService sendEmailService) {
		this.contactRepository = contactRepository;
		this.sendEmailService = sendEmailService;
	}

	/**
	 * Insert.
	 *
	 * @param newContactDTO the new contact DTO
	 * @return the contact DTO
	 * @throws MessagingException the messaging exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public ContactDTO insert(ContactDTO newContactDTO) throws MessagingException {
		Contact model = new Contact(newContactDTO);
		model = this.contactRepository.save(model);
		newContactDTO.setId(model.getId());
		try {
			this.getSendEmailService().enviarEmailComAnexo(newContactDTO.getEmail(),
					EmailMessages.createTitleContact(newContactDTO), EmailMessages.dadosContact(newContactDTO),
					"/logo/logo-white.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newContactDTO;
	}

	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	@Transactional(readOnly = true)
	public Page<ContactDTO> findAll(Pageable pageable) {
		return this.contactRepository.findAll(pageable).map(ContactDTO::new);
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the contact DTO
	 */
	@Transactional(readOnly = true)
	public ContactDTO findById(Long id) {
		return new ContactDTO(this.findModel(id));
	}

	/**
	 * Update.
	 *
	 * @param id the id
	 * @param contactDTO the contact DTO
	 * @return the contact DTO
	 */
	@Transactional(rollbackFor = Exception.class)
	public ContactDTO update(Long id, ContactDTO contactDTO) {
		ContactDTO fromDatabase = this.findById(id);
		Util.myCopyProperties(contactDTO, fromDatabase);
		this.contactRepository.save(new Contact(fromDatabase));
		return contactDTO;
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) {
		this.contactRepository.delete(this.findModel(id));
	}

	/**
	 * Find model.
	 *
	 * @param id the id
	 * @return the contact
	 */
	protected Contact findModel(Long id) {
		return this.contactRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
	}
}
