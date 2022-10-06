package com.sachin.MovieTicketBooking.entity;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
/*  @Data (not recommended as performance and memory issues may occur)
    getters and setters and toString and EqualsAndHashCode are generated
    RequiredArgsConstructor will be created, which includes final properties and @NonNull annotated properties,
    and it will be created only if no other constructor is created.
*/
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long ticketId;
    @NotNull(message = "Enter the number of seats")
    Long noOfSeats;
    @ManyToOne //Association
    @JoinColumn(name="movie_id", referencedColumnName = "movieId")
    @NotNull
    Movie movie;

    @ManyToOne //Association
    @JoinColumn(name = "user_id",referencedColumnName = "userId")
    @NotNull
    User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ticket ticket = (Ticket) o;
        return ticketId != null && Objects.equals(ticketId, ticket.ticketId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
