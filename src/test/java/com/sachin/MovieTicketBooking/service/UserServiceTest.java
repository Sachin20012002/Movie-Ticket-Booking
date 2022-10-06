package com.sachin.MovieTicketBooking.service;

import com.sachin.MovieTicketBooking.entity.User;
import com.sachin.MovieTicketBooking.error.UserNotFoundException;
import com.sachin.MovieTicketBooking.repository.MovieRepository;
import com.sachin.MovieTicketBooking.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
       User user=User.builder().
               userId(1L).
               email("sac@gmail.com").
               name("sachin").build();
        Mockito.when(userRepository.findByEmailIgnoreCase("sac@gmail.com"))
                .thenReturn(user);
    }

    @Test
    void fetchUserByEmail() throws UserNotFoundException {
        String email="sac@gmail.com";
        User found=userService.fetchUserByEmail(email);
        assertEquals(email,found.getEmail());
    }
}