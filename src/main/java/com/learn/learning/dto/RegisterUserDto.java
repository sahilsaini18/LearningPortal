package com.learn.learning.dto;

import java.util.Set;

import com.learn.learning.entity.CourseEntity;
import com.learn.learning.entity.RoleEntity;

import lombok.Data;

@Data
public class RegisterUserDto {
	
	private String name;

	private String email;
	
	private String password;
	
	private Set<RoleEntity>roles;
	
	private Set<CourseEntity> courses;
	

}
