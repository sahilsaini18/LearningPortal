package com.learn.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.learning.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long>{

	UserEntity findByEmail(String email);
}
