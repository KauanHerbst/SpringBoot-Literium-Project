package com.herbst.literium.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.herbst.literium.entities.Book;
import com.herbst.literium.entities.Favorite;
import com.herbst.literium.entities.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FavoriteDTO {
    private Long id;
    private User user;
    private Book book;

    public FavoriteDTO(Favorite favorite){
        this.id = favorite.getId();
        this.user = favorite.getUser();
        this.book = favorite.getBook();
    }

}
