/*
 * 
 */
package com.jeff.puc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class SecurityConfig.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, order = Ordered.HIGHEST_PRECEDENCE)
public class SecurityConfig {
	
	/** The auth token filter. */
	private final AuthTokenFilter authTokenFilter;
	
	/** The urls frotend. */
	@Value("${personal.security.url-cors}")
	private List<String> URLS_FROTEND;

	/**
	 * Instantiates a new security config.
	 *
	 * @param authTokenFilter the auth token filter
	 */
	public SecurityConfig(AuthTokenFilter authTokenFilter) {
		this.authTokenFilter = authTokenFilter;
	}

	/**
	 * Authentication manager.
	 *
	 * @param authenticationConfiguration the authentication configuration
	 * @return the authentication manager
	 * @throws Exception the exception
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	/**
	 * Authentication jwt token filter.
	 *
	 * @return the auth token filter
	 */
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return this.authTokenFilter;
	}

	/**
	 * Password encoder.
	 *
	 * @return the password encoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new Argon2PasswordEncoder();
	}

	/** The Constant PUBLIC_MATCHERS. */
	private static final String[] PUBLIC_MATCHERS = { "/auth/**", "/v3/**", "/class/**", "/user/userName/**",
			"/contacts/**", "/swagger-ui/**", "/user/trocarSenha/**", };

	/**
	 * Filter chain.
	 *
	 * @param http the http
	 * @return the security filter chain
	 * @throws Exception the exception
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable(); // Desativa opções de frame para prevenir ataques de clickjacking.
		http.cors().and().csrf().disable(); // Desativa a proteção CSRF e habilita a configuração CORS.
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Define a política de criação
																							// de sessão como sem
																							// estado.

		http.authorizeHttpRequests(auth -> auth.antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated()); // Configura
																														// as
																														// regras
																														// de
																														// autorização.
																														// Determinadas
																														// URLs
																														// são
																														// permitidas
																														// a
																														// todos,
																														// enquanto
																														// outras
																														// requerem
																														// autenticação.

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class); // Adiciona um
																											// filtro
																											// personalizado
																											// para
																											// processar
																											// tokens de
																											// autenticação
																											// JWT antes
																											// do filtro
																											// padrão de
																											// autenticação
																											// de nome
																											// de
																											// usuário e
																											// senha.

		return http.build(); // Constrói e retorna a cadeia de filtros de segurança configurada.
	}

	/**
	 * Cors configuration source.
	 *
	 * @return the cors configuration source
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		System.out.println(this.URLS_FROTEND);
		CorsConfiguration configuration = new CorsConfiguration();
		// configuration.setAllowedOrigins(this.URLS_FROTEND);
		configuration.setAllowedOrigins(Arrays.asList("*")); // Permite qualquer origem

		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "PATCH", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
		configuration.setExposedHeaders(List.of("x-auth-token"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
