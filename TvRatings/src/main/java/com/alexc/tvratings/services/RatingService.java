package com.alexc.tvratings.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexc.tvratings.models.Rating;
import com.alexc.tvratings.repositories.RatingRepository;

@Service
public class RatingService {
	@Autowired
	RatingRepository ratingRepo;
	
	public Rating createRating (Rating rating) {
		return ratingRepo.save(rating);
	}
}
