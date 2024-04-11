/*
 * 
 */
package com.jeff.puc.services.impl;

import javax.mail.MessagingException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeff.puc.config.UserDetailsImpl;
import com.jeff.puc.domain.User;
import com.jeff.puc.dto.UserDTO;
import com.jeff.puc.email.SendEmailService;
import com.jeff.puc.messages.EmailMessages;
import com.jeff.puc.repositories.UserRepository;
import com.jeff.puc.services.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDetailsServiceImpl.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	/** The user repository. */
	private final UserRepository userRepository;
	
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
	 * Instantiates a new user details service impl.
	 *
	 * @param userRepository the user repository
	 * @param sendEmailService the send email service
	 */
	public UserDetailsServiceImpl(UserRepository userRepository, SendEmailService sendEmailService) {
		this.userRepository = userRepository;
		this.sendEmailService = sendEmailService;
	}

	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the user
	 */
	@Transactional(readOnly = true)
	public User findByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
	}

	/**
	 * Load user by username.
	 *
	 * @param username the username
	 * @return the user details
	 * @throws UsernameNotFoundException the username not found exception
	 */
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.findByUsername(username);
		return UserDetailsImpl.build(user);
	}

	/**
	 * Insert.
	 *
	 * @param newUserDTO the new user DTO
	 * @return the user DTO
	 * @throws MessagingException the messaging exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public UserDTO insert(UserDTO newUserDTO) throws MessagingException {
		String email = newUserDTO.getEmail();
		if (this.userRepository.existsByEmail(email)) {
			throw new RuntimeException("Email already exists");
		}
		User model = new User(newUserDTO);
		model = this.userRepository.save(model);
		newUserDTO.setId(model.getId());
		try {

			this.getSendEmailService().enviarEmailComAnexo(newUserDTO.getEmail(), EmailMessages.createTitle(newUserDTO),
					EmailMessages.messageToNewUserLogo(newUserDTO), "/logo/logo-white.png");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return newUserDTO;
	}

	/**
	 * Enviar senha.
	 *
	 * @param userName the user name
	 * @return the user DTO
	 */
	@Transactional(rollbackFor = Exception.class)
	public UserDTO enviarSenha(String userName) {
		UserDTO userDTO = new UserDTO(this.findByUsername(userName));
		this.userRepository.findByUsername(userDTO.getEmail());
		try {

			this.getSendEmailService().enviarEmailComAnexo(userDTO.getEmail(), EmailMessages.createTitleSenha(userDTO),
					EmailMessages.recuperarDados(userDTO), "/logo/logo-white.png");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDTO;
	}

	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	@Transactional(readOnly = true)
	public Page<UserDTO> findAll(Pageable pageable) {
		return this.userRepository.findAll(pageable).map(UserDTO::new);
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the user DTO
	 */
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		return new UserDTO(this.findModel(id));
	}

	/**
	 * Update.
	 *
	 * @param id the id
	 * @param userDTO the user DTO
	 * @return the user DTO
	 */
	@Transactional(rollbackFor = Exception.class)
	public UserDTO update(Long id, UserDTO userDTO) {
		UserDTO fromDatabase = this.findById(id);
		Util.myCopyProperties(userDTO, fromDatabase);
		this.userRepository.save(new User(fromDatabase));
		return userDTO;
	}

	/**
	 * Trocar senha.
	 *
	 * @param id the id
	 * @param userDTO the user DTO
	 * @return the user DTO
	 */
	@Transactional(rollbackFor = Exception.class)
	public UserDTO trocarSenha(Long id, UserDTO userDTO) {
		UserDTO fromDatabase = this.findById(id);
		if (id == null) {
			throw new RuntimeException("not found");
		}

		Util.myCopyProperties(userDTO, fromDatabase);
		this.userRepository.save(new User(fromDatabase));
		return userDTO;
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) {
		this.userRepository.delete(this.findModel(id));
	}

	/**
	 * Find model.
	 *
	 * @param id the id
	 * @return the user
	 */
	protected User findModel(Long id) {
		return this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
	}

}
