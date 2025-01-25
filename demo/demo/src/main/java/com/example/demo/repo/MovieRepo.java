package com.example.demo.repo;

import com.example.demo.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie, Integer> {
    @Query("SELECT m FROM Movie m WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Movie> searchMovie(String keyword);
}



//+ "LOWER(m.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "