package com.example.testtaskforvitasoftapplication.service;

import com.example.testtaskforvitasoftapplication.entity.Request;
import com.example.testtaskforvitasoftapplication.entity.UserEntity;
import com.example.testtaskforvitasoftapplication.entity.enumClasses.RoleEnum;
import com.example.testtaskforvitasoftapplication.repository.RequestRepository;
import com.example.testtaskforvitasoftapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestRepository requestRepository;

    public UserEntity assignRole(Long id, RoleEnum roleEnum) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        user.getRoles().add(roleEnum);
        return userRepository.save(user);
    }

    public List<UserEntity> searchUsersByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    public Page<UserEntity> getAllUsers(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    public UserEntity updateUser(Long id, UserEntity userDetails) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        user.setName(userDetails.getName());
        user.setRoles(userDetails.getRoles());

        return userRepository.save(user);
    }
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public List<Request> getRequestsByUserId(Long id) {
        return requestRepository.findRequestsByUserId(id);
    }

    public UserEntity getUserById(Long id) {
        Optional<UserEntity> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }
}
