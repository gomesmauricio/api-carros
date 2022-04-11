package com.example.carros.api.controller;

import com.example.carros.domain.dto.UserDTO;
import com.example.carros.domain.model.User;
import com.example.carros.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/info")
    public UserDTO userInfo(@AuthenticationPrincipal User user) {

        //retorna usuario logado no contexto do spring
//      UserDetails userDetails = JwtUtil.getUserDetails();

        return UserDTO.create(user);
    }

    @GetMapping
    public List<UserDTO> listUser() {
        return service.getUser();
    }

}
