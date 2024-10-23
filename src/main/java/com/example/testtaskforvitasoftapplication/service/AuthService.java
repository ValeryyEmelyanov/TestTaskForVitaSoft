package com.example.testtaskforvitasoftapplication.service;

import com.example.testtaskforvitasoftapplication.entity.UserEntity;
import com.example.testtaskforvitasoftapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserEntity register(UserEntity user) {
        if (userRepository.findById(user.getId()).isPresent()) {
            throw new RuntimeException("User already exists with id: " + user.getId());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void logout() {
    }

    public boolean validateToken(String token) {
        boolean b = false;
        return false;
    }

    public String authenticate(String name, String password) {
        Optional<UserEntity> userOptional = userRepository.findByName(name);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {

                return "Authentication successful";
            }
        }

        throw new RuntimeException("Invalid username or password");
    }
}
