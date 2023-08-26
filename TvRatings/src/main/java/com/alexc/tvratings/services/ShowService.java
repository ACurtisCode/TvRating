package com.alexc.tvratings.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.alexc.tvratings.models.Show;
import com.alexc.tvratings.repositories.ShowRepository;

@Service
public class ShowService {
	@Autowired
	ShowRepository showRepo;

	public List<Show> getAll() {
		return showRepo.findAll();
	}

	public Show findShow(Long id) {
		return showRepo.findShowById(id).get();
	}
	public Show findShowByTitle(String title) {
		if (showRepo.findByTitle(title).isEmpty()) {
			return null;
		} else {
			return showRepo.findByTitle(title).get();
		}
	}
	public Show createShow(Show show, BindingResult result) {
			return showRepo.save(show);
	}

	public Show updateShow(Show show) {
		return showRepo.save(show);
	}

	public void deleteShow(Show show) {
		showRepo.delete(show);
	}

}
