package com.example.bn1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bn1.entity.BookIssueRecord;
import com.example.bn1.exception.BusinessValidationException;
import com.example.bn1.exception.ResourceNotFoundException;
import com.example.bn1.repository.BookIssueRecordRepository;

@Service
public class CirculationService {

    @Autowired
    BookIssueRecordRepository circulationRepo;

    // Save Data
    public BookIssueRecord saveData(BookIssueRecord data) {

        if (data.getFineAmount() != null &&
                data.getFineAmount().doubleValue() < 0) {

            throw new BusinessValidationException(
                    "Fine amount cannot be negative.");
        }

        if (data.getIssueDate() != null &&
                data.getDueDate() != null &&
                data.getDueDate().isBefore(data.getIssueDate())) {

            throw new BusinessValidationException(
                    "Due date cannot be before issue date.");
        }

        return circulationRepo.save(data);
    }

    // Get All Data
    public List<BookIssueRecord> getAllData() {
        return circulationRepo.findAll();
    }

    // Get By Id
    public BookIssueRecord getUserDetails(Long id) {

        return circulationRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book Issue Record not found with id: " + id));
    }

    // Update Data
    public BookIssueRecord updateDatabase(Long id, BookIssueRecord data) {

        BookIssueRecord update = circulationRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book Issue Record not found with id: " + id));

        if (data.getFineAmount() != null &&
                data.getFineAmount().doubleValue() < 0) {

            throw new BusinessValidationException(
                    "Fine amount cannot be negative.");
        }

        if (data.getIssueDate() != null &&
                data.getDueDate() != null &&
                data.getDueDate().isBefore(data.getIssueDate())) {

            throw new BusinessValidationException(
                    "Due date cannot be before issue date.");
        }

        update.setIssueDate(data.getIssueDate());
        update.setDueDate(data.getDueDate());
        update.setReturnDate(data.getReturnDate());
        update.setFineAmount(data.getFineAmount());

        return circulationRepo.save(update);
    }

    // Delete Data
    public BookIssueRecord getDelete(Long id) {

        BookIssueRecord delete = circulationRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book Issue Record not found with id: " + id));

        if (delete.getReturnDate() == null) {

            throw new BusinessValidationException(
                    "Cannot delete a book issue record before returning the book.");
        }

        circulationRepo.delete(delete);

        return delete;
    }
}