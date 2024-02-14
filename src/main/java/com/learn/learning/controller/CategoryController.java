package com.learn.learning.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.learning.dto.CategoryDto;
import com.learn.learning.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	private final CategoryService categoryService;
	
	@Autowired
	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	
	
	@PostMapping("/create")
	public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		return categoryService.addCategoryEntity(categoryDto);
	}
	@GetMapping("/all")
	public ResponseEntity<?> getAllCategory(){
		  return categoryService.getCategory();
	   }
}
