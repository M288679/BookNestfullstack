package com.example.bn1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.bn1.entity.libacc;
import com.example.bn1.service.authservice;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/booknest")
public class authcontroller {

    @Autowired
    private authservice libSer;

    // ===========================
    // CREATE LIBRARY ACCOUNT
    // ===========================
    @PostMapping("/post")
    public libacc saveData(@Valid @RequestBody libacc data) {
        return libSer.saveData(data);
    }

    // ===========================
    // GET ALL LIBRARY ACCOUNTS
    // ===========================
    @GetMapping("/get")
    public List<libacc> getData() {
        return libSer.getAllData();
    }

    // ===========================
    // GET LIBRARY ACCOUNT BY ID
    // ===========================
    @GetMapping("/get/{id}")
    public libacc getUserData(@PathVariable Long id) {
        return libSer.getUserDetails(id);
    }

    // ===========================
    // UPDATE LIBRARY ACCOUNT
    // ===========================
    @PutMapping("/update/{id}")
    public libacc updateData(
            @PathVariable Long id,
            @Valid @RequestBody libacc data) {

        return libSer.updateDatabase(id, data);
    }

    // ===========================
    // DELETE LIBRARY ACCOUNT
    // ===========================
    @DeleteMapping("/delete/{id}")
    public libacc deleteData(@PathVariable Long id) {
        return libSer.getDelete(id);
    }

    // ===========================
    // GENERATE JWT TOKEN
    // ===========================
    @PostMapping("/token")
    public String generateToken(@RequestParam("mail") String gmail) {
        return libSer.generateToken(gmail);
    }

}