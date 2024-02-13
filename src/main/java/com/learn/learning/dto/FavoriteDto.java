package com.learn.learning.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FavoriteDto {

	@JsonProperty("email")
	private String email;
	
	@JsonProperty("course_id")
	private Long courseId;
}
