package com.example.demo.dto;

import lombok.Data;
import java.util.List;

@Data
public class MovieDTO {
    private Integer id;
    private String name;
    private String description;
    private double ticketPrice;
    private List<Integer> eventIds;
    private List<Integer> watchedByUserIds;
}