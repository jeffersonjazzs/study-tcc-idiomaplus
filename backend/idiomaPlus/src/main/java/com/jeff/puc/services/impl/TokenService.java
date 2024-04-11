/*
 * 
 */
package com.jeff.puc.services.impl;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class TokenService.
 */
@Component
public class TokenService {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

	/** The jwt secret. */
	@Value("${personal.security.jwtSecret}")
	private String jwtSecret;

	/** The jwt expiration ms. */
	@Value("${personal.security.jwtExpirationMs}")
	private int jwtExpirationMs;

	/** The refresh token duration ms. */
	@Value("${personal.security.jwtRefreshExpirationMs}")
	private Long refreshTokenDurationMs;

	/** The password to encript. */
	@Value("${personal.security.passwordGenerate}")
	private String passwordToEncript;

	/**
	 * Generate access token.
	 *
	 * @param userPrincipal the user principal
	 * @return the string
	 */
	public String generateAccessToken(UserDetails userPrincipal) {
		return generateTokenFromUsername(userPrincipal.getUsername());
	}

	/**
	 * Generate access token.
	 *
	 * @param username the username
	 * @return the string
	 */
	public String generateAccessToken(String username) {
		return generateTokenFromUsername(username);
	}

	/**
	 * Generate token from username.
	 *
	 * @param username the username
	 * @return the string
	 */
	public String generateTokenFromUsername(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	/**
	 * Generate refresh token from username.
	 *
	 * @param username the username
	 * @return the string
	 */
	public String generateRefreshTokenFromUsername(String username) {
		Date momentoAtual = new Date();
		return Jwts.builder().setSubject(username).setIssuedAt(momentoAtual)
				.setExpiration(new Date(momentoAtual.getTime() + refreshTokenDurationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	/**
	 * Gets the user name from jwt token.
	 *
	 * @param token the token
	 * @return the user name from jwt token
	 */
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	/**
	 * Validate jwt token.
	 *
	 * @param authToken the auth token
	 * @return true, if successful
	 */
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}

	/**
	 * Gerador de senha.
	 */
	@Bean()
	public void geradorDeSenha() {
		Argon2PasswordEncoder argon = new Argon2PasswordEncoder();
		String encripted = argon.encode(this.passwordToEncript);
	}

}
