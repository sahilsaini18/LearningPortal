package com.learn.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.learning.entity.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, Long > {

}
