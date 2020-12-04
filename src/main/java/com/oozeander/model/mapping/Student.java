package com.oozeander.model.mapping;

import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "teachers")
public class Student {
	private Long id;

	// One To One Unidirectional
	@NonNull
	private Identity identity;
	// One To Many
	@NonNull
	@ToString.Exclude
	private List<Skill> skills;
	// Many To Many
	@NonNull
	@ToString.Exclude
	private Set<Professor> teachers;
}