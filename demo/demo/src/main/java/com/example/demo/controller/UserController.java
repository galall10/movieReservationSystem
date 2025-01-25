package com.example.demo.controller;

import com.example.demo.dto.MovieDTO;
import com.example.demo.mapper.*;
import com.example.demo.model.Movie;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public ResponseEntity<List<MovieDTO>> availableMovies() {
        List<Movie> movies = userService.getAllMovies();
        List<MovieDTO> movieDTO = movies.stream()
                .map(MovieMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(movieDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieDTO>> searchMovie(@RequestParam String keyword) {
        List<Movie> movies = userService.searchMovies(keyword);
        List<MovieDTO> movieDTOS = movies.stream()
                .map(MovieMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(movieDTOS);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDTO> movieDetails(@PathVariable Integer movieId) {
        Movie movie = userService.getMovieDetails(movieId);
        return ResponseEntity.ok(MovieMapper.toDto(movie));
    }

    @DeleteMapping("/{userId}/account")
    public ResponseEntity<String> deleteAccount(@PathVariable Integer userId) {
        userService.deleteAccount(userId);
        return ResponseEntity.ok("Account deleted successfully.");
    }

//    @GetMapping("/{userId}/movies")
//    public ResponseEntity<List<MiniMovieDTO>> getWatchedMovies(@PathVariable Integer userId) {
//        List<Movie> movies = userService.getWatchedMovies(userId);
//        List<MiniMovieDTO> watchedMovies = movies.stream()
//                .map(OtherMovieMapper::toMiniMovieDTO/*lsa 3ayz el watched date*/)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(watchedMovies);
//    }
}
