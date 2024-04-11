/*
 * 
 */
package com.jeff.puc.dto;

import java.time.LocalDateTime;

import com.jeff.puc.domain.Attendance;
import com.jeff.puc.domain.Lesson;
import com.jeff.puc.domain.Student;
import com.jeff.puc.domain.enums.AttendanceStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class AttendanceDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDTO {

	/** The id. */
	private Long id;

	/** The date time. */
	private LocalDateTime dateTime;

	/** The status. */
	private AttendanceStatus status;

	/** The student. */
	private Student student;

	/** The lesson. */
	private Lesson lesson;

	/**
	 * Instantiates a new attendance DTO.
	 *
	 * @param model the model
	 */
	public AttendanceDTO(Attendance model) {
		this.id = model.getId();
		this.dateTime = model.getDateTime();
		this.status = model.getStatus();
		this.student = model.getStudent();
		this.lesson = model.getLesson();

	}
}
