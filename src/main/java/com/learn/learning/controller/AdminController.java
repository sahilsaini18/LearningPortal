package com.learn.learning.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.learning.dto.LoginUserDto;
import com.learn.learning.dto.RegisterUserDto;
import com.learn.learning.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	private final UserService userService;
	
	@Autowired
	public AdminController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody RegisterUserDto registerUserDto){
		return userService.registerUser(registerUserDto);
	}
	

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginUserDto loginUserDto){
		return userService.loginUser(loginUserDto);
	}
}
