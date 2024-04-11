/*
 * 
 */
package com.jeff.puc.dto;

import com.jeff.puc.domain.Teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class TeacherDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO extends PersonDTO {
	
	/** The salary. */
	private Double salary;

	/**
	 * Instantiates a new teacher DTO.
	 *
	 * @param id the id
	 * @param name the name
	 * @param phone the phone
	 * @param email the email
	 * @param cep the cep
	 * @param street the street
	 * @param number the number
	 * @param district the district
	 * @param city the city
	 * @param state the state
	 * @param country the country
	 * @param salary the salary
	 */
	public TeacherDTO(Long id, String name, String phone, String email, String cep, String street, Integer number,
			String district, String city, String state, String country, Double salary) {
		super(id, name, phone, email, cep, street, number, district, city, state, country);
		this.salary = salary;
	}

	/**
	 * Instantiates a new teacher DTO.
	 *
	 * @param model the model
	 */
	public TeacherDTO(Teacher model) {
		super(model.getId(), model.getName(), model.getPhone(), model.getEmail(), model.getCep(), model.getStreet(),
				model.getNumber(), model.getDistrict(), model.getCity(), model.getState(), model.getCountry());
		this.salary = model.getSalary();

	}

}
