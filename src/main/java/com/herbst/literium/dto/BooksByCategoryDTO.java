package com.herbst.literium.dto;

import com.herbst.literium.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BooksByCategoryDTO {
    private List<BookDTO> books = new ArrayList<>();

}
