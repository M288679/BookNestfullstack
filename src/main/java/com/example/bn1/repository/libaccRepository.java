package com.example.bn1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bn1.entity.libacc;

@Repository
public interface libaccRepository extends JpaRepository<libacc, Long> {

    Optional<libacc> findByEmail(String email);

}
