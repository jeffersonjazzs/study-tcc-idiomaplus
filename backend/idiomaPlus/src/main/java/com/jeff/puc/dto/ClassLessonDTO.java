/*
 * 
 */
package com.jeff.puc.dto;

import java.time.LocalDateTime;

import com.jeff.puc.domain.ClassLesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class ClassLessonDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassLessonDTO {

	/** The id. */
	private Long id;
	
	/** The title. */
	private String title;
	
	/** The start time. */
	private LocalDateTime startTime;
	
	/** The end time. */
	private LocalDateTime endTime;
	
	/** The description. */
	private String description;

	/**
	 * Instantiates a new class lesson DTO.
	 *
	 * @param model the model
	 */
	public ClassLessonDTO(ClassLesson model) {
		this.id = model.getId();
		this.title = model.getTitle();
		this.startTime = model.getStartTime();
		this.endTime = model.getEndTime();
		this.description = model.getDescription();
	}
}
