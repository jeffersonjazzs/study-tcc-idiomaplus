/*
 * 
 */
package com.jeff.puc.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jeff.puc.dto.UserDTO;
import com.jeff.puc.repositories.UserRepository;
import com.jeff.puc.services.impl.UserDetailsServiceImpl;

import java.net.URI;

// TODO: Auto-generated Javadoc
/**
 * The Class UserResource.
 */
@RestController
@RequestMapping("/user")
public class UserResource {
	
	/** The user details service impl. */
	private final UserDetailsServiceImpl userDetailsServiceImpl;
	
	/** The user repository. */
	private final UserRepository userRepository;

	/**
	 * Instantiates a new user resource.
	 *
	 * @param userDetailsServiceImpl the user details service impl
	 * @param userRepository the user repository
	 */
	public UserResource(UserDetailsServiceImpl userDetailsServiceImpl, UserRepository userRepository) {
		this.userDetailsServiceImpl = userDetailsServiceImpl;
		this.userRepository = userRepository;
	}

	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the response entity
	 */
	@GetMapping
	public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable) {
		return ResponseEntity.ok(this.userDetailsServiceImpl.findAll(pageable));
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(this.userDetailsServiceImpl.findById(id));
	}

	/**
	 * Find by name.
	 *
	 * @param userName the user name
	 * @return the response entity
	 */
	@GetMapping("/userName/{userName}")
	public ResponseEntity<UserDTO> findByName(@PathVariable String userName) {
		this.userDetailsServiceImpl.findByUsername(userName);
		return ResponseEntity.ok(this.userDetailsServiceImpl.enviarSenha(userName));
	}

	/**
	 * Update.
	 *
	 * @param id the id
	 * @param userDTO the user DTO
	 * @return the response entity
	 */
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
		return ResponseEntity.accepted().body(this.userDetailsServiceImpl.update(id, userDTO));
	}

	/**
	 * Trocar senha.
	 *
	 * @param id the id
	 * @param userDTO the user DTO
	 * @return the response entity
	 */
	@PutMapping("trocarSenha/{id}")
	public ResponseEntity<UserDTO> trocarSenha(@PathVariable Long id, @RequestBody UserDTO userDTO) {
		return ResponseEntity.accepted().body(this.userDetailsServiceImpl.trocarSenha(id, userDTO));
	}

	/**
	 * Insert.
	 *
	 * @param newUserDTO the new user DTO
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<UserDTO> insert(@RequestBody UserDTO newUserDTO) {
		try {
			this.userRepository.findByUsername(newUserDTO.getUsername()).ifPresent(user -> {
				throw new Error("Username already exists");
			});

			UserDTO userDTO = this.userDetailsServiceImpl.insert(newUserDTO);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userDTO.getId())
					.toUri();
			return ResponseEntity.created(uri).body(userDTO);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.userDetailsServiceImpl.delete(id);
		return ResponseEntity.noContent().build();
	}

}
