package com.example.testtaskforvitasoftapplication.repository;

import com.example.testtaskforvitasoftapplication.entity.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    public Optional<Request> findById(Long req);

    List<Request> findByUserId(Long id);

    Page<Request> findByUserId(Long userId, Pageable pageable);

    List<Request> findRequestsByUserId(Long id);
}
