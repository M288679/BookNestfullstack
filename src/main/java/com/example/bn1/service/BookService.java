package com.example.bn1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bn1.entity.LibraryBook;
import com.example.bn1.exception.BusinessValidationException;
import com.example.bn1.exception.ResourceNotFoundException;
import com.example.bn1.repository.LibraryBookRepository;

@Service
public class BookService {

    @Autowired
    LibraryBookRepository bookRepo;

    // Save Book
    public LibraryBook saveBook(LibraryBook data) {

        if (data.getTotalCopies() < 0) {
            throw new BusinessValidationException(
                    "Total copies cannot be negative.");
        }

        if (data.getAvailableCopies() > data.getTotalCopies()) {
            throw new BusinessValidationException(
                    "Available copies cannot exceed total copies.");
        }

        if (data.getIsbn() == null || data.getIsbn().isBlank()) {
            throw new BusinessValidationException(
                    "ISBN cannot be empty.");
        }

        return bookRepo.save(data);
    }

    // Get All Books
    public List<LibraryBook> getAllBooks() {
        return bookRepo.findAll();
    }

    // Get Book By Id
    public LibraryBook getBookById(Long id) {

        return bookRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with id: " + id));
    }

    // Update Book
    public LibraryBook updateBook(Long id, LibraryBook data) {

        LibraryBook viewData = bookRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with id: " + id));

        if (data.getTotalCopies() < 0) {
            throw new BusinessValidationException(
                    "Total copies cannot be negative.");
        }

        if (data.getAvailableCopies() > data.getTotalCopies()) {
            throw new BusinessValidationException(
                    "Available copies cannot exceed total copies.");
        }

        viewData.setIsbn(data.getIsbn());
        viewData.setTitle(data.getTitle());
        viewData.setAuthor(data.getAuthor());
        viewData.setCategory(data.getCategory());
        viewData.setTotalCopies(data.getTotalCopies());
        viewData.setAvailableCopies(data.getAvailableCopies());
        viewData.setShelfLocation(data.getShelfLocation());

        return bookRepo.save(viewData);
    }

    // Delete Book
    public LibraryBook deleteBook(Long id) {

        LibraryBook data = bookRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with id: " + id));

        if (data.getAvailableCopies() < data.getTotalCopies()) {
            throw new BusinessValidationException(
                    "Cannot delete a book that is currently issued.");
        }

        bookRepo.delete(data);

        return data;
    }
}

