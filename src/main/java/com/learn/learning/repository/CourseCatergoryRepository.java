package com.learn.learning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.learning.entity.CategoryEntity;
import com.learn.learning.entity.CourseCategory;

public interface CourseCatergoryRepository extends JpaRepository<CourseCategory, Long> {
	List<CourseCategory> findByCategoryEntity(CategoryEntity categoryEntity);
}
