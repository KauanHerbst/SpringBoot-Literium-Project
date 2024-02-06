package com.herbst.projectspringtwo.dto;

import com.herbst.projectspringtwo.entities.Book;
import com.herbst.projectspringtwo.entities.Favorite;
import com.herbst.projectspringtwo.entities.User;
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
