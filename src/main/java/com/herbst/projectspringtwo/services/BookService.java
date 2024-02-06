package com.herbst.projectspringtwo.services;

import com.herbst.projectspringtwo.dto.BookDTO;
import com.herbst.projectspringtwo.entities.Book;
import com.herbst.projectspringtwo.entities.Category;
import com.herbst.projectspringtwo.repositories.BookRepository;
import com.herbst.projectspringtwo.services.exceptions.DataBaseIntegrityViolationException;
import com.herbst.projectspringtwo.services.exceptions.EntityIdNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Transactional
    public BookDTO store(BookDTO entity) {
        Book book = new Book(entity);
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

    @Transactional
    public void delete(Long id) {
       try {
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
    private void updateData(Book entity, BookDTO updateEntity){
        entity.setName(updateEntity.getName());
        entity.setAuthor(updateEntity.getAuthor());
        entity.setDescription(updateEntity.getDescription());
        entity.setYear(updateEntity.getYear());
        entity.setPrice_in_cents(updateEntity.getPrice_in_cents());
        entity.getCategories().clear();
        if(updateEntity.getCategories() != null){
            updateEntity.getCategories().forEach(categoryDTO -> entity.getCategories().add(new Category(categoryDTO)));
        }
    }
}
