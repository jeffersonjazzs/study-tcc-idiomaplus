/*
 * 
 */
package com.jeff.puc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.jeff.puc.domain.User;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	/** The id. */
	@NotBlank
	protected Long id;
	
	/** The name. */
	@NotBlank
	protected String name;
	
	/** The email. */
	@NotBlank
	@Email
	protected String email;
	
	/** The password. */
	@NotBlank
	protected String password;
	
	/** The role. */
	@NotBlank
	protected String role;
	
	/** The username. */
	@NotBlank
	public String username;

	/**
	 * Instantiates a new user DTO.
	 *
	 * @param user the user
	 */
	public UserDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.role = user.getRole();
		this.username = user.getUsername();
	}
}
