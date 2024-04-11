/*
 * 
 */
package com.jeff.puc.dto;

import com.jeff.puc.domain.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentDTO.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO extends PersonDTO {
	
	/** The fees. */
	private Double fees;

	/**
	 * Instantiates a new student DTO.
	 *
	 * @param model the model
	 */
	public StudentDTO(Student model) {
		super(model.getId(), model.getName(), model.getPhone(), model.getEmail(), model.getCep(), model.getStreet(),
				model.getNumber(), model.getDistrict(), model.getCity(), model.getState(), model.getCountry());
		this.fees = model.getFees();
	}

}
