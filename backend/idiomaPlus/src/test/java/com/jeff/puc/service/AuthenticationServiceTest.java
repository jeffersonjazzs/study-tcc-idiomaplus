/*
 * 
 */
package com.jeff.puc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jeff.puc.config.UserDetailsImpl;
import com.jeff.puc.domain.User;
import com.jeff.puc.dto.auth.LoginRequest;
import com.jeff.puc.dto.auth.LoginResponse;
import com.jeff.puc.dto.auth.RefreshResponse;
import com.jeff.puc.services.Util;
import com.jeff.puc.services.impl.AuthenticationService;
import com.jeff.puc.services.impl.TokenService;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthenticationServiceTest.
 */
@SpringBootTest
class AuthenticationServiceTest {
	
	/** The token service. */
	@Mock
	private TokenService tokenService;
	
	/** The authentication manager. */
	@Mock
	private AuthenticationManager authenticationManager;
	
	/** The util. */
	private static MockedStatic<Util> util;
	
	/** The under test. */
	@InjectMocks
	private AuthenticationService underTest;

	/**
	 * Sets the up.
	 */
	@BeforeAll
	static void setUp() {
		util = mockStatic(Util.class);
	}

	/**
	 * Tear down.
	 */
	@AfterAll
	static void tearDown() {
		util.close();
	}

	/**
	 * Authenticate user 0.
	 */
	@Test
	@DisplayName("Should return a LoginResponse when receiving a valid LoginRequest")
	void authenticateUser0() {
		String username = "username";
		String password = "password";

		UserDetails userDetails = UserDetailsImpl
				.build(User.builder().username(username).password(password).role("ADMIN").build());

		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, password,
				Stream.of("ADMIN").map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

		when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authentication);

		String accessToken = "accessToken";
		when(tokenService.generateAccessToken(any(UserDetails.class))).thenReturn(accessToken);
		String refreshToken = "refreshToken";
		when(tokenService.generateRefreshTokenFromUsername(anyString())).thenReturn(refreshToken);

		LoginRequest loginRequest = LoginRequest.builder().username(username).password(password).build();

		LoginResponse loginResponse = underTest.authenticateUser(loginRequest);

		assertEquals(accessToken, loginResponse.getAccessToken());
		assertEquals(refreshToken, loginResponse.getRefreshToken());
	}

	/**
	 * Authenticate user 1.
	 */
	@Test
	@DisplayName("Throws BadCredentialsException when receiving an invalid LoginRequest")
	void authenticateUser1() {
		when(authenticationManager.authenticate(any(Authentication.class)))
				.thenThrow(new BadCredentialsException("BadCredentialsException"));

		LoginRequest loginRequest = LoginRequest.builder().username("username").password("password").build();

		var exception = assertThrows(BadCredentialsException.class, () -> underTest.authenticateUser(loginRequest));
		assertEquals("BadCredentialsException", exception.getMessage());
	}

	/**
	 * Refresh the token 0.
	 */
	@Test
	@DisplayName("Should return a RefreshResponse when receiving a valid RefreshToken")
	void refreshTheToken0() {
		String username = "username";
		String refreshToken = "refreshToken";
		String accessToken = "accessToken";
		String newRefreshToken = "newRefreshToken";

		when(tokenService.validateJwtToken(anyString())).thenReturn(true);
		when(tokenService.getUserNameFromJwtToken(anyString())).thenReturn(username);
		when(tokenService.generateAccessToken(anyString())).thenReturn(accessToken);
		when(tokenService.generateRefreshTokenFromUsername(anyString())).thenReturn(newRefreshToken);

		RefreshResponse refreshResponse = underTest.refreshTheToken(refreshToken);

		assertEquals(accessToken, refreshResponse.getAccessToken());
		assertEquals(newRefreshToken, refreshResponse.getRefreshToken());
	}

	/**
	 * Refresh the token 1.
	 */
	@Test
	@DisplayName("Throws TokenRefreshException when receiving an invalid RefreshToken")
	void refreshTheToken1() {
		when(tokenService.validateJwtToken(anyString())).thenReturn(false);

		var exception = assertThrows(RuntimeException.class, () -> underTest.refreshTheToken("refreshToken"));
		// exception encapsulada pelo TokenRefreshException
		assertEquals("Token expirado", exception.getMessage());
	}

}
