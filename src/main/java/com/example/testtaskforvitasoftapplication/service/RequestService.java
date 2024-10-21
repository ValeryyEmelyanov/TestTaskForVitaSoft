package com.example.testtaskforvitasoftapplication.service;

import com.example.testtaskforvitasoftapplication.entity.Request;
import com.example.testtaskforvitasoftapplication.entity.UserEntity;
import com.example.testtaskforvitasoftapplication.entity.enumClasses.RequestStatusEnum;
import com.example.testtaskforvitasoftapplication.repository.RequestRepository;
import com.example.testtaskforvitasoftapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    public Request createRequest(String content, UserEntity user) {
        Request request = new Request();
        request.setContent(content);
        request.setUser(user);
        return requestRepository.save(request);
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public Request updateRequestStatus(Long requestId, RequestStatusEnum newStatus) {
        Request request = requestRepository.findById(requestId).orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus(newStatus);
        request.setUpdatedAt(LocalDateTime.now());
        return requestRepository.save(request);
    }

    public Request getRequestById(Long requestId) {
        return requestRepository.findById(requestId).orElseThrow(() -> new RuntimeException("Request not found"));
    }

    public void deleteRequest(Long requestId) {
        requestRepository.deleteById(requestId);
    }

    public List<Request> getRequestsByUserId(Long userId) {
        return null;
    }
}
