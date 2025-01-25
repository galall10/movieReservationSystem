package com.example.demo.repo;

import com.example.demo.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<Event,Integer> {
}
