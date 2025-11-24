package com.example.esdProject.service;

import com.example.esdProject.dto.ExchangeRequestDTO;
import com.example.esdProject.dto.PendingRequestDTO;
import com.example.esdProject.dto.UpdateExchangeRequestDTO;
import com.example.esdProject.entity.ExchangeRequest;
import com.example.esdProject.entity.Student;
import com.example.esdProject.repos.ExchangeRequestRepo;
import com.example.esdProject.repos.HostelRepo;
import com.example.esdProject.repos.StudentRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class ExchangeRequestService {
    private final ExchangeRequestRepo exchangeRequestRepo;
    private final StudentRepo studentRepo;
    private final HostelRepo hostelRepo;

    public ExchangeRequestService(ExchangeRequestRepo exchangeRequestRepo, StudentRepo studentRepo, HostelRepo hostelRepo) {
        this.exchangeRequestRepo = exchangeRequestRepo;
        this.studentRepo = studentRepo;
        this.hostelRepo = hostelRepo;
    }

    @Transactional
    public ExchangeRequest createExchangeRequest(ExchangeRequestDTO dto) {
        Student requester = studentRepo.findById(dto.getRequesterId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid requesterId: " + dto.getRequesterId()));
        Student target = studentRepo.findById(dto.getTargetStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid targetStudentId: " + dto.getTargetStudentId()));
        // need to check whether requester's is_request_active is false or true
        // if true, throw error
        if(requester.getIsRequestActive()) {
            throw new IllegalArgumentException("Requester already has an active request");
        }
        studentRepo.markRequestActive(requester.getStudent_id());

        ExchangeRequest req = new ExchangeRequest(requester, target);
        return exchangeRequestRepo.save(req);
    }

    @Transactional
    public void updateExchangeRequest(UpdateExchangeRequestDTO dto) {
        int id = dto.getRequestId();
        ExchangeRequest.ExchangeStatus status = dto.getStatus();
        ExchangeRequest req = exchangeRequestRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid exchangeRequestId: " + id));
        // hostel table me: target id par jayega requester id
        // aur fir requester id par jayega target id
        Integer requester_id = req.getRequester().getStudent_id();
        Integer target_id = req.getTarget_student().getStudent_id();

        if (status == ExchangeRequest.ExchangeStatus.APPROVED) {
            exchangeRequestRepo.updateExchangeRequest(dto);
            hostelRepo.clearStudentByStudentId(requester_id);
            hostelRepo.setStudentIdWhereStudentId(target_id, requester_id);
            hostelRepo.setStudentIdWhereNull(target_id);
            studentRepo.markRequestInactive(requester_id);
        }
        else if (status == ExchangeRequest.ExchangeStatus.REJECTED) {
            exchangeRequestRepo.updateExchangeRequest(dto);
            studentRepo.markRequestInactive(requester_id);
        }
        else {
            throw new IllegalArgumentException("Invalid status: " + status);
        }

    }
    public List<ExchangeRequest> getAllPendingRequests(PendingRequestDTO req){
        return exchangeRequestRepo.findAllByTargetId(req.getStudentId());
    }
    public List<ExchangeRequest> getExchangeRequestHistory(PendingRequestDTO req) {
        return exchangeRequestRepo.findAllByRequesterId(req.getStudentId());
    }
}
