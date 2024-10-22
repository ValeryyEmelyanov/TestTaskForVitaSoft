package com.example.testtaskforvitasoftapplication.service;

import com.example.testtaskforvitasoftapplication.entity.UserEntity;
import com.example.testtaskforvitasoftapplication.entity.enumClasses.RoleEnum;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public UserEntity assignRole(Long userId, RoleEnum roleEnum) {
        return null;
    }

    public List<UserEntity> searchUsersByName(String name) {
        return null;
    }
}
