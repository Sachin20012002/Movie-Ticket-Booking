package com.sachin.MovieTicketBooking.service;

import com.sachin.MovieTicketBooking.entity.Ticket;
import com.sachin.MovieTicketBooking.error.MovieNotFoundException;
import com.sachin.MovieTicketBooking.error.TicketNotFoundException;
import com.sachin.MovieTicketBooking.error.UserNotFoundException;

import java.util.List;

public interface TicketService {

    void cancelTicket(Long id) throws TicketNotFoundException;

    Ticket bookTicket(Long seats, Long userId, Long movieId) throws Exception;

    List<Ticket> showAllTickets();

    Ticket getTicket(Long id) throws TicketNotFoundException;

    List<Ticket> showAllTicketsOfUserId(Long userId) throws TicketNotFoundException, UserNotFoundException;
}
