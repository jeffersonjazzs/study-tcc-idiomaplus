/*
 * 
 */
package com.jeff.puc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.jeff.puc.dto.CoursesDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * The Class Course.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;

	/**
	 * Instantiates a new course.
	 *
	 * @param dto the dto
	 */
	public Course(CoursesDTO dto) {
		this.id = dto.getId();
		this.name = dto.getName();
		this.description = dto.getDescription();
	}
}
