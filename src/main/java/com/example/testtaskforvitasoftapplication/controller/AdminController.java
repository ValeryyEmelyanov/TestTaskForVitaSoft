package com.example.testtaskforvitasoftapplication.controller;

import com.example.testtaskforvitasoftapplication.entity.UserEntity;
import com.example.testtaskforvitasoftapplication.entity.enumClasses.RoleEnum;
import com.example.testtaskforvitasoftapplication.service.AdminService;
import com.example.testtaskforvitasoftapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final AdminService adminService;

    @Autowired
    public AdminController(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/search")
    public ResponseEntity<List<UserEntity>> searchUsers(@RequestParam String name) {
        List<UserEntity> users = userService.searchUsersByName(name);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/users/{userId}/roles/operator")
    public ResponseEntity<UserEntity> assignOperatorRole(@PathVariable Long userId) {
        UserEntity updatedUser = userService.assignRole(userId, RoleEnum.OPERATOR);
        return ResponseEntity.ok(updatedUser);
    }
}
