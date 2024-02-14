package com.learn.learning.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.learning.entity.CategoryEntity;

public interface CategoryRepository  extends JpaRepository<CategoryEntity, Long> {
	Optional<CategoryEntity> findByCategoryType(String category);
}
