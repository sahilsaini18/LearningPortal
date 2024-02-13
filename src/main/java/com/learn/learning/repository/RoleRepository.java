package com.learn.learning.repository;

import java.util.Optional;

import org.springframework.context.support.BeanDefinitionDsl.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.learning.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
	Optional<Role> findByRoleType(String roletype);
}
