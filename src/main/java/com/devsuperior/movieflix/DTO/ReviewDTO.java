package com.devsuperior.movieflix.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;

public class ReviewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank(message = "Campo requerido")
	private String text;
	private User user;
	private Long userId;
	private Long movieId;
	
	
	public ReviewDTO() {
		
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


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Long getMovieId() {
		return movieId;
	}


	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}


	public ReviewDTO(Long id, @NotBlank(message = "Campo requerido") String text, User user, Long userId,
			Long movieId) {
		super();
		this.id = id;
		this.text = text;
		this.user = user;
		this.userId = userId;
		this.movieId = movieId;
	}


	public ReviewDTO(Review entity) {
		this.id = entity.getId();
		this.text = entity.getText();
		this.user = entity.getUser();
		this.userId = entity.getUser().getId();
		this.movieId = entity.getMovie().getId();
	}

	
	
}
