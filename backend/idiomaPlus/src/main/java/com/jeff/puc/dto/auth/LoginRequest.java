/*
 * 
 */
package com.jeff.puc.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginRequest.
 */
@Data

/**
 * The Class LoginRequestBuilder.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    
    /** The username. */
    private String username;
    
    /** The password. */
    private String password;
	 
}
