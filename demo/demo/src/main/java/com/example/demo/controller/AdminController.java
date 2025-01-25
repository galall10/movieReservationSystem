package com.example.demo.controller;

import com.example.demo.dto.HallDTO;
import com.example.demo.dto.MovieDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/addMovie")
    public ResponseEntity<String> addMovie(@RequestBody MovieDTO movieDTO) {
        adminService.addMovie(movieDTO);
        return ResponseEntity.ok("Movie added successfully.");
    }

    @PutMapping("/editMovie/{movieId}")
    public ResponseEntity<String> editMovie(@PathVariable Integer movieId, @RequestBody MovieDTO movieDTO) {
        adminService.editMovie(movieId, movieDTO);
        return ResponseEntity.ok("Movie edited successfully.");
    }

    @DeleteMapping("/deleteMovie/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable Integer movieId) {
        adminService.deleteMovie(movieId);
        return ResponseEntity.ok("Movie deleted successfully.");
    }

    @PostMapping("/addAdmin")
    public ResponseEntity<String> addAdmin(@RequestBody UserDTO userDto) {
        adminService.addAdmin(userDto);
        return ResponseEntity.ok("Admin added successfully.");
    }
    @DeleteMapping("/deleteAdmin/{adminId}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Integer adminId){
        adminService.deleteAdmin(adminId);
        return ResponseEntity.ok("Admin account deleted successfully.");
    }
    //add hall - edit hall - remove hall
    @PostMapping("/addHall")
    public ResponseEntity<String> addHall(@RequestBody HallDTO hallDTO) {
        adminService.addHall(hallDTO);
        return ResponseEntity.ok("Hall added successfully.");
    }

    @PutMapping("/editHall/{hallId}")
    public ResponseEntity<String> editHall(@PathVariable Integer hallId, @RequestBody HallDTO hallDTO) {
        adminService.editHall(hallId, hallDTO);
        return ResponseEntity.ok("Hall edited successfully.");
    }

    @DeleteMapping("/deleteHall/{hallId}")
    public ResponseEntity<String> deleteHall(@PathVariable Integer hallId) {
        adminService.deleteHall(hallId);
        return ResponseEntity.ok("Hall deleted successfully.");
    }
}

