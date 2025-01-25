package com.example.demo.repo;

import com.example.demo.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepo extends JpaRepository<Seat,Integer> {
}
