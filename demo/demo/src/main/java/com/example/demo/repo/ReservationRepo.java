package com.example.demo.repo;

import com.example.demo.model.Reservation;
import com.example.demo.model.Event;
import com.example.demo.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface ReservationRepo extends JpaRepository<Reservation, Integer> {
    // Replace "schedule" with "event"
    Optional<Reservation> findBySeatAndEvent(Seat seat, Event event);

    Collection<Object> findByEventIdAndSeatId(Integer eventId, Integer seatId);
}