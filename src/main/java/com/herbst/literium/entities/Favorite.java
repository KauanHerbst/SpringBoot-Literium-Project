package com.herbst.literium.entities;

import com.herbst.literium.dto.FavoriteDTO;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "tb_favorites")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Favorite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
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

    public Favorite(FavoriteDTO favoriteDTO){
        this.id = favoriteDTO.getId();
        this.user = favoriteDTO.getUser();
        this.book = favoriteDTO.getBook();
    }

}
