/*
 * 
 */
package com.jeff.puc.dto.auth;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class RefreshResponse.
 */
@Data

/**
 * The Class RefreshResponseBuilder.
 */
@Builder
@NoArgsConstructor
public class RefreshResponse {
    
    /** The access token. */
    private String accessToken;
    
    /** The refresh token. */
    private String refreshToken;

    /**
     * Instantiates a new refresh response.
     *
     * @param accessToken the access token
     * @param refreshToken the refresh token
     */
    public RefreshResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
