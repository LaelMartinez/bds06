package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.DTO.ReviewDTO;
import com.devsuperior.movieflix.DTO.ReviewUserDTO;
import com.devsuperior.movieflix.DTO.UserDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;

@Service
public class ReviewService{

	@Autowired
	private ReviewRepository repository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MovieRepository movieRepository;

	
	@Autowired
	public List<ReviewDTO> findAll(){
		List<Review> list = repository.findAll();
		return list.stream().map(x -> new ReviewDTO(x) ).collect(Collectors.toList());
	}	

	@Transactional
	public ReviewUserDTO insert(ReviewDTO dto) {
		Review entity = new Review();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		
		
		return new ReviewUserDTO(entity, new UserDTO(entity.getUser()));
	}
	
	private void copyDtoToEntity(ReviewDTO dto, Review entity) {
		entity.setId(dto.getId());
		entity.setText(dto.getText());
		Movie movie = movieRepository.getOne(dto.getMovieId());
		User user = userRepository.getOne(dto.getUserId());
		entity.setMovie(movie);
		entity.setUser(user);
	}	
	
	
}
