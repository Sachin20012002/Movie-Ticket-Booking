package com.sachin.MovieTicketBooking.controller;


import com.sachin.MovieTicketBooking.entity.User;
import com.sachin.MovieTicketBooking.error.UserNotFoundException;
import com.sachin.MovieTicketBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/user")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/user")
    public User addUser(@Valid @RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Long id) throws UserNotFoundException {
        return userService.getUser(id);
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user) throws UserNotFoundException {
        return userService.updateUser(id,user);
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") Long id) throws UserNotFoundException {
        userService.deleteUserById(id);
        return "User deleted Successfully!!";
    }

    @GetMapping("/user/email/{email}")
    public User fetchUserByEmail(@PathVariable("email") String email) throws UserNotFoundException {
        return userService.fetchUserByEmail(email);
    }
}
