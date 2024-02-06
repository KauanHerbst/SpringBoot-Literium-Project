package com.herbst.projectspringtwo.entities;

import com.herbst.projectspringtwo.dto.BookDTO;
import com.herbst.projectspringtwo.dto.CategoryDTO;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String year;
    private String author;
    private Integer price_in_cents;
    private String imageUrl;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;
    @PrePersist
    public void prePersist(){
        this.createdAt = Instant.now();
    }
    @PreUpdate
    public void preUpdate(){
        this.updatedAt = Instant.now();
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "book_category", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();

    public Book(BookDTO entity){
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.author = entity.getAuthor();
        this.year = entity.getYear();
        this.price_in_cents = entity.getPrice_in_cents();
        this.imageUrl = entity.getImageUrl();
        setCategories(entity.getCategories());
    }
    private void setCategories(List<CategoryDTO> categories){
        if(!categories.isEmpty()){
            categories.forEach(CategoryDTO -> this.categories.add(new Category(CategoryDTO)));
        }
    }


}
