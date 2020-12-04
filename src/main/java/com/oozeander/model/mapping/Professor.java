package com.oozeander.model.mapping;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = { "students" })
public class Professor {
	private Long id;
	@NonNull
	private String course;

	// Many to Many
	private Set<Student> students;
}