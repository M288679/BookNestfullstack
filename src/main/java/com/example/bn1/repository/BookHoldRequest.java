package com.example.bn1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bn1.entity.BookHoldrequest;

public interface BookHoldRequest extends JpaRepository<BookHoldrequest, Long> {

}
