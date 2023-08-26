package com.alexc.tvratings.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name="ratings")
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DecimalMax(value="5.0", inclusive=true, message="rating must be 5 or below")
	@DecimalMin(value="1.0", inclusive=true, message="rating must be 1 or higher")
	private float showRating;
	
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User reviewCreator;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="show_id")
	private Show ratedShow;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getShowRating() {
		return showRating;
	}

	public void setShowRating(float showRating) {
		this.showRating = showRating;
	}

	public User getReviewCreator() {
		return reviewCreator;
	}

	public void setReviewCreator(User reviewCreator) {
		this.reviewCreator = reviewCreator;
	}

	public Show getRatedShow() {
		return ratedShow;
	}

	public void setRatedShow(Show ratedShow) {
		this.ratedShow = ratedShow;
	}
	
}
