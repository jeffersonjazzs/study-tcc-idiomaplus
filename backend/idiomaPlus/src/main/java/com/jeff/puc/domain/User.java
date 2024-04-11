/*
 * 
 */
package com.jeff.puc.domain;

import lombok.*;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import com.jeff.puc.dto.UserDTO;

import javax.persistence.*;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
@Entity(name = "user_auth")
@Getter
@Setter

/**
 * The Class UserBuilder.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** The name. */
	private String name;
	
	/** The username. */
	private String username;
	
	/** The email. */
	private String email;
	
	/** The password. */
	private String password;
	
	/** The role. */
	private String role;

	/**
	 * Instantiates a new user.
	 *
	 * @param userDTO the user DTO
	 */
	public User(UserDTO userDTO) {
		this.id = userDTO.getId();
		this.name = userDTO.getName();
		this.email = userDTO.getEmail();
		this.password = new Argon2PasswordEncoder(16, 32, 1, 4096, 3).encode(userDTO.getPassword());
		this.role = userDTO.getRole();
		this.username = userDTO.getUsername();
	}
}
