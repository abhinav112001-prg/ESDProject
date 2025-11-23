package com.example.esdProject.repos;

import com.example.esdProject.dto.UpdateExchangeRequestDTO;
import com.example.esdProject.entity.ExchangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ExchangeRequestRepo extends JpaRepository<ExchangeRequest, Integer> {
    @Modifying
    @Query("update ExchangeRequest e set e.status = :#{#dto.status} where e.id = :#{#dto.requestId}")
    public int updateExchangeRequest(@Param("dto") UpdateExchangeRequestDTO dto);
}
