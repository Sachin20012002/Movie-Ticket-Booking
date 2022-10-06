package com.sachin.MovieTicketBooking.controller;

import com.sachin.MovieTicketBooking.entity.Ticket;
import com.sachin.MovieTicketBooking.error.TicketNotFoundException;
import com.sachin.MovieTicketBooking.error.UserNotFoundException;
import com.sachin.MovieTicketBooking.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TicketController {

    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService){
        this.ticketService=ticketService;
    }

    @PostMapping("ticket/{seats}/{userId}/{movieId}")
    public Ticket bookTicket(@PathVariable("seats") Long seats,@PathVariable("userId") Long userId,@PathVariable("movieId") Long movieId) throws Exception {
        return ticketService.bookTicket(seats,userId,movieId);
    }

    @DeleteMapping("ticket/{id}")
    public String cancelTicket(@PathVariable("id") Long id) throws TicketNotFoundException {
        ticketService.cancelTicket(id);
        return "Ticket Cancelled";
    }

    @GetMapping("ticket")
    public List<Ticket> showAllTickets(){
        return ticketService.showAllTickets();
    }

    @GetMapping("ticket/{id}")
    public Ticket getTicket(@PathVariable("id") Long id) throws TicketNotFoundException {
        return ticketService.getTicket(id);
    }

    @GetMapping("ticket/user/{id}")
    public List<Ticket> showAllTicketsOfUserId(@PathVariable("id") Long userId) throws UserNotFoundException, TicketNotFoundException {
        return ticketService.showAllTicketsOfUserId(userId);
    }
}
