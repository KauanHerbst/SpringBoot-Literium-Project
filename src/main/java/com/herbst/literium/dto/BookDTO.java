package com.herbst.literium.dto;

import com.herbst.literium.entities.Book;
import com.herbst.literium.entities.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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
public class BookDTO {
    private Long id;
    @NotBlank(message = "Name não pode ser vázio")
    private String name;
    @NotBlank(message = "Description não pode ser vázio")
    private String description;
    @NotBlank(message = "Year não pode ser vázio")
    private String year;
    @NotBlank(message = "Author não pode ser vázio")
    private String author;
    @Positive(message = "Preço não pode ser negatvo")
    private Double price;
    @NotBlank(message = "Image Url não pode ser vázio")
    private String imageUrl;
    private List<CategoryDTO> categories = new ArrayList<>();

    public BookDTO(Book book){
        this.id = book.getId();
        this.name = book.getName();
        this.description = book.getDescription();
        this.year = book.getYear();
        this.author = book.getAuthor();
        this.price = book.getPrice();
        this.imageUrl = book.getImageUrl();
        setCategories(book.getCategories());
        System.out.println(book.getCategories() != null);
    }
    public void setCategories(List<Category> categories){
        if(!categories.isEmpty()){
            categories.forEach(category -> this.categories.add(new CategoryDTO(category)));
        }
    }
}
