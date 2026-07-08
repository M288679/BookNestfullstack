package com.example.bn1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bn1.entity.BookIssueRecord;

public interface BookIssueRecordRepository extends JpaRepository<BookIssueRecord, Long> {

}