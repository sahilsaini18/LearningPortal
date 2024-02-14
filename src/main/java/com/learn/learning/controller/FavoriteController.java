package com.learn.learning.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.learning.dto.FavoriteDto;
import com.learn.learning.service.FavoriteService;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

	@Autowired
	private FavoriteService favoriteService;
	
	@PostMapping("/make")
	public ResponseEntity<?>makeFav(@Valid @RequestBody FavoriteDto favoriteDto){
		return favoriteService.selectFavorite(favoriteDto);
	}
	
	@PostMapping("/remove")
	public ResponseEntity<?> removeFavorite(@Valid @RequestBody FavoriteDto favoriteDto){
		return favoriteService.removeFav(favoriteDto);
	}
}
