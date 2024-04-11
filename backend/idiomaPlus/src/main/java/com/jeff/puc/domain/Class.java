/*
 * 
 */
package com.jeff.puc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import com.jeff.puc.dto.ClassDTO;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

// TODO: Auto-generated Javadoc
/**
 * The Class Class.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Class {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** The teacher. */
	@OneToOne
	@Cascade(org.hibernate.annotations.CascadeType.REFRESH)
	private Teacher teacher;
	
	/** The students. */
	@OneToMany
	@Cascade(org.hibernate.annotations.CascadeType.REFRESH)
	private Set<Student> students;
	
	/** The grade. */
	private int grade;

	/**
	 * Instantiates a new class.
	 *
	 * @param dto the dto
	 */
	public Class(ClassDTO dto) {
		this.id = dto.getId();
		this.teacher = new Teacher(dto.getTeacher());
		this.students = dto.getStudents().stream().map(Student::new).collect(Collectors.toSet());
		this.grade = dto.getGrade();
	}

}
