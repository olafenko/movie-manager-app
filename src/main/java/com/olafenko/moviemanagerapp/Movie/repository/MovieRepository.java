package com.olafenko.moviemanagerapp.Movie.repository;


import com.olafenko.moviemanagerapp.Movie.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {


    Optional<Movie> findMovieById(Long id);



}
