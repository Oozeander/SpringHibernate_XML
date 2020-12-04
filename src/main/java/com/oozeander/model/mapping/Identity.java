package com.oozeander.model.mapping;

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
@EqualsAndHashCode(exclude = { "student" })
public class Identity {
	private Long id;
	@NonNull
	private String firstname, lastname;
	@NonNull
	private Integer age;

	// One To One
	@ToString.Exclude
	private Student student;
}