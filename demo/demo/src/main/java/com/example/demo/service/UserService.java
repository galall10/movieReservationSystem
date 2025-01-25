package com.example.demo.service;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.model.Movie;
import com.example.demo.model.Reservation;
import com.example.demo.model.Seat;
import com.example.demo.model.Users;
import com.example.demo.repo.MovieRepo;
import com.example.demo.repo.ReservationRepo;
import com.example.demo.repo.SeatRepo;
import com.example.demo.repo.UserRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private final MovieRepo movieRepo;
    private final UserRepo userRepo;

    public UserService(MovieRepo movieRepo, UserRepo userRepo) {
        this.movieRepo = movieRepo;
        this.userRepo = userRepo;
    }

    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    public List<Movie> searchMovies(String keyword) {
        return movieRepo.searchMovie(keyword);
    }

    public Movie getMovieDetails(Integer movieId) {
        return movieRepo.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found with ID: " + movieId));
    }


    public void deleteAccount(Integer userId) {
        if (!userRepo.existsById(userId)) {
            throw new RuntimeException("Users not found with ID: " + userId);
        }
        userRepo.deleteById(userId);
    }

//    public List<Movie> getWatchedMovies(Integer userId) {
//        Users user = userRepo.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
//        return user.getWatchedMovies();
//    }
}
