/*
 * 
 */
package com.jeff.puc.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class RefreshRequest.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshRequest {
    
    /** The refresh token. */
    private String refreshToken;
}
