package com.oozeander.model.embedded;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GamerId implements Serializable {
	private static final long serialVersionUID = 8690286444691299302L;
	private String id, username, password;
}