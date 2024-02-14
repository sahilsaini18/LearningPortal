package com.learn.learning.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learn.learning.dto.CategoryDto;
import com.learn.learning.entity.CategoryEntity;
import com.learn.learning.mapper.CourseMapper;
import com.learn.learning.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);
	private static final String MESSAGE_KEY = "message";
	
	@Autowired
	private CourseMapper courseMapper;
	
	public ResponseEntity<?> addCategoryEntity(CategoryDto categoryDto){
		Map<String,String> response = new HashMap<>();
		
		try {
			CategoryEntity toAdd = courseMapper.categoryDtoToCategoryEntity(categoryDto);
			
			if(toAdd == null) {
				response.put(MESSAGE_KEY, "Error found");
				logger.error("Failed to add category. Received null category object from mapper");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			categoryRepository.save(toAdd);
			response.put(MESSAGE_KEY,"category is added");
			logger.info("Category is added:{}",toAdd);
			return new ResponseEntity<>(response,HttpStatus.CREATED);
		} catch(Exception e) {
			response.put(MESSAGE_KEY, "Category already exists");
			logger.error("failed to add category. it already exist",e);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	public ResponseEntity<?> getCategory(){
		Map<String,List<CategoryEntity>> response = new HashMap<>();
		response.put(MESSAGE_KEY, categoryRepository.findAll());
		return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
	}
}
