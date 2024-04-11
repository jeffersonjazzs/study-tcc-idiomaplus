/*
 * 
 */
package com.jeff.puc.config;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeff.puc.services.impl.DBService;

// TODO: Auto-generated Javadoc
//@Configuration
/**
 * The Class TestConfig.
 */
//@Profile("test")
public class TestConfig {

	/** The db service. */
	@Autowired
	private DBService dbService;

/**
 * Instantiate database.
 *
 * @return true, if successful
 */
//    @Bean
	public boolean instantiateDatabase() {
		dbService.instantiateTestDatabase();
		return true;
	}

}
