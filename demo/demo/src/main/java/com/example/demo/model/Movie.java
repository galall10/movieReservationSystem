package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private double ticketPrice;

    @OneToMany(mappedBy = "movie")
    private List<Event> events = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_watched_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Users> watchedByUsers = new ArrayList<>();
}