package com.sachin.MovieTicketBooking.entity;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long userId;
    String name;
    @NotBlank(message = "Enter your email address")
    @NotNull(message = "Enter your email address")
    @Email(message = "Enter a valid email")
    String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return userId != null && Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
