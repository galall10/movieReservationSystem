package com.example.demo.controller;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.service.ReservationService;
import com.example.demo.Enum.ReservationState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/user/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // Endpoint to create a new reservation
    @PostMapping("/create")
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        try {
            ReservationDTO createdReservation = reservationService.createReservation(reservationDTO);
            return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to get reservation details
    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationDTO> getReservation(@PathVariable Integer reservationId) {
        try {
            ReservationDTO reservation = reservationService.getReservationById(reservationId);
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to update reservation state (confirm, cancel, etc.)
    @PutMapping("/update/{reservationId}")
    public ResponseEntity<ReservationDTO> updateReservationState(@PathVariable Integer reservationId,
                                                                 @RequestParam ReservationState newState) {
        try {
            ReservationDTO updatedReservation = reservationService.updateReservationState(reservationId, newState);
            return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to cancel reservation
    @PutMapping("/cancel/{reservationId}")
    public ResponseEntity<ReservationDTO> cancelReservation(@PathVariable Integer reservationId) {
        try {
            ReservationDTO cancelledReservation = reservationService.cancelReservation(reservationId);
            return new ResponseEntity<>(cancelledReservation, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}