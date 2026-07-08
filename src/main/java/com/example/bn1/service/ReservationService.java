package com.example.bn1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bn1.entity.FinePayment;
import com.example.bn1.exception.ResourceNotFoundException;
import com.example.bn1.repository.FinePaymentRepository;

@Service
public class ReservationService {

    @Autowired
    FinePaymentRepository finePaymentRepository;

    public FinePayment saveData(FinePayment data) {
        return finePaymentRepository.save(data);
    }

    public List<FinePayment> getAllData() {
        return finePaymentRepository.findAll();
    }

    public FinePayment getUserDetails(Long id) {

        return finePaymentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Fine Payment not found with id: " + id));
    }

    public FinePayment updateDatabase(Long id, FinePayment data) {

        FinePayment update = finePaymentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Fine Payment not found with id: " + id));

        update.setAmount(data.getAmount());
        update.setPaymentDate(data.getPaymentDate());

        return finePaymentRepository.save(update);
    }

    public FinePayment getDelete(Long id) {

        FinePayment delete = finePaymentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Fine Payment not found with id: " + id));

        finePaymentRepository.delete(delete);

        return delete;
    }
}