package com.example.demo.mapper;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.model.Event;
import com.example.demo.model.Reservation;
import com.example.demo.model.Seat;
import com.example.demo.model.Users;

public class ReservationMapper {

    public static ReservationDTO toDto(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setEventId(reservation.getEvent().getId());
        reservationDTO.setUserId(reservation.getUser().getId());
        reservationDTO.setSeatId(reservation.getSeat().getId());
        reservationDTO.setState(reservation.getState());

        // Populate event dates from the associated Event entity
        reservationDTO.setEventStartDate(reservation.getEvent().getStartDate());
        reservationDTO.setEventEndDate(reservation.getEvent().getEndDate());

        return reservationDTO;
    }

    public static Reservation toEntity(ReservationDTO reservationDTO, Event event, Users user, Seat seat) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getId());
        reservation.setState(reservationDTO.getState());
        reservation.setEvent(event);
        reservation.setUser(user);
        reservation.setSeat(seat);
        return reservation;
    }
}