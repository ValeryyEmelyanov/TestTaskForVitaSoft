package com.example.testtaskforvitasoftapplication.controller;

import com.example.testtaskforvitasoftapplication.dto.LoginRequest;
import com.example.testtaskforvitasoftapplication.entity.UserEntity;
import com.example.testtaskforvitasoftapplication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@Valid @RequestBody UserEntity user) {
        logger.info("Registering user: {}", user.getName());
        UserEntity createdUser = authService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        logger.info("User attempting to log in: {}", loginRequest.getUsername());
        String token = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        logger.info("User logging out");
        authService.logout();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check")
    public ResponseEntity<Void> checkToken(@RequestParam String token) {
        logger.info("Checking token validity: {}", token);
        boolean isValid = authService.validateToken(token);
        return ResponseEntity.ok().build();
    }
}
