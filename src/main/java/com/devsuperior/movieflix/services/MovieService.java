package com.devsuperior.movieflix.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.DTO.GenreDTO;
import com.devsuperior.movieflix.DTO.MovieDTO;
import com.devsuperior.movieflix.DTO.MovieSimpleDTO;
import com.devsuperior.movieflix.DTO.ReviewUserDTO;
import com.devsuperior.movieflix.DTO.UserDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService{

	@Autowired
	private MovieRepository repository;
	
	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	@Transactional(readOnly = true)
	public Page<MovieSimpleDTO> findAllPaged(Pageable pageable) {
		Page<Movie> page = repository.findAll(pageable);
		return page.map(x -> new MovieSimpleDTO(x));
	}
	
	
	@Transactional(readOnly = true)
	public Page<MovieSimpleDTO> findByGenre(Long genreId, Pageable pageable){
		Genre genre = ( genreId == 0 ) ? null : genreRepository.getOne(genreId); 
		Page<Movie> page = repository.find(genre, pageable);
		return page.map(x -> new MovieSimpleDTO(x));
	}	


	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		Optional<Movie> obj = repository.findById(id);
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new MovieDTO(entity, new GenreDTO(entity.getGenre()));
	}

	@Transactional(readOnly = true)
	public List<ReviewUserDTO> findReviews(Long movieId){
		Movie movie = ( movieId == 0 ) ? null : repository.getOne(movieId); 
		List<Review> list = reviewRepository.find(movie);
		List<ReviewUserDTO> listDTO = new ArrayList<>(); 
		
		for (Review l: list) {
			ReviewUserDTO reviewUserDTO = new ReviewUserDTO( l, new UserDTO( l.getUser() ) );	
            listDTO.add(reviewUserDTO);			
		}
		
		return listDTO;
	}
	
}
