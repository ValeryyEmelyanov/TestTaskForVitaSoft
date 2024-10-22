package com.example.testtaskforvitasoftapplication.service;

import com.example.testtaskforvitasoftapplication.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public UserEntity register(UserEntity user) {
        UserEntity o = null;
        return o;
    }

    public void logout() {
    }

    public boolean validateToken(String token) {
        boolean b = false;
        return false;
    }

    public String authenticate(String username, String password) {
        return null;
    }
}
