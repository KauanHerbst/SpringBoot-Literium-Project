package com.herbst.literium.controllers;

import com.herbst.literium.dto.BookDTO;
import com.herbst.literium.services.BookService;
import com.herbst.literium.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@WebMvcTest(BookController.class)
public class BookControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService service;
    @MockBean
    private CategoryService categoryService;
    private BookDTO expectedBook;
    private PageImpl<BookDTO> pageReturn;

    @BeforeEach
    void setUp(){
        expectedBook = new BookDTO();
        pageReturn = new PageImpl<>(List.of(expectedBook));

        Mockito.when(service.findAllPage(ArgumentMatchers.any())).thenReturn(pageReturn);
    }

    @Test
    public void findAllPageShouldReturnPage() throws Exception{

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
