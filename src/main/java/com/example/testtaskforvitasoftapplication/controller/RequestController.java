package com.example.testtaskforvitasoftapplication.controller;

import com.example.testtaskforvitasoftapplication.entity.Request;
import com.example.testtaskforvitasoftapplication.entity.UserEntity;
import com.example.testtaskforvitasoftapplication.entity.enumClasses.RequestStatusEnum;
import com.example.testtaskforvitasoftapplication.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<Request> createRequest(@RequestBody String content, @RequestParam Long userId) {
        UserEntity user = new UserEntity();
        Request request = requestService.createRequest(content, user);
        return ResponseEntity.ok(request);
    }

    @GetMapping
    public ResponseEntity<List<Request>> getAllRequests() {
        List<Request> requests = requestService.getAllRequests();
        return ResponseEntity.ok(requests);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Request> updateRequestStatus(@PathVariable Long id, @RequestParam RequestStatusEnum status) {
        Request updatedRequest = requestService.updateRequestStatus(id, status);
        return ResponseEntity.ok(updatedRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable Long id) {
        Request request = requestService.getRequestById(id); // Создайте метод в сервисе
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        requestService.deleteRequest(id); // Создайте метод в сервисе
        return ResponseEntity.noContent().build();
    }
}
