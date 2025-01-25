package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.mapper.HallMapper;
import com.example.demo.mapper.MovieMapper;
import com.example.demo.model.*;
import com.example.demo.repo.EventRepo;
import com.example.demo.repo.HallRepo;
import com.example.demo.repo.MovieRepo;
import com.example.demo.repo.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AdminService {
    private final UserRepo userRepo;
    private final MovieRepo movieRepo;
    private final HallRepo hallRepo;
    private final EventRepo eventRepo;

    public AdminService(UserRepo userRepo, MovieRepo movieRepo, HallRepo hallRepo, EventRepo eventRepo) {
        this.userRepo = userRepo;
        this.movieRepo = movieRepo;
        this.hallRepo = hallRepo;
        this.eventRepo = eventRepo;
    }

    // Add a new movie
    public void addMovie(MovieDTO movieDTO) {
        if (movieDTO == null) {
            throw new IllegalArgumentException("MovieDTO cannot be null");
        }

        // Convert the DTO to an entity
        Movie movie = MovieMapper.toEntity(movieDTO);

        // Save the movie
        movieRepo.save(movie);
    }

    // Edit an existing movie
    public void editMovie(Integer id, MovieDTO movieDTO) {
        // Find the existing movie by ID
        Movie toBeEditedMovie = movieRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));

        // Update the existing movie with the new values from the DTO
        toBeEditedMovie.setName(movieDTO.getName());
        toBeEditedMovie.setDescription(movieDTO.getDescription());
        toBeEditedMovie.setTicketPrice(movieDTO.getTicketPrice());

        // Save the updated movie
        movieRepo.save(toBeEditedMovie);
    }

    // Delete a movie
    public void deleteMovie(Integer id) {
        movieRepo.deleteById(id);
    }

    // Add admin user
    public void addAdmin(UserDTO userDto) {
        Users admin = new Users();
        admin.setName(userDto.getName());
        admin.setUsername(userDto.getUsername());
        admin.setPassword(new BCryptPasswordEncoder(12).encode(userDto.getPassword()));
        admin.setRole("ADMIN");
        userRepo.save(admin); // Save the admin to userRepo
    }

    // Delete admin user
    public void deleteAdmin(Integer adminId) {
        userRepo.deleteById(adminId);
    }

    // Add a new hall
    public void addHall(HallDTO hallDTO) {
        Hall hall = HallMapper.toEntity(hallDTO);
        hallRepo.save(hall);
    }

    // Edit an existing hall
    public void editHall(Integer id, HallDTO hallDTO) {
        // Find the existing hall by ID
        Hall toBeEditedHall = hallRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hall not found"));

        // Update the existing hall with the new values from the DTO
        toBeEditedHall.setNumberOfSeats(hallDTO.getNumberOfSeats());
        toBeEditedHall.initializeSeats(); // Reinitialize seats if needed

        // Save the updated hall
        hallRepo.save(toBeEditedHall);
    }

    // Delete a hall
    public void deleteHall(Integer id) {
        hallRepo.deleteById(id);
    }
}