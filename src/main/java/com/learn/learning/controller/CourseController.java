package com.learn.learning.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.learning.dto.CourseDto;
import com.learn.learning.dto.GetCourseDto;
import com.learn.learning.entity.CourseCategory;
import com.learn.learning.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createCourse(@Valid @RequestBody CourseDto courseDto){
		return courseService.addCourse(courseDto);
	}
	
	@PostMapping("/view")
	public ResponseEntity<?> viewCourse(@Valid @RequestBody GetCourseDto getCourseDto){
		Optional<List<CourseCategory>> response= courseService.getCourse(getCourseDto);
		
		if(response.isEmpty()) {
			return new ResponseEntity<>(new HashMap<>().put("Message","category doesnt exist"), HttpStatus.BAD_REQUEST);
			
		}
		return new ResponseEntity<>(response.get(),HttpStatus.OK);
	}
	@PostMapping("/view/{id}")
	public ResponseEntity<?> viewCourseById(@Valid @PathVariable Long id, @RequestBody GetCourseDto getCourseDto){
		return courseService.getCourseId(id, getCourseDto);
	}

}
