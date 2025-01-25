package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Users;
import com.example.demo.repo.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JWTService jwtService;
    private final AuthenticationManager authManager;
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder encoder;

    // Constructor for Dependency Injection
    public AuthService(
            JWTService jwtService,
            AuthenticationManager authManager,
            UserRepo userRepo) {
        this.jwtService = jwtService;
        this.authManager = authManager;
        this.userRepo = userRepo;
        this.encoder = new BCryptPasswordEncoder(12);
    }

//    public Users register(Users user) {
//        user.setRole("USER");
//        user.setPassword(encoder.encode(user.getPassword()));
//        userRepo.save(user);
//        return user;
//    }

    public UserDTO register(UserDTO userDto) {
        Users userEntity = UserMapper.toEntity(userDto);
        userEntity.setPassword(encoder.encode(userDto.getPassword())); // Encode password
        userEntity.setRole("ROLE_USER");
        Users savedUser = userRepo.save(userEntity);
        return UserMapper.toDTO(savedUser);
    }

    public String verify(UserDTO userDTO) {
        Users user = UserMapper.toEntity(userDTO);
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            throw new RuntimeException("Authentication failed");
        }
    }
//    public String verify(Users user) {
//        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//        if (authentication.isAuthenticated()) {
//            return jwtService.generateToken(user.getUsername());
//        } else {
//            return "fail";
//        }
//    }

}
