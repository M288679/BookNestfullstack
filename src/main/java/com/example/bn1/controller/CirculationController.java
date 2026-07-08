package com.example.bn1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.bn1.entity.BookIssueRecord;
import com.example.bn1.service.CirculationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bookissue")
public class CirculationController {

    @Autowired
    CirculationService circulationSer;

    @PostMapping("/issuepostbook")
    public BookIssueRecord saveData(
            @Valid @RequestBody BookIssueRecord data) {

        return circulationSer.saveData(data);
    }

    @GetMapping("/issuegetbook")
    public List<BookIssueRecord> getData() {
        return circulationSer.getAllData();
    }

    @GetMapping("/issuegetid/{id}")
    public BookIssueRecord getUserData(@PathVariable Long id) {
        return circulationSer.getUserDetails(id);
    }

    @PutMapping("/issueupdateid/{id}")
    public BookIssueRecord updateData(
            @PathVariable Long id,
            @Valid @RequestBody BookIssueRecord data) {

        return circulationSer.updateDatabase(id, data);
    }

    @DeleteMapping("/issuedeleteid/{id}")
    public BookIssueRecord deleteData(@PathVariable Long id) {
        return circulationSer.getDelete(id);
    }
}
