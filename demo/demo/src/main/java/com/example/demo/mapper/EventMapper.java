package com.example.demo.mapper;

import com.example.demo.dto.EventDTO;
import com.example.demo.model.Event;
import com.example.demo.model.Hall;
import com.example.demo.model.Movie;

public class EventMapper {

    public static EventDTO toDto(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setStartDate(event.getStartDate());
        eventDTO.setEndDate(event.getEndDate());
        eventDTO.setHallId(event.getHall().getId());
        eventDTO.setMovieId(event.getMovie().getId());
        return eventDTO;
    }

    public static Event toEntity(EventDTO eventDTO, Hall hall, Movie movie) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setStartDate(eventDTO.getStartDate());
        event.setEndDate(eventDTO.getEndDate());
        event.setHall(hall);
        event.setMovie(movie);
        return event;
    }
}