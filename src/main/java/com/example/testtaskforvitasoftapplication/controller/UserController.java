package com.example.testtaskforvitasoftapplication.controller;


import com.example.testtaskforvitasoftapplication.dto.RequestDTO;
import com.example.testtaskforvitasoftapplication.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private RequestService requestService;

    @PostMapping("/requests")
    public ResponseEntity<RequestDTO> createRequest(@Valid @RequestBody RequestDTO requestDTO) {
        RequestDTO createdRequest = requestService.createRequest(requestDTO);
        logger.info("Created request with ID: {}", createdRequest.getId());
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    @GetMapping("/requests")
    public ResponseEntity<Page<RequestDTO>> getUserRequests(@RequestParam Long id, Pageable pageable) {
        Page<RequestDTO> userRequests = requestService.getUserRequests(id, pageable);
        logger.info("Fetched requests for user ID: {}, Page: {}", id, pageable.getPageNumber());
        return new ResponseEntity<>(userRequests, HttpStatus.OK);
    }

    @PutMapping("/requests/{requestId}")
    public ResponseEntity<RequestDTO> updateRequest(@PathVariable Long requestId,
                                                    @Valid @RequestBody RequestDTO requestDTO) {
        RequestDTO updatedRequest = requestService.updateRequest(requestId, requestDTO);
        logger.info("Updated request ID: {}", requestId);
        return new ResponseEntity<>(updatedRequest, HttpStatus.OK);
    }

    @PostMapping("/requests/{requestId}/submit")
    public ResponseEntity<Void> submitRequest(@PathVariable Long requestId) {
        requestService.submitRequest(requestId);
        logger.info("Submitted request ID: {}", requestId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
