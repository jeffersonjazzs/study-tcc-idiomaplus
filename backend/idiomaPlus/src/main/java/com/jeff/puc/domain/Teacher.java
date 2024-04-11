/*
 * 
 */
package com.jeff.puc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.jeff.puc.dto.TeacherDTO;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

// TODO: Auto-generated Javadoc
/**
 * The Class Teacher.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends Person {

	/** The salary. */
	private Double salary;
	
	/** The classe. */
	@OneToOne
	@Cascade(CascadeType.ALL)
	private Class classe;

	/**
	 * Instantiates a new teacher.
	 *
	 * @param dto the dto
	 */
	public Teacher(TeacherDTO dto) {
		super(dto.getId(), dto.getName(), dto.getPhone(), dto.getEmail(), dto.getCep(), dto.getStreet(),
				dto.getNumber(), dto.getDistrict(), dto.getCity(), dto.getState(), dto.getCountry());
		this.salary = dto.getSalary();
	}

}
