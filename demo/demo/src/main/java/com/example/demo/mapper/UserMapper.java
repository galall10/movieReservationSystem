package com.example.demo.mapper;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.Users;

public class UserMapper {

    public static Users toEntity(UserDTO userDTO) {
        Users user = new Users();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setRole(userDTO.getRole());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public static UserDTO toDTO(Users user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole());
        // Exclude password in the DTO for security reasons
        return userDTO;
    }
}
