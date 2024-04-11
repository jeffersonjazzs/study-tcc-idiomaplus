/*
 * 
 */
package com.jeff.puc.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.jeff.puc.dto.ClassLessonDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * The Class ClassLesson.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassLesson {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** The title. */
	private String title;
	
	/** The start time. */
	private LocalDateTime startTime;
	
	/** The end time. */
	private LocalDateTime endTime;
	
	/** The description. */
	private String description;

	/** The a class. */
	// Relacionamento com a classe
	@ManyToOne
	@JoinColumn(name = "class_id")
	private Class aClass;

	/**
	 * Instantiates a new class lesson.
	 *
	 * @param dto the dto
	 */
	public ClassLesson(ClassLessonDTO dto) {
		this.id = dto.getId();
		this.title = dto.getTitle();
		this.startTime = dto.getStartTime();
		this.endTime = dto.getEndTime();
		this.description = dto.getDescription();

	}

}