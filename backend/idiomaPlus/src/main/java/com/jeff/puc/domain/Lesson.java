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

import com.jeff.puc.dto.LessonDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * The Class Lesson.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** The date time. */
	private LocalDateTime dateTime;
	
	/** The content. */
	private String content;

	/** The class lesson. */
	@ManyToOne
	@JoinColumn(name = "class_id")
	private ClassLesson classLesson;

	/**
	 * Instantiates a new lesson.
	 *
	 * @param dto the dto
	 */
	public Lesson(LessonDTO dto) {
		this.id = dto.getId();
		this.dateTime = dto.getDateTime();
		this.content = dto.getContent();

	}

}
