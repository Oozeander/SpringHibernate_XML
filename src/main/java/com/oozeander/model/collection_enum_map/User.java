package com.oozeander.model.collection_enum_map;

import java.util.List;
import java.util.Map;

import com.oozeander.model.mapping.Skill;

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
@EqualsAndHashCode(exclude = { "skills" })
public class User {
	private Long id;
	@NonNull
	private List<String> emails;
	@NonNull
	private List<Skill> skills;
	@NonNull
	private Gender gender;
	@NonNull
	private Map<String, Integer> skillAndMark;
}