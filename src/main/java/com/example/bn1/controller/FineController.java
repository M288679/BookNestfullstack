package com.example.bn1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.bn1.entity.BookHoldrequest;
import com.example.bn1.service.FineService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class FineController {

    @Autowired
    FineService fineSer;

    @PostMapping("/holdpost")
    public BookHoldrequest saveData(@RequestBody BookHoldrequest data) {
        return fineSer.saveData(data);
    }

    @GetMapping("/holdget")
    public List<BookHoldrequest> getData() {
        return fineSer.getAllData();
    }

    @GetMapping("/holdget/{id}")
    public BookHoldrequest getUserData(@PathVariable Long id) {
        return fineSer.getUserDetails(id);
    }

    @PutMapping("/holdupdate/{id}")
    public BookHoldrequest updateData(@PathVariable Long id,
            @RequestBody BookHoldrequest data) {
        return fineSer.updateDatabase(id, data);
    }

    @DeleteMapping("/holddelete/{id}")
    public BookHoldrequest getDeleteData(@PathVariable Long id) {
        return fineSer.getDelete(id);
    }
}