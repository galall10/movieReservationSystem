package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String hallName;
    private int numberOfSeats;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();

    // Constructor to initialize seats list based on numberOfSeats
    public Hall(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
        initializeSeats();
    }

    // Initialize seats with unique numbers
    public void initializeSeats() {
        for (int i = 1; i <= numberOfSeats; i++) {
            Seat newSeat = new Seat(this, i);
            seats.add(newSeat);
        }
    }
}