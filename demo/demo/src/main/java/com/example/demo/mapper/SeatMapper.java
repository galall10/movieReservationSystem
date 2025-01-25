package com.example.demo.mapper;

import com.example.demo.dto.SeatDTO;
import com.example.demo.model.Seat;

public class SeatMapper {

    public static SeatDTO toDto(Seat seat) {
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setId(seat.getId());
        seatDTO.setNumber(seat.getNumber());
        seatDTO.setHallId(seat.getHall().getId());
        return seatDTO;
    }

    public static Seat toEntity(SeatDTO seatDTO) {
        Seat seat = new Seat();
        seat.setId(seatDTO.getId());
        seat.setNumber(seatDTO.getNumber());

        // Note: You need to fetch the Hall entity from the database
        // and set it in the seat entity.

        return seat;
    }
}