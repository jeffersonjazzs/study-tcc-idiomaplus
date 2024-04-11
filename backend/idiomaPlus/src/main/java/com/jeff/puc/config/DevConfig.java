/*
 * 
 */
package com.jeff.puc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Value;

import com.jeff.puc.services.impl.DBService;

// TODO: Auto-generated Javadoc
//@Configuration
/**
 * The Class DevConfig.
 */
//@Profile("dev")
public class DevConfig {

	/** The db service. */
	private DBService dbService;

	/**
	 * Instantiates a new dev config.
	 *
	 * @param dbService the db service
	 */
	public DevConfig(DBService dbService) {
		this.dbService = dbService;
	}

	/** The strategy. */
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	/**
	 * Instantiate database.
	 *
	 * @return true, if successful
	 * @throws ParseException the parse exception
	 */
	// @Bean
	public boolean instantiateDatabase() throws ParseException {

		if (!"create".equals(strategy)) {
			return false;
		}

		dbService.instantiateTestDatabase();
		return true;
	}

}