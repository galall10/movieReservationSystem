package com.example.demo.controller;

import com.example.demo.dto.EventDTO;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/schedules")
public class EventController {

    @Autowired
    private EventService eventService;

    // Endpoint to add a new schedule
    @PostMapping("/add")
    public ResponseEntity<EventDTO> addSchedule(@RequestBody EventDTO eventDTO) {
        try {
            EventDTO createdSchedule = eventService.addSchedule(eventDTO);
            return new ResponseEntity<>(createdSchedule, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to update an existing schedule
    @PutMapping("/update/{scheduleId}")
    public ResponseEntity<EventDTO> updateSchedule(@PathVariable Integer scheduleId,
                                                   @RequestBody EventDTO eventDTO) {
        try {
            EventDTO updatedSchedule = eventService.updateSchedule(scheduleId, eventDTO);
            return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to delete a schedule
    @DeleteMapping("/delete/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Integer scheduleId) {
        try {
            eventService.deleteSchedule(scheduleId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to get a schedule by ID
    @GetMapping("/{scheduleId}")
    public ResponseEntity<EventDTO> getScheduleById(@PathVariable Integer scheduleId) {
        try {
            EventDTO schedule = eventService.getScheduleById(scheduleId);
            return new ResponseEntity<>(schedule, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to get all schedules
    @GetMapping("/all")
    public ResponseEntity<List<EventDTO>> getAllSchedules() {
        try {
            List<EventDTO> schedules = eventService.getAllSchedules();
            return new ResponseEntity<>(schedules, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}