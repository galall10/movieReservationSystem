package com.example.demo.mapper;

import com.example.demo.dto.HallDTO;
import com.example.demo.model.Hall;
import com.example.demo.model.Seat;

import java.util.List;
import java.util.stream.Collectors;

public class HallMapper {

    public static HallDTO toDto(Hall hall) {
        HallDTO hallDTO = new HallDTO();
        hallDTO.setId(hall.getId());
        hallDTO.setHallName(hall.getHallName());
        hallDTO.setNumberOfSeats(hall.getNumberOfSeats());

        // Map seat IDs
        List<Integer> seatIds = hall.getSeats().stream()
                .map(Seat::getId)
                .collect(Collectors.toList());
        hallDTO.setSeatIds(seatIds);

        return hallDTO;
    }

    public static Hall toEntity(HallDTO hallDTO) {
        Hall hall = new Hall();
        hall.setId(hallDTO.getId());
        hall.setHallName(hallDTO.getHallName());
        hall.setNumberOfSeats(hallDTO.getNumberOfSeats());

        // Initialize seats based on the number of seats
        hall.initializeSeats();

        return hall;
    }
}