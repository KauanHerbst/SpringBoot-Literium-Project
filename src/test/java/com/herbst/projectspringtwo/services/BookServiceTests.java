package com.herbst.projectspringtwo.services;

import com.herbst.projectspringtwo.entities.Book;
import com.herbst.projectspringtwo.repositories.BookRepository;
import com.herbst.projectspringtwo.services.exceptions.DataBaseIntegrityViolationException;
import com.herbst.projectspringtwo.services.exceptions.EntityIdNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class BookServiceTests {
    @InjectMocks
    private BookService service;
    @Mock
    private BookRepository repository;
    private Long existId;
    private Long nonExistsId;
    private Book expectedObject;
    private Long dependentId;
    private PageImpl<Book> pageReturn;
    @BeforeEach
    void setUp (){
        existId = 1l;
        nonExistsId = 1000l;
        dependentId = 2000L;
        expectedObject = new Book();
        pageReturn = new PageImpl<Book>(List.of(expectedObject));
        Mockito.doNothing().when(repository).deleteById(existId);
        Mockito.doThrow(EntityIdNotFoundException.class).when(repository).deleteById(nonExistsId);
        Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);

        Mockito.doReturn(Optional.of(expectedObject)).when(repository).findById(existId);
        Mockito.doThrow(EntityIdNotFoundException.class).when(repository).findById(nonExistsId);

        Mockito.doReturn(pageReturn).when(repository).findAll((Pageable) ArgumentMatchers.any());

    }

    @Test
    public void findAllShouldReturnPage(){
        Assertions.assertDoesNotThrow( () -> {
            service.findAllPage(ArgumentMatchers.any());
        });
        Mockito.verify(repository).findAll((Pageable) ArgumentMatchers.any());
    }

    @Test
    public void deleteShouldThrowDataBaseIntegrityViolationExceptionWhenIdDependent(){
        Assertions.assertThrows(DataBaseIntegrityViolationException.class, () -> {
            service.delete(dependentId);
        });
        Mockito.verify(repository).deleteById(dependentId);
    }


    @Test
    public void findShouldReturnExpectedObjectWhenIdExists(){
        Assertions.assertDoesNotThrow(() -> {
            service.findById(existId);
        });

        Mockito.verify(repository).findById(existId);
    }

    @Test
    public void findShouldThrowEntityIdNotFoundExceptionWhenIdDoesNotExists(){
        Assertions.assertThrows(EntityIdNotFoundException.class, () -> {
            service.findById(nonExistsId);
        });

        Mockito.verify(repository).findById(nonExistsId);
    }

    @Test
    public void deleteShouldDoNothingWhenIdExist(){
        Assertions.assertDoesNotThrow(() -> {
            service.delete(existId);
        });

        Mockito.verify(repository).deleteById(existId);
    }
}
