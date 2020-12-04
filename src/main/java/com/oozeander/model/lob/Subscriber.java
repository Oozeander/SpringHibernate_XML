package com.oozeander.model.lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscriber {
	private Long id;
	private String firstname, lastname;
	private Avatar avatar;
}