package com.sachin.MovieTicketBooking.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
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
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long movieId;

    @NotBlank(message = "Enter a the movie name")
    String name;

    @PositiveOrZero(message = "Total Seats must be Positive")
    @NotNull(message = "Enter the total Seats")
    Long totalSeats;

    @PositiveOrZero(message = "Available Seats must be Positive")
    @NotNull(message = "Enter the Available Seats")
    Long availableSeats;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Movie movie = (Movie) o;
        return movieId != null && Objects.equals(movieId, movie.movieId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
