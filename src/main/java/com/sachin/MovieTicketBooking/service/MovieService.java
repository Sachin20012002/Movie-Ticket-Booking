package com.sachin.MovieTicketBooking.service;

import com.sachin.MovieTicketBooking.entity.Movie;
import com.sachin.MovieTicketBooking.error.MovieNotFoundException;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies();

    Movie addMovie(Movie movie);

    Movie getMovie(Long id) throws MovieNotFoundException;

    void deleteMovie(Long id) throws MovieNotFoundException;

    Movie updateMovie(Long id, Movie movie) throws MovieNotFoundException;
}
