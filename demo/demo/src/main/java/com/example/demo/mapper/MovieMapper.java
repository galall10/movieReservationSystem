package com.example.demo.mapper;

import com.example.demo.dto.MovieDTO;
import com.example.demo.model.Movie;
import com.example.demo.model.Event;
import com.example.demo.model.Users;

import java.util.List;
import java.util.stream.Collectors;

public class MovieMapper {

    public static MovieDTO toDto(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setName(movie.getName());
        movieDTO.setDescription(movie.getDescription());
        movieDTO.setTicketPrice(movie.getTicketPrice());

        // Map event IDs
        List<Integer> eventIds = movie.getEvents().stream()
                .map(Event::getId)
                .collect(Collectors.toList());
        movieDTO.setEventIds(eventIds);

        // Map user IDs who watched the movie
        List<Integer> watchedByUserIds = movie.getWatchedByUsers().stream()
                .map(Users::getId)
                .collect(Collectors.toList());
        movieDTO.setWatchedByUserIds(watchedByUserIds);

        return movieDTO;
    }

    public static Movie toEntity(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setName(movieDTO.getName());
        movie.setDescription(movieDTO.getDescription());
        movie.setTicketPrice(movieDTO.getTicketPrice());

        // Note: You may need to fetch events and users from the database
        // and set them in the movie entity if required.

        return movie;
    }
}