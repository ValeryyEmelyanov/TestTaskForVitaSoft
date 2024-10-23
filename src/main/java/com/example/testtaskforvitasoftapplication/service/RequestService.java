package com.example.testtaskforvitasoftapplication.service;

import com.example.testtaskforvitasoftapplication.dto.RequestDTO;
import com.example.testtaskforvitasoftapplication.entity.Request;
import com.example.testtaskforvitasoftapplication.entity.UserEntity;
import com.example.testtaskforvitasoftapplication.entity.enumClasses.RequestStatusEnum;
import com.example.testtaskforvitasoftapplication.repository.RequestRepository;
import com.example.testtaskforvitasoftapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.example.testtaskforvitasoftapplication.entity.enumClasses.RequestStatusEnum.DRAFT;
import static com.example.testtaskforvitasoftapplication.entity.enumClasses.RequestStatusEnum.SUBMITTED;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    public Request createRequest(String content, UserEntity user) {
        Request request = new Request();
        request.setText(content);
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

    public RequestDTO getRequestById(Long requestId) {
        Optional<Request> optionalRequest = requestRepository.findById(requestId);
        if (optionalRequest.isPresent()) {
            return convertToDTO(optionalRequest.get());
        } else {
            throw new NoSuchElementException("Request not found with id " + requestId);
        }
    }

    private RequestDTO convertToDTO(Request requestEntity) {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setId(requestEntity.getId());
        requestDTO.setText(requestEntity.getText());

        return requestDTO;
    }

    public void deleteRequest(Long requestId) {
        requestRepository.deleteById(requestId);
    }

    public List<Request> getRequestsByUserId(Long id) {
        return requestRepository.findByUserId(id);
    }

    public RequestDTO updateRequest(Long requestId, RequestDTO requestDTO) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new NoSuchElementException("Request not found with id: " + requestId));
        request.setText(requestDTO.getText());
        request.setUpdatedAt(LocalDateTime.now());
        Request updatedRequest = requestRepository.save(request);
        return convertToDTO(updatedRequest);
    }


    public void submitRequest(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new NoSuchElementException("Request not found with id: " + requestId));
        if (!request.getStatus().equals(DRAFT)) {
            throw new IllegalStateException("Cannot submit request that is not in draft status.");
        }
        request.setStatus(SUBMITTED);
        requestRepository.save(request);
    }

    public Page<RequestDTO> getUserRequests(Long userId, Pageable pageable) {
        Page<Request> requests = requestRepository.findByUserId(userId, pageable); // Исправлено
        return requests.map(this::convertToDTO);
    }

    public RequestDTO createRequest(RequestDTO requestDTO) {
        UserEntity user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + requestDTO.getUserId()));

        Request request = new Request();
        request.setText(requestDTO.getText());
        request.setUser(user);
        request.setStatus(DRAFT);
        request.setCreatedAt(LocalDateTime.now());

        Request savedRequest = requestRepository.save(request);
        return convertToDTO(savedRequest);
    }

}
