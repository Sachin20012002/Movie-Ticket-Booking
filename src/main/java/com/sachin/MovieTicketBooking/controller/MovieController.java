package com.sachin.MovieTicketBooking.controller;

import com.sachin.MovieTicketBooking.entity.Movie;
import com.sachin.MovieTicketBooking.error.MovieNotFoundException;
import com.sachin.MovieTicketBooking.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService){
        this.movieService=movieService;
    }

    @PostMapping("movie")
    public Movie addMovie(@Valid @RequestBody Movie movie){
        return movieService.addMovie(movie);
    }

    @GetMapping("movies")
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping("movie/{id}")
    public Movie getMovie(@PathVariable("id") Long id) throws MovieNotFoundException {
        return movieService.getMovie(id);
    }

    @DeleteMapping("movie/{id}")
    public String deleteMovie(@PathVariable("id") Long id) throws MovieNotFoundException {
        movieService.deleteMovie(id);
        return "Movie deleted Successfully";
    }

    @PutMapping("movie/{id}")
    public Movie updateMovie(@PathVariable("id") Long id,@RequestBody Movie movie) throws MovieNotFoundException {
        return movieService.updateMovie(id,movie);
    }
}
