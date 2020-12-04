package com.oozeander.model.lob;

import java.sql.Blob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Avatar {
	@NonNull
	private String fileName;
	@NonNull
	private String fileType;
	private String fileSize;
	private Blob fileData;
}