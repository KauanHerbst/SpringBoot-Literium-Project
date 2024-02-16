package com.herbst.literium.repositories;

import com.herbst.literium.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b JOIN b.categories c WHERE c.id = :categoryId")
    Page<Book> findBookIdsByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);

    Page<Book> findByNameIgnoreCaseContaining(String name, Pageable pageable);
}
