package com.example.bn1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bn1.entity.BookHoldrequest;
import com.example.bn1.exception.ResourceNotFoundException;
import com.example.bn1.repository.BookHoldRequest;

@Service
public class FineService {

    @Autowired
    BookHoldRequest fineRepo;

    public BookHoldrequest saveData(BookHoldrequest data) {
        return fineRepo.save(data);
    }

    public List<BookHoldrequest> getAllData() {
        return fineRepo.findAll();
    }

    public BookHoldrequest getUserDetails(Long id) {

        return fineRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book Hold Request not found with id: " + id));
    }

    public BookHoldrequest updateDatabase(Long id, BookHoldrequest data) {

        BookHoldrequest viewData = fineRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book Hold Request not found with id: " + id));

        viewData.setRequestDate(data.getRequestDate());

        return fineRepo.save(viewData);
    }

    public BookHoldrequest getDelete(Long id) {

        BookHoldrequest data = fineRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book Hold Request not found with id: " + id));

        fineRepo.delete(data);

        return data;
    }
}