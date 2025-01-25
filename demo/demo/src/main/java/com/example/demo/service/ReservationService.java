package com.example.demo.service;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.model.*;
import com.example.demo.repo.ReservationRepo;
import com.example.demo.repo.EventRepo;
import com.example.demo.repo.SeatRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.Enum.ReservationState;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepo reservationRepository;
    private final EventRepo eventRepository;
    private final SeatRepo seatRepository;
    private final UserRepo userRepository;

    public ReservationService(ReservationRepo reservationRepository,
                              EventRepo eventRepository,
                              SeatRepo seatRepository,
                              UserRepo userRepository) {
        this.reservationRepository = reservationRepository;
        this.eventRepository = eventRepository;
        this.seatRepository = seatRepository;
        this.userRepository = userRepository;
    }

    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        // Validate user, event, and seat exist
        Users user = userRepository.findById(reservationDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User with ID " + reservationDTO.getUserId() + " not found"));
        Event event = eventRepository.findById(reservationDTO.getEventId())
                .orElseThrow(() -> new RuntimeException("Event with ID " + reservationDTO.getEventId() + " not found"));
        Seat seat = seatRepository.findById(reservationDTO.getSeatId())
                .orElseThrow(() -> new RuntimeException("Seat with ID " + reservationDTO.getSeatId() + " not found"));

        // Check if the seat is already reserved for this event
        if (!isSeatAvailable(event.getId(), seat.getId())) {
            throw new RuntimeException("Seat " + seat.getNumber() + " is already reserved for event " + event.getId());
        }

        // Convert DTO to Entity
        Reservation reservation = ReservationMapper.toEntity(reservationDTO, event, user, seat);
        reservation.setState(ReservationState.PENDING);

        // Save the reservation
        Reservation savedReservation = reservationRepository.save(reservation);

        // Convert the saved entity back to DTO
        return ReservationMapper.toDto(savedReservation);
    }

    public boolean isSeatAvailable(Integer eventId, Integer seatId) {
        return reservationRepository
                .findByEventIdAndSeatId(eventId, seatId)
                .isEmpty();
    }

    public ReservationDTO updateReservationState(Integer reservationId, ReservationState newState) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation with ID " + reservationId + " not found"));
        reservation.setState(newState);
        Reservation updatedReservation = reservationRepository.save(reservation);
        return ReservationMapper.toDto(updatedReservation);
    }

    public ReservationDTO getReservationById(Integer reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation with ID " + reservationId + " not found"));
        return ReservationMapper.toDto(reservation);
    }

    public ReservationDTO cancelReservation(Integer reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation with ID " + reservationId + " not found"));
        reservation.setState(ReservationState.CANCELLED);
        Reservation cancelledReservation = reservationRepository.save(reservation);
        return ReservationMapper.toDto(cancelledReservation);
    }
}