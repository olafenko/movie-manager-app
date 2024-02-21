package com.olafenko.moviemanagerapp.Movie.service;

import com.olafenko.moviemanagerapp.Movie.dto.AddMovieDto;
import com.olafenko.moviemanagerapp.exception.MovieAlreadyExistsException;
import com.olafenko.moviemanagerapp.exception.UserNotFoundException;
import com.olafenko.moviemanagerapp.exception.WrongMovieDescriptionException;
import com.olafenko.moviemanagerapp.exception.validation.RuleValidator;
import com.olafenko.moviemanagerapp.Movie.models.Movie;
import com.olafenko.moviemanagerapp.Movie.repository.MovieRepository;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final RuleValidator validator;

    public ResponseEntity<Movie> addMovie(AddMovieDto request){
        String code = UUID.randomUUID().toString();

        if (compareMovies(request.title())){
            throw new MovieAlreadyExistsException("Movie " + request.title() + " already exists.");
        } else if (!validator.validate(request.description())){
            throw new WrongMovieDescriptionException("Max length of description is 190 characters.");
        } else {

            Movie newMovie = new Movie(request.title(), request.description(), request.genre(), request.premiere(), request.imageUrl(), code);
            movieRepository.save(newMovie);
            return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
        }

    }

    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    public Movie updateMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public Movie findMovieById(Long id){
        return movieRepository.findMovieById(id).orElseThrow(() -> new UserNotFoundException("Movie with id" + id + " not found."));
    }

    public void deleteMovieById(Long id){
        movieRepository.deleteById(id);
    }

    private boolean compareMovies(String title){
        List<Movie> allMovies = findAllMovies();

        return allMovies.stream()
                .anyMatch(movie -> movie.getTitle().toLowerCase().replace(" ","").equals(title.toLowerCase().replace(" ","")));

    }

}
