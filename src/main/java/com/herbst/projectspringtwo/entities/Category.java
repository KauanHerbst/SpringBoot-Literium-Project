package com.herbst.projectspringtwo.entities;

import com.herbst.projectspringtwo.dto.CategoryDTO;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_categories")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Category implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

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

    @ManyToMany(mappedBy = "categories")
    private List<Book> books = new ArrayList<>();
    public Category(CategoryDTO entity){
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
