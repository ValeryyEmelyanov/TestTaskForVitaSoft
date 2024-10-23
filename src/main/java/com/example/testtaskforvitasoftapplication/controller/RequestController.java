package com.example.testtaskforvitasoftapplication.controller;

import com.example.testtaskforvitasoftapplication.dto.RequestDTO;
import com.example.testtaskforvitasoftapplication.entity.Request;
import com.example.testtaskforvitasoftapplication.entity.UserEntity;
import com.example.testtaskforvitasoftapplication.entity.enumClasses.RequestStatusEnum;
import com.example.testtaskforvitasoftapplication.service.RequestService;
import com.example.testtaskforvitasoftapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private static final Logger logger = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    private RequestService requestService;

    @Autowired
    private UserService userService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<Request> createRequest(@RequestBody String content, @RequestParam Long id) {
        UserEntity user = userService.getUserById(id);
        Request request = requestService.createRequest(content, user);
        logger.info("Created request with ID: {} for user ID: {}", request.getId(), id);
        return ResponseEntity.ok(request);
    }

    @GetMapping
    public ResponseEntity<List<Request>> getAllRequests(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        List<Request> requests = requestService.getAllRequests(PageRequest.of(page, size));
        logger.info("Fetching all requests, page: {}, size: {}", page, size);
        return ResponseEntity.ok(requests);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Request> updateRequestStatus(@PathVariable Long id, @RequestParam RequestStatusEnum status) {
        Request updatedRequest = requestService.updateRequestStatus(id, status);
        logger.info("Updated request ID: {} to status: {}", id, status);
        return ResponseEntity.ok(updatedRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestDTO> getRequestById(@PathVariable Long id) {
        RequestDTO request = requestService.getRequestById(id);
        logger.info("Fetched request by ID: {}", id);
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        requestService.deleteRequest(id);
        logger.info("Deleted request ID: {}", id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/view/{id}")
    public ResponseEntity<String> viewRequest(@PathVariable Long id) {
        RequestDTO requestDto = requestService.getRequestById(id);
        String formattedText = requestDto.getFormattedText();
        logger.info("Viewing request ID: {}", id);
        return ResponseEntity.ok(formattedText);
    }
}
