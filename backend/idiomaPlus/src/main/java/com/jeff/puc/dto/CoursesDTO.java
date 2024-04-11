/*
 * 
 */
package com.jeff.puc.dto;

import com.jeff.puc.domain.Course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class CoursesDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursesDTO {

	/** The id. */
	private Long id;
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;

	/**
	 * Instantiates a new courses DTO.
	 *
	 * @param model the model
	 */
	public CoursesDTO(Course model) {
		this.id = model.getId();
		this.name = model.getName();
		this.description = model.getDescription();
	}

}
