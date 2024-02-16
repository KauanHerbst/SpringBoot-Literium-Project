package com.herbst.literium.repositories;

import com.herbst.literium.entities.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Page<Favorite> findByUserId(Long id, Pageable pageable);
    void deleteByUserIdAndBookId(Long userId, Long bookId);
    Favorite findByUserIdAndBookId(Long userId, Long bookId);
    void deleteByBookId(Long bookId);
}
