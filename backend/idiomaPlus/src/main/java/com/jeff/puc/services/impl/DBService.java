/*
 * 
 */
package com.jeff.puc.services.impl;

import com.jeff.puc.repositories.ClassRepository;
import com.jeff.puc.repositories.StudentRepository;
import com.jeff.puc.repositories.TeacherRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class DBService.
 */
//@Service
public class DBService {
	
	/** The class repository. */
	private final ClassRepository classRepository;
	
	/** The student repository. */
	private final StudentRepository studentRepository;
	
	/** The teacher repository. */
	private final TeacherRepository teacherRepository;

	/**
	 * Instantiates a new DB service.
	 *
	 * @param classRepository the class repository
	 * @param studentRepository the student repository
	 * @param teacherRepository the teacher repository
	 */
	public DBService(ClassRepository classRepository, StudentRepository studentRepository,
			TeacherRepository teacherRepository) {
		this.classRepository = classRepository;
		this.studentRepository = studentRepository;
		this.teacherRepository = teacherRepository;
	}

	/**
	 * Instantiate test database.
	 */
	public void instantiateTestDatabase() {

	}

}
