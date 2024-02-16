package com.herbst.literium.controllers;

import com.herbst.literium.repositories.BookRepository;
import com.herbst.literium.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/categories/{categoryId}/books")
public class BookCategoryController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity findByCategory(@PathVariable Long categoryId, Pageable pageable){
        return ResponseEntity.ok(bookService.findBooksByCategory(pageable, categoryId));
    }
}
