package com.herbst.literium.controllers;

import com.herbst.literium.dto.FavoriteResponseDTO;
import com.herbst.literium.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/insert/{userId}/{bookId}")
    public ResponseEntity<FavoriteResponseDTO> insertByUserId(@PathVariable Long userId, @PathVariable Long bookId){
        return ResponseEntity.ok(favoriteService.insertByUserId(userId, bookId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity findByUserId(@PathVariable Long userId, Pageable pageable){
        return ResponseEntity.ok(favoriteService.findByUserId(userId, pageable));
    }

    @DeleteMapping("/{userId}/{bookId}")
    public ResponseEntity deleteByUserId(@PathVariable Long userId, @PathVariable Long bookId){
        favoriteService.deleteByBookId(userId, bookId);
        return ResponseEntity.noContent().build();
    }
}
