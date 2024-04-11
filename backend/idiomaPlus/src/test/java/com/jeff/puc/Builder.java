/*
 * 
 */
package com.jeff.puc;

import java.util.Set;

import com.jeff.puc.domain.Class;
import com.jeff.puc.domain.Student;
import com.jeff.puc.domain.Teacher;
import com.jeff.puc.dto.ClassDTO;
import com.jeff.puc.dto.StudentDTO;
import com.jeff.puc.dto.TeacherDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class Builder.
 */
public class Builder {
	
	/**
	 * Teacher 1.
	 *
	 * @return the teacher
	 */
	public static Teacher teacher1() {
		Teacher model = new Teacher();

		model.setId(1l);
		model.setName("Teacher");
		model.setEmail("email@hotmail.com");
		model.setPhone("1185632568");
		model.setSalary(3000.0);

		return model;
	}

	/**
	 * Teacher DTO 1.
	 *
	 * @return the teacher DTO
	 */
	public static TeacherDTO teacherDTO1() {
		TeacherDTO model = new TeacherDTO();

		model.setId(1l);
		model.setName("Teacher");
		model.setEmail("email@hotmail.com");
		model.setPhone("1199887766");
		model.setSalary(3000.0);

		return model;
	}

	/**
	 * Student 1.
	 *
	 * @return the student
	 */
	public static Student student1() {
		Student model = new Student();

		model.setId(1l);
		model.setName("Teacher");
		model.setEmail("email@hotmail.com");
		model.setPhone("1166554433");

		return model;
	}

	/**
	 * Student DTO 1.
	 *
	 * @return the student DTO
	 */
	public static StudentDTO studentDTO1() {
		StudentDTO model = new StudentDTO();

		model.setId(1l);
		model.setName("Teacher");
		model.setEmail("email@hotmail.com");
		model.setPhone("1188996633");

		return model;
	}

	/**
	 * Class 1.
	 *
	 * @return the class
	 */
	public static Class class1() {
		Class model = new Class();

		model.setId(1l);
		model.setGrade(1);
		model.setTeacher(Builder.teacher1());
		model.setStudents(Set.of(Builder.student1()));

		return model;
	}

	/**
	 * Class DTO 1.
	 *
	 * @return the class DTO
	 */
	public static ClassDTO classDTO1() {
		ClassDTO dto = new ClassDTO();

		dto.setId(1l);
		dto.setGrade(1);
		dto.setTeacher(Builder.teacherDTO1());
		dto.setStudents(Set.of(Builder.studentDTO1()));

		return dto;
	}

}
