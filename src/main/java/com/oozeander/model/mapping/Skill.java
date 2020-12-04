package com.oozeander.model.mapping;

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
@EqualsAndHashCode(exclude = "student")
public class Skill {
	private Long id;
	@NonNull
	private String name;
	@NonNull
	private Integer mark;

	// Many To One
	private Student student;
}