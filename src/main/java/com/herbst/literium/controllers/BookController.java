package com.herbst.literium.controllers;

import com.herbst.literium.dto.BookDTO;
import com.herbst.literium.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping
    public ResponseEntity<BookDTO> store(@Valid @RequestBody BookDTO entity){
        entity = bookService.store(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(entity);
    }

    @GetMapping
    public ResponseEntity<Page<BookDTO>> findAllPage(Pageable pageable){
        return ResponseEntity.ok(bookService.findAllPage(pageable));

    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(bookService.findById(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable Long id,@Valid @RequestBody BookDTO entity){
        return ResponseEntity.ok(bookService.update(id, entity));
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity findByName(@PathVariable String name, Pageable pageable){
        return ResponseEntity.ok(bookService.findBookContaining(pageable, name));
    }
}
