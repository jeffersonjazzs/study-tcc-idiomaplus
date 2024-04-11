/*
 * 
 */
package com.jeff.puc.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.jeff.puc.domain.enums.AttendanceStatus;
import com.jeff.puc.dto.AttendanceDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * The Class Attendance.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The date time. */
	private LocalDateTime dateTime;

	/** The status. */
	@Enumerated(EnumType.STRING)
	private AttendanceStatus status; // Enum: PRESENT, ABSENT, LATE

	/** The student. */
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	/** The lesson. */
	@ManyToOne
	@JoinColumn(name = "lesson_id")
	private Lesson lesson;

	/**
	 * Instantiates a new attendance.
	 *
	 * @param attendanceDTO the attendance DTO
	 */
	public Attendance(AttendanceDTO attendanceDTO) {

		this.id = attendanceDTO.getId();
		this.dateTime = attendanceDTO.getDateTime();
		this.status = attendanceDTO.getStatus();
		this.student = attendanceDTO.getStudent();
		this.lesson = attendanceDTO.getLesson();
	}

}
