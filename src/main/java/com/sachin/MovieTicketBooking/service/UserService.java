package com.sachin.MovieTicketBooking.service;

import com.sachin.MovieTicketBooking.entity.User;
import com.sachin.MovieTicketBooking.error.UserNotFoundException;

import java.util.List;

public interface UserService {
    User addUser(User user);

    User getUser(Long id) throws UserNotFoundException;

    User updateUser(Long id, User user) throws UserNotFoundException;

    void deleteUserById(Long id) throws UserNotFoundException;

    List<User> fetchUserList();

    User fetchUserByEmail(String email) throws UserNotFoundException;

    List<User> getAllUsers();
}
