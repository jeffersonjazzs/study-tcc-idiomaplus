/*
 * 
 */
package com.jeff.puc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jeff.puc.services.impl.TokenService;
import com.jeff.puc.services.impl.UserDetailsServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthTokenFilter.
 */
@Component
public class AuthTokenFilter extends OncePerRequestFilter {
	
	/** The token service. */
	private final TokenService tokenService;
	
	/** The user details service. */
	private final UserDetailsServiceImpl userDetailsService;

	/** The Constant loggerInstance. */
	private static final Logger loggerInstance = LoggerFactory.getLogger(AuthTokenFilter.class);

	/**
	 * Instantiates a new auth token filter.
	 *
	 * @param tokenService the token service
	 * @param userDetailsService the user details service
	 */
	public AuthTokenFilter(TokenService tokenService, UserDetailsServiceImpl userDetailsService) {
		this.tokenService = tokenService;
		this.userDetailsService = userDetailsService;
	}

	/**
	 * Do filter internal.
	 *
	 * @param request the request
	 * @param response the response
	 * @param filterChain the filter chain
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {
		try {
			String jwt = parseJwt(request);
			if (jwt != null && tokenService.validateJwtToken(jwt)) {
				String username = tokenService.getUserNameFromJwtToken(jwt);

				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			} else {
				loggerInstance.warn("JWT Token is null or invalid: " + jwt);
			}
		} catch (Exception e) {
			loggerInstance.error("Cannot set user authentication: {e}", e);
		}

		filterChain.doFilter(request, response);
	}

	/**
	 * Parses the jwt.
	 *
	 * @param request the request
	 * @return the string
	 */
	protected String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7);
		}
		return null;
	}
}
