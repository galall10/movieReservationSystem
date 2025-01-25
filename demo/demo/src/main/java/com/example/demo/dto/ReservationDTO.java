package com.example.demo.dto;

import com.example.demo.Enum.ReservationState;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationDTO {
    private Integer id;
    private Integer eventId;
    private Integer userId;
    private Integer seatId;
    private ReservationState state;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date eventStartDate; // Transient field

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date eventEndDate;   // Transient field
}