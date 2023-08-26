package com.alexc.tvratings.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alexc.tvratings.models.Show;


@Repository
public interface ShowRepository extends CrudRepository<Show, Long> {
	List<Show> findAll();
	Optional<Show> findShowById(Long id);
	Optional<Show> findByTitle(String title);
}
