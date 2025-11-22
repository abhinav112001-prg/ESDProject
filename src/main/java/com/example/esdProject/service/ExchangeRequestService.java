package com.example.esdProject.service;

import com.example.esdProject.dto.ExchangeRequestDTO;
import com.example.esdProject.entity.ExchangeRequest;
import com.example.esdProject.entity.Student;
import com.example.esdProject.repos.ExchangeRequestRepo;
import com.example.esdProject.repos.StudentRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class ExchangeRequestService {
    private final ExchangeRequestRepo exchangeRequestRepo;
    private final StudentRepo studentRepo;

    public ExchangeRequestService(ExchangeRequestRepo exchangeRequestRepo, StudentRepo studentRepo) {
        this.exchangeRequestRepo = exchangeRequestRepo;
        this.studentRepo = studentRepo;
    }

    @Transactional
    public ExchangeRequest createExchangeRequest(ExchangeRequestDTO dto) {
        Student requester = studentRepo.findById(dto.getRequesterId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid requesterId: " + dto.getRequesterId()));
        Student target = studentRepo.findById(dto.getTargetStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid targetStudentId: " + dto.getTargetStudentId()));

        studentRepo.markRequestActive(requester.getStudent_id());

        ExchangeRequest req = new ExchangeRequest(requester, target);
        return exchangeRequestRepo.save(req);
    }
}
