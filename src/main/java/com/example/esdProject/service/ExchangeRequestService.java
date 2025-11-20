package com.example.esdProject.service;

import com.example.esdProject.entity.ExchangeRequest;
import com.example.esdProject.repos.ExchangeRequestRepo;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ExchangeRequestService {
    private final ExchangeRequestRepo exchangeRequestRepo;
    public ExchangeRequestService(ExchangeRequestRepo exchangeRequestRepo) {
        this.exchangeRequestRepo = exchangeRequestRepo;
    }

    public ExchangeRequest createExchangeRequest(ExchangeRequest req) {
        return exchangeRequestRepo.save(req);
    }
}
