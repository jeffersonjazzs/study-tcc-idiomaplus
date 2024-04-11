/*
 * 
 */
package com.jeff.puc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jeff.puc.Builder;
import com.jeff.puc.domain.Class;
import com.jeff.puc.dto.ClassDTO;
import com.jeff.puc.repositories.ClassRepository;
import com.jeff.puc.services.impl.ClassService;

// TODO: Auto-generated Javadoc
/**
 * The Class ClassServiceTest.
 */
@SpringBootTest
public class ClassServiceTest {
	
	/** The class repository. */
	@Mock
	ClassRepository classRepository;
	
	/** The under test. */
	@InjectMocks
	ClassService underTest;

	/**
	 * Register 0.
	 */
	@Test
	@DisplayName("Should return ClassDTO when receives ClassDTO")
	void register0() {
		Class expected = Builder.class1();

		when(classRepository.save(any(Class.class))).thenReturn(expected);

		ClassDTO response = underTest.insert(new ClassDTO(expected));

		assertEquals(expected.getId(), response.getId());
	}

	/**
	 * Update 0.
	 */
	@Test
	@DisplayName("Throws RuntimeException when updating an object that doesn't exist")
	void update0() {
		when(classRepository.findById(anyLong())).thenReturn(Optional.empty());

		var exception = assertThrows(RuntimeException.class,
				() -> underTest.update(Builder.teacherDTO1().getId(), Builder.classDTO1()));

		String expectedMessage = "not found";
		assertEquals(expectedMessage, exception.getMessage());
	}

	/**
	 * Update 1.
	 */
	@Test
	@DisplayName("Should return ClassDTO when updating valid ClassDTO")
	void update1() {
		Class expected = Builder.class1();
		when(classRepository.findById(anyLong())).thenReturn(Optional.of(expected));

		when(classRepository.save(any(Class.class))).thenReturn(expected);

		ClassDTO argument = Builder.classDTO1();
		ClassDTO response = underTest.update(argument.getId(), argument);

	}

	/**
	 * Find by id 0.
	 */
	@Test
	@DisplayName("Should return ClassDTO when receiving a valid id")
	void findById0() {
		ClassDTO argument = Builder.classDTO1();
		when(classRepository.findById(anyLong())).thenReturn(Optional.of(new Class(argument)));
		ClassDTO response = underTest.findById(1L);

		assertNotNull(response);
		assertEquals(argument, response);
		assertEquals(argument.getId(), response.getId());
	}

	/**
	 * Find by id 1.
	 */
	@Test
	@DisplayName("Throws RuntimeException when id doesn't exist")
	void findById1() {
		when(classRepository.findById(anyLong())).thenReturn(Optional.empty());
		var exception = assertThrows(RuntimeException.class, () -> underTest.findById(1L));

		String expectedMessage = "not found";
		assertEquals(expectedMessage, exception.getMessage(), exception.getMessage());
	}

	/**
	 * Find all 0.
	 */
	@Test
	@DisplayName("Should return Page<ClassDTO> when receives Pageable")
	void findAll0() {
		List<Class> list = List.of(Builder.class1(), Builder.class1(), Builder.class1());
		Page<Class> page = new PageImpl<>(list);
		when(classRepository.findAll(any(Pageable.class))).thenReturn(page);

		Page<ClassDTO> response = underTest.findAll(Pageable.unpaged());

		assertNotNull(response.getContent());
		assertEquals(page.getTotalElements(), response.getTotalElements());
		assertEquals(page.getContent().get(0).getId(), response.getContent().get(0).getId());
	}

	/**
	 * Delete 0.
	 */
	@Test
	@DisplayName("Throws PersonException when deleting id that doesn't exist")
	void delete0() {
		when(classRepository.findById(anyLong())).thenReturn(Optional.empty());

		var exception = assertThrows(RuntimeException.class, () -> underTest.delete(1L));

		String expectedMessage = "not found";
		assertEquals(expectedMessage, exception.getMessage());
	}

	/**
	 * Delete 1.
	 */
	@Test
	@DisplayName("Should update Class with excluded true when receives id that exists")
	void delete1() {
		Class argument = Builder.class1();
		when(classRepository.findById(anyLong())).thenReturn(Optional.of(argument));

		Assertions.assertThatCode(() -> underTest.delete(1L)).doesNotThrowAnyException();
	}

}
