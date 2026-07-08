package com.example.bn1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bn1.entity.FinePayment;

@Repository
public interface FinePaymentRepository extends JpaRepository<FinePayment, Long> {

}