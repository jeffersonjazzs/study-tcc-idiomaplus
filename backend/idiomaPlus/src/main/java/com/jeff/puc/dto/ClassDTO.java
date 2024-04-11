/*
 * 
 */
package com.jeff.puc.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.jeff.puc.domain.Class;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class ClassDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassDTO {
	
	/** The id. */
	private Long id;
	
	/** The teacher. */
	private TeacherDTO teacher;
	
	/** The students. */
	private Set<StudentDTO> students;
	
	/** The grade. */
	private int grade;

	/**
	 * Instantiates a new class DTO.
	 *
	 * @param model the model
	 */
	public ClassDTO(Class model) {
		this.id = model.getId();
		this.teacher = new TeacherDTO(model.getTeacher());
		this.students = model.getStudents().stream().map(StudentDTO::new).collect(Collectors.toSet());
		this.grade = model.getGrade();
	}
}
