package com.example.bn1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.bn1.entity.LibraryBook;
import com.example.bn1.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/libbook")
public class BookController {

    @Autowired
    BookService bookSer;

    @PostMapping("/postbook")
    public LibraryBook saveBook(@Valid @RequestBody LibraryBook book) {
        return bookSer.saveBook(book);
    }

    @GetMapping("/getbook")
    public List<LibraryBook> getAllBooks() {
        return bookSer.getAllBooks();
    }

    @GetMapping("/getbookid/{id}")
    public LibraryBook getBookById(@PathVariable Long id) {
        return bookSer.getBookById(id);
    }

    @PutMapping("/update/{id}")
    public LibraryBook updateBook(@PathVariable Long id,
                                  @Valid @RequestBody LibraryBook book) {
        return bookSer.updateBook(id, book);
    }

    @DeleteMapping("/delete/{id}")
    public LibraryBook deleteBook(@PathVariable Long id) {
        return bookSer.deleteBook(id);
    }
}