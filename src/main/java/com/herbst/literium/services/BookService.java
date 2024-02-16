package com.herbst.literium.services;

import com.herbst.literium.dto.BookDTO;
import com.herbst.literium.dto.BooksByCategoryDTO;
import com.herbst.literium.dto.CategoryDTO;
import com.herbst.literium.entities.Book;
import com.herbst.literium.entities.Category;
import com.herbst.literium.repositories.BookRepository;
import com.herbst.literium.repositories.CategoryRepository;
import com.herbst.literium.repositories.FavoriteRepository;
import com.herbst.literium.services.exceptions.DataBaseIntegrityViolationException;
import com.herbst.literium.services.exceptions.EntityIdNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public BookDTO store(BookDTO entity) {
       List<Category> categories = new ArrayList<>();
       entity.getCategories().stream().map(CategoryDTO::getId)
               .map(categoryService::findById)
               .filter(Objects::nonNull)
               .map(Category::new)
               .map(category -> entityManager.merge(category))
               .forEach(categories::add);
       entity.getCategories().clear();

        Book book = new Book(entity);
        book.getCategories().addAll(categories);
        book = bookRepository.save(book);
        return new BookDTO(book);
    }
    @Transactional(readOnly = true)
    public Page<BookDTO> findAllPage(Pageable pageable) {
        Page<Book> pagesBooks = bookRepository.findAll(pageable);
        return pagesBooks.map(book -> new BookDTO(book));
    }
    @Transactional(readOnly = true)
    public BookDTO findById(Long id) {
        Optional<Book> entity = bookRepository.findById(id);
        Book book = entity.orElseThrow(() -> new EntityIdNotFoundException(id));
        return new BookDTO(book);
    }

    public Page<BookDTO> findBookContaining(Pageable pageable, String name){
        Page<Book> books = bookRepository.findByNameIgnoreCaseContaining(name, pageable);
        return books.map(book -> new BookDTO(book));
    }

    @Transactional
    public void delete(Long id) {
       try {
           Optional<Book> book = bookRepository.findById(id);
           if(book == null) throw new EntityIdNotFoundException(id);
           book.get().getCategories().clear();
           favoriteRepository.deleteByBookId(id);
           bookRepository.save(book.get());
           bookRepository.deleteById(id);
       }catch (DataIntegrityViolationException e){
           throw new DataBaseIntegrityViolationException(id);
       }
    }

    @Transactional
    public BookDTO update(Long id, BookDTO updateEntity) {
        try{
            Book entity = bookRepository.getReferenceById(id);
            updateData(entity, updateEntity);
            entity = bookRepository.save(entity);
            return new BookDTO(entity);
        }catch (EntityNotFoundException e){
            throw new EntityIdNotFoundException(id);
        }
    }

    public Page<BookDTO> findBooksByCategory(Pageable pageable, Long categoryId){
        Page<Book> books = bookRepository.findBookIdsByCategoryId(categoryId, pageable);
        return books.map(BookDTO::new);
    }
    private void updateData(Book entity, BookDTO updateEntity){
        entity.setName(updateEntity.getName());
        entity.setAuthor(updateEntity.getAuthor());
        entity.setDescription(updateEntity.getDescription());
        entity.setYear(updateEntity.getYear());
        entity.setPrice(updateEntity.getPrice());
        entity.setImageUrl(updateEntity.getImageUrl());
        entity.getCategories().clear();
        if(updateEntity.getCategories() != null){
            updateEntity.getCategories().forEach(categoryDTO -> entity.getCategories().add(new Category(categoryDTO)));
        }
    }
}
