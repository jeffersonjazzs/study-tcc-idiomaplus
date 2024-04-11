/*
 * 
 */
package com.jeff.puc.dto;

import java.time.LocalDateTime;

import com.jeff.puc.domain.Lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class LessonDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonDTO {

	/** The id. */
	private Long id;
	
	/** The date time. */
	private LocalDateTime dateTime;
	
	/** The content. */
	private String content;

	/**
	 * Instantiates a new lesson DTO.
	 *
	 * @param model the model
	 */
	public LessonDTO(Lesson model) {
		this.id = model.getId();
		this.dateTime = model.getDateTime();
		this.content = model.getContent();
	}

}
