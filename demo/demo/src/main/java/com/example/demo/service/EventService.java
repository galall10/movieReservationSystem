package com.example.demo.service;

import com.example.demo.dto.EventDTO;
import com.example.demo.mapper.EventMapper;
import com.example.demo.model.Event;
import com.example.demo.model.Hall;
import com.example.demo.model.Movie;
import com.example.demo.repo.HallRepo;
import com.example.demo.repo.MovieRepo;
import com.example.demo.repo.EventRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepo eventRepo;
    private final HallRepo hallRepo;
    private final MovieRepo movieRepo;

    // Constructor-based dependency injection
    public EventService(EventRepo eventRepo, HallRepo hallRepo, MovieRepo movieRepo) {
        this.eventRepo = eventRepo;
        this.hallRepo = hallRepo;
        this.movieRepo = movieRepo;
    }

    // Add a new schedule
    public EventDTO addSchedule(EventDTO eventDTO) {
        Hall hall = hallRepo.findById(eventDTO.getHallId())
                .orElseThrow(() -> new RuntimeException("Hall not found"));
        Movie movie = movieRepo.findById(eventDTO.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Event event = EventMapper.toEntity(eventDTO, hall, movie);
        Event savedEvent = eventRepo.save(event);
        return EventMapper.toDto(savedEvent);
    }

    // Update an existing schedule
    public EventDTO updateSchedule(Integer scheduleId, EventDTO eventDTO) {
        Event existingEvent = eventRepo.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Hall hall = hallRepo.findById(eventDTO.getHallId())
                .orElseThrow(() -> new RuntimeException("Hall not found"));
        Movie movie = movieRepo.findById(eventDTO.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        existingEvent.setHall(hall);
        existingEvent.setMovie(movie);
        existingEvent.setStartDate(eventDTO.getStartDate());
        existingEvent.setEndDate(eventDTO.getEndDate());

        Event updatedEvent = eventRepo.save(existingEvent);
        return EventMapper.toDto(updatedEvent);
    }

    // Delete a schedule
    public void deleteSchedule(Integer scheduleId) {
        eventRepo.deleteById(scheduleId);
    }

    // Get a schedule by ID
    public EventDTO getScheduleById(Integer scheduleId) {
        Event event = eventRepo.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        return EventMapper.toDto(event);
    }

    // Get all schedules
    public List<EventDTO> getAllSchedules() {
        List<Event> events = eventRepo.findAll();
        return events.stream()
                .map(EventMapper::toDto)
                .collect(Collectors.toList());
    }
}