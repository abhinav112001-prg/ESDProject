package com.example.esdProject.controller;

import java.util.*;

import com.example.esdProject.entity.ExchangeRequest;
import com.example.esdProject.service.ExchangeRequestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exchange")
public class ExchangeRequestController {

    private final ExchangeRequestService service;
    public ExchangeRequestController(ExchangeRequestService service) {
        this.service = service;
    }

    @PostMapping("/create-request")
    public ExchangeRequest createExchangeRequest(@RequestBody ExchangeRequest req){
        return service.createExchangeRequest(req);
    }
}
