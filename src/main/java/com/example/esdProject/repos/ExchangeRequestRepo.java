package com.example.esdProject.repos;

import com.example.esdProject.entity.ExchangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ExchangeRequestRepo extends JpaRepository<ExchangeRequest, Integer> {
}
