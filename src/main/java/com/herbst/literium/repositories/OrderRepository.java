package com.herbst.literium.repositories;

import com.herbst.literium.entities.Favorite;
import com.herbst.literium.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByUserId(Long id, Pageable pageable);
    void deleteByUserIdAndBookId(Long userId, Long bookId);
    Order findByUserIdAndBookId(Long userId, Long bookId);
    void deleteByBookId(Long bookId);
}
