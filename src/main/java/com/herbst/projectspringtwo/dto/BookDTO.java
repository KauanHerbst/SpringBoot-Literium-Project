package com.herbst.projectspringtwo.dto;

import com.herbst.projectspringtwo.entities.Book;
import com.herbst.projectspringtwo.entities.Category;
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
    @NotBlank(message = "Campo não pode ser vazio")
    private String name;
    @NotBlank(message = "Campo não pode ser vazio")
    private String description;
    private String year;
    @NotBlank(message = "Campo não pode ser vazio")
    private String author;
    @Positive(message = "Preço não pode ser negatvo")
    private Integer price_in_cents;
    private String imageUrl;
    private List<CategoryDTO> categories = new ArrayList<>();

    public BookDTO(Book book){
        this.id = book.getId();
        this.name = book.getName();
        this.description = book.getDescription();
        this.year = book.getYear();
        this.author = book.getAuthor();
        this.price_in_cents = book.getPrice_in_cents();
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
