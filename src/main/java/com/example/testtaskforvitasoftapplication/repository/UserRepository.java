package com.example.testtaskforvitasoftapplication.repository;

import com.example.testtaskforvitasoftapplication.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {


    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByName(String name);

    List<UserEntity> findByNameContainingIgnoreCase(String name);
}