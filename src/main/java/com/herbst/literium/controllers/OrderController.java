package com.herbst.literium.controllers;

import com.herbst.literium.dto.FavoriteResponseDTO;
import com.herbst.literium.dto.OrderAmountDTO;
import com.herbst.literium.dto.OrderResponseDTO;
import com.herbst.literium.services.FavoriteService;
import com.herbst.literium.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/insert/{userId}/{bookId}")
    public ResponseEntity<OrderResponseDTO> insertByUserId(@PathVariable Long userId, @PathVariable Long bookId,
                                                           @RequestBody OrderAmountDTO orderAmountDTO){
        return ResponseEntity.ok(orderService.insertByUserId(userId, bookId, orderAmountDTO));
    }

    @GetMapping("/{userId}")
    public ResponseEntity findByUserId(@PathVariable Long userId, Pageable pageable){
        return ResponseEntity.ok(orderService.findByUserId(userId, pageable));
    }

    @DeleteMapping("/{userId}/{bookId}")
    public ResponseEntity deleteByUserId(@PathVariable Long userId, @PathVariable Long bookId){
        orderService.deleteByBookId(userId, bookId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}/{bookId}")
    public ResponseEntity updateOrder(@PathVariable Long userId, @PathVariable Long bookId, @RequestBody OrderAmountDTO orderAmountDTO){
        orderService.updateOrder(bookId, userId, orderAmountDTO);
        return ResponseEntity.accepted().build();
    }
}
