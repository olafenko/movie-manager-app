package com.olafenko.moviemanagerapp.Movie.controller;

import com.olafenko.moviemanagerapp.Movie.models.Movie;
import com.olafenko.moviemanagerapp.Movie.dto.AddMovieDto;
import com.olafenko.moviemanagerapp.Movie.service.MovieService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")

public class MovieController {

    private final MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies(){
        List<Movie> allMovies = movieService.findAllMovies();
        return new ResponseEntity<>(allMovies, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long id){
        Movie movieById = movieService.findMovieById(id);
        return new ResponseEntity<>(movieById, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMovie(@RequestBody AddMovieDto request){

        return movieService.addMovie(request);
    }

    @PutMapping("/update")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie){
        Movie updatedMovie = movieService.updateMovie(movie);
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable("id") Long id){
        movieService.deleteMovieById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
