package com.herbst.literium.services;

import com.herbst.literium.dto.FavoriteResponseDTO;
import com.herbst.literium.dto.OrderAmountDTO;
import com.herbst.literium.dto.OrderResponseDTO;
import com.herbst.literium.entities.Book;
import com.herbst.literium.entities.Favorite;
import com.herbst.literium.entities.Order;
import com.herbst.literium.entities.User;
import com.herbst.literium.repositories.BookRepository;
import com.herbst.literium.repositories.OrderRepository;
import com.herbst.literium.repositories.UserRepository;
import com.herbst.literium.services.exceptions.EntityAlreadyExistsException;
import com.herbst.literium.services.exceptions.EntityIdNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;
    public Page<OrderResponseDTO> findByUserId(Long userId, Pageable pageable){
        verifyUserId(userId);
        Page<Order> orders = orderRepository.findByUserId(userId, pageable);
        return orders.map(order -> new OrderResponseDTO(order));
    }


    public OrderResponseDTO insertByUserId(Long userId, Long bookId, OrderAmountDTO orderAmountDTO){
        User user = verifyUserId(userId);
        Book book = verifyBookId(bookId);
        Order orderVerify = orderRepository.findByUserIdAndBookId(userId, bookId);
        if(orderVerify != null) throw new EntityAlreadyExistsException();
        Order order = new Order();
        order.setUser(user);
        order.setBook(book);
        order.setAmount(orderAmountDTO.getAmount());
        order.setPrice(orderAmountDTO.getAmount() * book.getPrice());
        orderRepository.save(order);
        return new OrderResponseDTO(order);
    }

    public void updateOrder(Long bookId, Long userId, OrderAmountDTO orderAmountDTO){
        Order order = orderRepository.findByUserIdAndBookId(userId, bookId);
        Book book = verifyBookId(bookId);
        if(order == null) throw new EntityIdNotFoundException(bookId);
        order.setAmount(orderAmountDTO.getAmount());
        order.setPrice(orderAmountDTO.getAmount() * book.getPrice());
        orderRepository.save(order);
    }

    @Transactional
    public void deleteByBookId(Long userId, Long bookId){
        verifyUserId(userId);
        verifyBookId(bookId);
        orderRepository.deleteByUserIdAndBookId(userId, bookId);
    }
    private User verifyUserId(Long userId){
        User user = userRepository.getReferenceById(userId);
        if(!(user != null)) throw new EntityIdNotFoundException(userId);
        return user;
    }

    private Book verifyBookId(Long bookId){
        Optional<Book> optionalBookbook = bookRepository.findById(bookId);
        Book book = optionalBookbook.get();
        if(!(book != null)) throw new EntityIdNotFoundException(bookId);
        return book;
    }
}
