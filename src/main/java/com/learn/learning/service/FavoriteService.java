package com.learn.learning.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learn.learning.dto.FavoriteDto;
import com.learn.learning.entity.CourseEntity;
import com.learn.learning.entity.UserEntity;
import com.learn.learning.repository.CourseRepository;
import com.learn.learning.repository.UserRepository;

@Service
public class FavoriteService {

		private static final Logger logger = LoggerFactory.getLogger(FavoriteService.class);
	    private static final String MESSAGE_KEY = "message";
		
		@Autowired
		private CourseRepository courseRepository;
		
		@Autowired
		private UserRepository userRepository;
		
		public ResponseEntity<Map<String,String>>selectFavorite(FavoriteDto favoriteDto){
		Map<String,String> response = new HashMap<>();
		
		Optional<CourseEntity> courseObj = courseRepository.findById(favoriteDto.getCourseId());
		String email = favoriteDto.getEmail();
		UserEntity userEntity= userRepository.findByEmail(email);
		
		if(userEntity == null || courseObj.isEmpty()) {
			response.put(MESSAGE_KEY,"Invalid error");
			logger.error("failed to add to fav");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		Set<CourseEntity> favorite= userEntity.getFavourites();
		favorite.add(courseObj.get());
		userEntity.setFavourites(favorite);
		userRepository.save(userEntity);
		
		response.put(MESSAGE_KEY,"course added");
		logger.info("course added CourseId:{}, UserEntity:{}",favoriteDto.getCourseId(),email);
		return new ResponseEntity<>(response,HttpStatus.OK);
}
		public ResponseEntity<Map<String,String>> removeFav(FavoriteDto favoriteDto){
			Map<String,String> response = new HashMap<>();
			
			Optional<CourseEntity> course = courseRepository.findById(favoriteDto.getCourseId());
			String email = favoriteDto.getEmail();
			UserEntity user = userRepository.findByEmail(email);
			
			if(user==null || course.isEmpty()) {
				response.put(MESSAGE_KEY,"something went wrong");
				logger.info("course added to fav CourseId: {}, User:{}",favoriteDto.getCourseId(), email);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
				
			}
			Set<CourseEntity> favorite  = user.getFavourites();
			
			try {
				favorite.remove(course.get());
				logger.info("course removed");
			}catch(Exception e) {
				logger.error("failed to remove from fav");
				response.put("Error", e.toString());
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			
			user.setFavourites(favorite);
			userRepository.save(user);
			
			response.put(MESSAGE_KEY, "Course removed from fav");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
}
