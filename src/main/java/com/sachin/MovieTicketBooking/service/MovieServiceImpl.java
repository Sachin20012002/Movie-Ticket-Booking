package com.sachin.MovieTicketBooking.service;

import com.sachin.MovieTicketBooking.entity.Movie;
import com.sachin.MovieTicketBooking.error.MovieNotFoundException;
import com.sachin.MovieTicketBooking.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MovieServiceImpl implements MovieService{
    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository){
        this.movieRepository=movieRepository;
    }
    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie getMovie(Long id) throws MovieNotFoundException {
        return movieRepository.findById(id).orElseThrow(()-> new MovieNotFoundException("Movie Not Found"));
    }

    @Override
    public void deleteMovie(Long id) throws MovieNotFoundException {
        if(movieRepository.findById(id).isEmpty()){
            throw new MovieNotFoundException("Movie does not exist to Delete");
        }
        movieRepository.deleteById(id);
    }

    @Override
    public Movie updateMovie(Long id, Movie movie) throws MovieNotFoundException {
        if(movieRepository.findById(id).isEmpty()){
            throw new MovieNotFoundException("Enter a valid Movie Id to Update");
        }
        Movie existingMovie=movieRepository.findById(id).get();
        if(Objects.nonNull(movie.getName()) && !"".equalsIgnoreCase(movie.getName())){
            existingMovie.setName(movie.getName());
        }
        if(Objects.nonNull(movie.getAvailableSeats())){
            existingMovie.setAvailableSeats(movie.getAvailableSeats());
        }
        if(Objects.nonNull(movie.getTotalSeats())){
            existingMovie.setTotalSeats(movie.getTotalSeats());
        }
        return movieRepository.save(existingMovie);
    }

}
