package com.learn.learning.mapper;

import org.mapstruct.Mapper;

import com.learn.learning.dto.CategoryDto;
import com.learn.learning.dto.CourseDto;
import com.learn.learning.entity.CategoryEntity;
import com.learn.learning.entity.CourseEntity;

@Mapper(componentModel = "spring")
public interface CourseMapper {
	
	CategoryEntity categoryDtoToCategoryEntity(CategoryDto categoryDto);
	
	CourseEntity courseDtoToCourseEntity(CourseDto courseDto);
	
	CategoryEntity typeToCategoryEntity(String type);
	
	CategoryDto categoryEntityToCategoryDto(CategoryEntity categoryEntity);
	
	

}
