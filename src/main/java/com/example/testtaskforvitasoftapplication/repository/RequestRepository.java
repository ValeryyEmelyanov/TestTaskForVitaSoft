package com.example.testtaskforvitasoftapplication.repository;

import com.example.testtaskforvitasoftapplication.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    public Optional<Request> findById(Long Id);
}
