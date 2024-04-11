/*
 * 
 */
package com.jeff.puc.services.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.jeff.puc.config.UserDetailsImpl;
import com.jeff.puc.dto.auth.LoginRequest;
import com.jeff.puc.dto.auth.LoginResponse;
import com.jeff.puc.dto.auth.RefreshResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthenticationService.
 */
@Service
public class AuthenticationService {
	
	/** The token service. */
	private final TokenService tokenService;
	
	/** The authentication manager. */
	private final AuthenticationManager authenticationManager;

	/**
	 * Instantiates a new authentication service.
	 *
	 * @param jwtUtils the jwt utils
	 * @param authenticationManager the authentication manager
	 */
	public AuthenticationService(TokenService jwtUtils, AuthenticationManager authenticationManager) {
		this.tokenService = jwtUtils;
		this.authenticationManager = authenticationManager;
	}

	/**
	 * Authenticate user.
	 *
	 * @param loginRequest the login request
	 * @return the login response
	 */
	public LoginResponse authenticateUser(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = (UserDetailsImpl) authentication.getPrincipal();
		String accessToken = tokenService.generateAccessToken(userDetails);
		String refreshToken = this.tokenService.generateRefreshTokenFromUsername(userDetails.getUsername());

		return LoginResponse.builder().accessToken(accessToken).refreshToken(refreshToken)
				.username(userDetails.getUsername()).build();
	}

	/**
	 * Refresh the token.
	 *
	 * @param requestRefreshToken the request refresh token
	 * @return the refresh response
	 */
	public RefreshResponse refreshTheToken(String requestRefreshToken) {
		try {
			if (this.tokenService.validateJwtToken(requestRefreshToken)) {
				String username = this.tokenService.getUserNameFromJwtToken(requestRefreshToken);
				String newAccessToken = this.tokenService.generateAccessToken(username);
				String newRefreshToken = this.tokenService.generateRefreshTokenFromUsername(username);
				return RefreshResponse.builder().accessToken(newAccessToken).refreshToken(newRefreshToken).build();
			} else {
				throw new BadCredentialsException("Token expirado");
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}