package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number; // Seat number within the hall

    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    public Seat(Hall hall, Integer number) {
        this.hall = hall;
        this.number = number;
    }
}
