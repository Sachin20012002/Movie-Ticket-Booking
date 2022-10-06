package com.sachin.MovieTicketBooking.service;

import com.sachin.MovieTicketBooking.entity.Movie;
import com.sachin.MovieTicketBooking.entity.Ticket;
import com.sachin.MovieTicketBooking.entity.User;
import com.sachin.MovieTicketBooking.error.MovieNotFoundException;
import com.sachin.MovieTicketBooking.error.TicketNotFoundException;
import com.sachin.MovieTicketBooking.error.UserNotFoundException;
import com.sachin.MovieTicketBooking.repository.MovieRepository;
import com.sachin.MovieTicketBooking.repository.TicketRepository;
import com.sachin.MovieTicketBooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Positive;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository,MovieRepository movieRepository,UserRepository userRepository){
        this.ticketRepository=ticketRepository;
        this.movieRepository=movieRepository;
        this.userRepository=userRepository;
    }
    @Override
    public Ticket bookTicket(@Positive(message = "number of seats must be positive") Long seats, Long userId, Long movieId) throws Exception {
        if(movieRepository.findById(movieId).isEmpty())
            throw new MovieNotFoundException("Invalid Movie Id");
        Movie movie=movieRepository.findById(movieId).get();
        User user=userRepository.findById(userId).get();
        if(userRepository.findById(userId).isEmpty())
            throw new UserNotFoundException("Invalid User Id");
        Ticket ticket= Ticket.builder().noOfSeats(seats).user(user).movie(movie).build();
        Long availableSeats= movie.getAvailableSeats();
        if(availableSeats>=seats) {
            movie.setAvailableSeats(availableSeats-seats);
            movieRepository.save(movie);
            return ticketRepository.save(ticket);
        }
        throw new Exception("Requested number of seats are not available");
    }

    @Override
    public List<Ticket> showAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getTicket(Long id) throws TicketNotFoundException {
        if(ticketRepository.findById(id).isEmpty())
            throw new TicketNotFoundException("Ticket Not Found");

        return ticketRepository.findById(id).get();
    }

    @Override
    public List<Ticket> showAllTicketsOfUserId(Long userId) throws UserNotFoundException {
        if(ticketRepository.findAllByUserId(userId)==null)
            throw new UserNotFoundException("User Not Found");

        return ticketRepository.findAllByUserId(userId);
    }

    @Override
    public void cancelTicket(Long id) throws TicketNotFoundException {
        if(ticketRepository.findById(id).isEmpty())
            throw new TicketNotFoundException("Ticket Not Found");
        Ticket ticket=ticketRepository.findById(id).get();
        Movie movie=ticket.getMovie();
        movie.setAvailableSeats(movie.getAvailableSeats()+ticket.getNoOfSeats());
        ticketRepository.deleteById(id);
        movieRepository.save(movie);
    }
}
