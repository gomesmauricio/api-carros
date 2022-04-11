package com.example.carros.domain.service;


import com.example.carros.domain.dto.UserDTO;
import com.example.carros.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

    public List<UserDTO> getUser() {
        return userRepository.findAll().stream().map(UserDTO::create).collect(Collectors.toList());
    }
}
