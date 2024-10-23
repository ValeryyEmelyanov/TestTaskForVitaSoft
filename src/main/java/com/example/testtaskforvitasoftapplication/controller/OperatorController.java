package com.example.testtaskforvitasoftapplication.controller;

import com.example.testtaskforvitasoftapplication.entity.Request;
import com.example.testtaskforvitasoftapplication.entity.enumClasses.RequestStatusEnum;
import com.example.testtaskforvitasoftapplication.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/operator")
public class OperatorController {

    private static final Logger logger = LoggerFactory.getLogger(OperatorController.class);

    private final RequestService requestService;

    @Autowired
    public OperatorController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/requests")
    public ResponseEntity<List<Request>> getAllRequests() {
        logger.info("Fetching all requests");
        List<Request> requests = requestService.getAllRequests();
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/requests/user/{id}")
    public ResponseEntity<List<Request>> getRequestsByUserId(@PathVariable Long id) {
        logger.info("Fetching requests for user ID: {}", id);
        List<Request> requests = requestService.getRequestsByUserId(id);
        return ResponseEntity.ok(requests);
    }

    @PostMapping("/requests/{requestId}/approve")
    public ResponseEntity<Request> approveRequest(@PathVariable Long requestId) {
        logger.info("Approving request ID: {}", requestId);
        Request updatedRequest = requestService.updateRequestStatus(requestId, RequestStatusEnum.APPROVED);
        return ResponseEntity.ok(updatedRequest);
    }

    @PostMapping("/requests/{requestId}/reject")
    public ResponseEntity<Request> rejectRequest(@PathVariable Long requestId) {
        logger.info("Rejecting request ID: {}", requestId);
        Request updatedRequest = requestService.updateRequestStatus(requestId, RequestStatusEnum.REJECTED);
        return ResponseEntity.ok(updatedRequest);
    }
}
