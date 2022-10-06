package com.sachin.MovieTicketBooking.repository;

import com.sachin.MovieTicketBooking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {

    @Query(value = "select * from ticket where user_id=:userId",nativeQuery = true)
    List<Ticket> findAllByUserId(@Param("userId") Long userId);
}
