package com.example.testtaskforvitasoftapplication.controller;

import com.example.testtaskforvitasoftapplication.entity.UserEntity;
import com.example.testtaskforvitasoftapplication.entity.enumClasses.RoleEnum;
import com.example.testtaskforvitasoftapplication.service.AdminService;
import com.example.testtaskforvitasoftapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private final UserService userService;
    private final AdminService adminService;

    @Autowired
    public AdminController(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public ResponseEntity<Page<UserEntity>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        logger.info("Fetching all users - page: {}, size: {}", page, size);
        Page<UserEntity> users = userService.getAllUsers(PageRequest.of(page, size));
        logger.info("Retrieved {} users", users.getTotalElements());
        return ResponseEntity.ok(users);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity userDetails) {
        logger.info("Updating user with id: {}", id);
        UserEntity updatedUser = userService.updateUser(id, userDetails);
        logger.info("Updated user: {}", updatedUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        logger.info("Deleting user with id: {}", id);
        userService.deleteUser(id);
        logger.info("Deleted user with id: {}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/search")
    public ResponseEntity<List<UserEntity>> searchUsers(@RequestParam String name) {
        logger.info("Searching users with name: {}", name);
        List<UserEntity> users = userService.searchUsersByName(name);
        logger.info("Found {} users with name: {}", users.size(), name);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/users/{userId}/roles/operator")
    public ResponseEntity<UserEntity> assignOperatorRole(@PathVariable Long userId) {
        logger.info("Assigning operator role to user with id: {}", userId);
        UserEntity updatedUser = userService.assignRole(userId, RoleEnum.OPERATOR);
        logger.info("Assigned operator role to user: {}", updatedUser);
        return ResponseEntity.ok(updatedUser);
    }
}
