package com.devsuperior.movieflix.DTO;

import java.io.Serializable;

import com.devsuperior.movieflix.entities.Review;

public class ReviewUserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String text;
	private Long movieId;
	private UserDTO user;
	
	
	public ReviewUserDTO() {
		
	}


	public ReviewUserDTO(Long id, String text, Long movieId, UserDTO user) {
		this.id = id;
		this.text = text;
		this.movieId = movieId;
		this.user = user;
	}

	public ReviewUserDTO(Review entity, UserDTO user) {
		id = entity.getId();
		text = entity.getText();
		movieId = entity.getMovie().getId();
		this.user = user;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public Long getMovieId() {
		return movieId;
	}


	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}


	public UserDTO getUserDTO() {
		return user;
	}


	public void setUserDTO(UserDTO user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "ReviewUserDTO [id=" + id + ", text=" + text + ", movieId=" + movieId + ", user=" + user + "]";
	}




	
	
}
