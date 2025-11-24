package com.example.esdProject.controller;

import java.util.*;

import com.example.esdProject.dto.ExchangeRequestDTO;
import com.example.esdProject.dto.PendingRequestDTO;
import com.example.esdProject.dto.UpdateExchangeRequestDTO;
import com.example.esdProject.entity.ExchangeRequest;
import com.example.esdProject.service.ExchangeRequestService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exchange")
public class ExchangeRequestController {

    private final ExchangeRequestService service;
    public ExchangeRequestController(ExchangeRequestService service) {
        this.service = service;
    }

    @PostMapping("/create-request")
    public ExchangeRequest createExchangeRequest(@Validated @RequestBody ExchangeRequestDTO req){
        return service.createExchangeRequest(req);
    }

    @PutMapping("/update-request")
    public void updateExchangeRequest(@Validated @RequestBody UpdateExchangeRequestDTO req){
        service.updateExchangeRequest(req);
    }

    @PostMapping("/pending-requests")
    public  List<ExchangeRequest> getAllPendingRequests(@Validated @RequestBody PendingRequestDTO req){
        return service.getAllPendingRequests(req);
    }

    @PostMapping("/history")
    public  List<ExchangeRequest> getExchangeRequestHistory(@Validated @RequestBody PendingRequestDTO req){
        return service.getExchangeRequestHistory(req);
    }
}
