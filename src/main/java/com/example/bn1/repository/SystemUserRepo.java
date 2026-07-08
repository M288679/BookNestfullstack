package com.example.bn1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bn1.entity.SystemUser;

public interface SystemUserRepo extends JpaRepository<SystemUser,Long> {
    Optional<SystemUser> findByEmail(String email);
} 