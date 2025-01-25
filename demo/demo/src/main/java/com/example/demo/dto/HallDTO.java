package com.example.demo.dto;

import lombok.Data;
import java.util.List;

@Data
public class HallDTO {
    private Integer id;
    private String hallName;
    private int numberOfSeats;
    private List<Integer> seatIds;
}