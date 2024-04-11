/*
 * 
 */
package com.jeff.puc.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginResponse.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

/**
 * The Class LoginResponseBuilder.
 */
@Builder
public class LoginResponse {
   
	/** The access token. */
	private String accessToken;
    
    /** The refresh token. */
    private String refreshToken;
    
    /** The username. */
    private String username;
    
    /** The roles. */
    private List<String> roles;
}
