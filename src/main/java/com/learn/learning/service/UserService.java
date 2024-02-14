package com.learn.learning.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.learn.learning.dto.LoginUserDto;
import com.learn.learning.dto.RegisterUserDto;
import com.learn.learning.entity.RoleEntity;
import com.learn.learning.entity.UserEntity;
import com.learn.learning.mapper.UserMapper;
import com.learn.learning.repository.RoleRepository;
import com.learn.learning.repository.UserRepository;

@Service
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	private static final String MESSAGE_KEY = "message";
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserMapper userMapper;
	
	public ResponseEntity<?> registerUser(RegisterUserDto registerUser){
		Map<String,String> response= new HashMap<>();
		Optional<RoleEntity> defaultRole = roleRepository.findByRoleType("LEARNER");
		
		if(defaultRole.isPresent()) {
		HashSet<RoleEntity> roles = new HashSet<>();
		roles.add(defaultRole.get());
		registerUser.setRoles(roles);
		
		UserEntity newUser= userMapper.registerUserDtoToUserEntity(registerUser);
		
		try {
			userRepository.save(newUser);
			response.put(MESSAGE_KEY, "User registered");
			logger.info("user registered: {}", newUser.getEmail());
			return new ResponseEntity<>(response,HttpStatus.CREATED);
		} catch(Exception e) {
			logger.error("Failed to register the user",e);
			response.put("Error", "user already exist in database");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		}
		else {
			response.put("Error","Default role not found");
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public ResponseEntity<?> loginUser(LoginUserDto loginUser){
		HashMap<String,String> response = new HashMap<>();
		
		try {
				UserEntity user = userRepository.findByEmail(loginUser.getEmail());
				RegisterUserDto userDetails = userMapper.userEntityToRegisterUserDto(user);
				
				if(userDetails.getPassword().equals(loginUser.getPassword())) {
					response.put(MESSAGE_KEY,"User logged in Successfully");
					logger.info("User logged in: {}", loginUser.getEmail());
					return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
					
				}
				else {
					response.put(MESSAGE_KEY, "user authentication failed");
					logger.warn("user authentication failed for this email: {}", loginUser.getEmail());
					return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
				}
				} catch(Exception e) {
					response.put(MESSAGE_KEY,"Authentication Error");
					logger.error("Erroe occurred during authentication",e);
					return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
				}
		
	}

}
