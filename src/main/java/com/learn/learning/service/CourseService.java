package com.learn.learning.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learn.learning.dto.CourseDto;
import com.learn.learning.dto.GetCourseDto;
import com.learn.learning.entity.CategoryEntity;
import com.learn.learning.entity.CourseCategory;
import com.learn.learning.entity.CourseEntity;
import com.learn.learning.mapper.CourseMapper;
import com.learn.learning.repository.CategoryRepository;
import com.learn.learning.repository.CourseCatergoryRepository;
import com.learn.learning.repository.CourseRepository;

@Service
public class CourseService {
		private static final Logger logger = LoggerFactory.getLogger(CourseService.class);
		
		@Autowired
		private CourseRepository courseRepository;
		
		@Autowired
		private CategoryRepository categoryRepository;
		
		@Autowired
		private CourseCatergoryRepository courseCatergoryRepository;
		
		@Autowired
		private CourseMapper courseMapper;
		
		public ResponseEntity<?> addCourse(CourseDto courseDto){
			Map<String, String> response = new HashMap<>();
			
			Optional<CategoryEntity> categoryObject= categoryRepository.findByCategoryType(courseDto.getCategory());
			
			if(categoryObject.isEmpty()) {
				response.put("Message","Category not found");
				return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
				
			}
			
			CategoryEntity categoryEntity = categoryObject.get();
			
			CourseEntity courseEntity = courseMapper.courseDtoToCourseEntity(courseDto);
			
			CourseCategory object = new CourseCategory();
			object.setCategoryEntity(categoryEntity);
			object.setCourseEntity(courseRepository.save(courseEntity));
			courseCatergoryRepository.save(object);
			response.put("message", "Course added successfully");
			logger.info("course added:{}",courseEntity);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
			
			
		}
		
		public Optional<List<CourseCategory>> getCourse(GetCourseDto getCourseDto){
			
			
			Optional<CategoryEntity> categoryObject = categoryRepository.findByCategoryType(getCourseDto.getCategory());
			
			if(categoryObject.isEmpty()) {
				return Optional.of(null);
			}
			
			CategoryEntity categoryEntity = categoryObject.get();
			
			List<CourseCategory> coursesCategories = courseCatergoryRepository.findByCategoryEntity(categoryEntity);
			Optional<List<CourseCategory>> optionalListOptional = Optional.of(coursesCategories);
			logger.info("Retrived {} courses for category: {}",coursesCategories.size(),categoryEntity.getCategoryType());
			return optionalListOptional;
		}
		
		public ResponseEntity<?> getCourseId(Long id, GetCourseDto getCourseDto){
			Optional<List<CourseCategory>> optionalCourseCategories = getCourse(getCourseDto);
			
			if(optionalCourseCategories.isPresent()) {
			List<CourseCategory> courseCategories = optionalCourseCategories.get();
			
		
			for(CourseCategory courseEntity:courseCategories) {
				if(courseEntity.getId().equals(id)) {
					return new ResponseEntity<>(courseCategories, HttpStatus.OK);
				}
				
			}
			return new ResponseEntity<>(new HashMap<>().put("Message", "Id doesnt exist"),HttpStatus.BAD_REQUEST);
		}
		else {
			return new ResponseEntity<>(new HashMap<>().put("message", "No course data available"),HttpStatus.NOT_FOUND);
		}
}
}
