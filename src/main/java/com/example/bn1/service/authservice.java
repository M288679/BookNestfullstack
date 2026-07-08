package com.example.bn1.service;

import java.security.Key;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bn1.entity.libacc;
import com.example.bn1.exception.BusinessValidationException;
import com.example.bn1.exception.ResourceNotFoundException;
import com.example.bn1.repository.libaccRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class authservice {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.exp}")
    private long exp;

    @Autowired
    private libaccRepository libRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ==========================
    // CREATE
    // ==========================
    public libacc saveData(libacc data) {

        if (data.getEmail() == null || data.getEmail().isBlank()) {
            throw new BusinessValidationException("Email cannot be empty.");
        }

        if (data.getPasswordHash() == null || data.getPasswordHash().isBlank()) {
            throw new BusinessValidationException("Password cannot be empty.");
        }

        if (data.getFullName() == null || data.getFullName().isBlank()) {
            throw new BusinessValidationException("Full Name cannot be empty.");
        }

        data.setPasswordHash(passwordEncoder.encode(data.getPasswordHash()));

        return libRepo.save(data);
    }

    // ==========================
    // GET ALL
    // ==========================
    public List<libacc> getAllData() {
        return libRepo.findAll();
    }

    // ==========================
    // GET BY ID
    // ==========================
    public libacc getUserDetails(Long id) {

        return libRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Library Account not found with ID : " + id));
    }

    // ==========================
    // UPDATE
    // ==========================
    public libacc updateDatabase(Long id, libacc data) {

        libacc old = libRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Library Account not found with ID : " + id));

        old.setFullName(data.getFullName());
        old.setEmail(data.getEmail());
        old.setActive(data.isActive());

        old.setPasswordHash(
                passwordEncoder.encode(data.getPasswordHash()));

        return libRepo.save(old);
    }

    // ==========================
    // DELETE
    // ==========================
    public libacc getDelete(Long id) {

        libacc data = libRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Library Account not found with ID : " + id));

        libRepo.delete(data);

        return data;
    }

    // ==========================
    // JWT TOKEN
    // ==========================
    public String generateToken(String email) {

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + exp))
                .signWith(getKeys())
                .compact();

    }

    // ==========================
    // SECRET KEY
    // ==========================
    private Key getKeys() {

        return Keys.hmacShaKeyFor(secret.getBytes());

    }

}
