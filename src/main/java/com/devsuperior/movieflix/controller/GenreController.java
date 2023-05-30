package com.devsuperior.movieflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.DTO.GenreDTO;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.services.AuthService;
import com.devsuperior.movieflix.services.GenreService;


@RestController
@RequestMapping(value = "/genres")
public class GenreController {

	@Autowired
	private GenreService service;

	@Autowired
	private AuthService authService;
	
	
	@GetMapping
	public ResponseEntity<List<GenreDTO>> findAll() {
		
		User user = authService.authenticated();
		authService.validateSelfOrMemberAndVisitor(user.getId());
		
		List<GenreDTO> list = service.findAll();		
		return ResponseEntity.ok().body(list);
	}	

}

