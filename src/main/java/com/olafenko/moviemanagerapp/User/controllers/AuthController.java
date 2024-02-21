package com.olafenko.moviemanagerapp.User.controllers;

import com.olafenko.moviemanagerapp.User.dtos.LoginRequest;
import com.olafenko.moviemanagerapp.User.dtos.RegisterRequest;
import com.olafenko.moviemanagerapp.User.dtos.UserDto;
import com.olafenko.moviemanagerapp.User.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;



    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody LoginRequest loginRequest){
        UserDto user = userService.login(loginRequest);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody RegisterRequest request){
        UserDto user = userService.register(request);
        return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
    }


}
