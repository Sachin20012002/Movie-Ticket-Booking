package com.sachin.MovieTicketBooking.repository;

import com.sachin.MovieTicketBooking.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;


//Error
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        User user=User.builder().
                userId(1L).
                name("sachin").
                email("sac@gmail.com").build();

        entityManager.persist(user);
    }


    @Test
    void findByEmailIgnoreCase() {
        User user=userRepository.findByEmailIgnoreCase("sachin@gmail.com");
        assertEquals(user.getName(),"sachin");
    }
}