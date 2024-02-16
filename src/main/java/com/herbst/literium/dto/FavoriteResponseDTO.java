package com.herbst.literium.dto;

import com.herbst.literium.entities.Book;
import com.herbst.literium.entities.Favorite;
import com.herbst.literium.entities.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FavoriteResponseDTO {
    private Long id;
    private Long userId;
    private Long bookId;

    public FavoriteResponseDTO(Favorite favorite){
        this.id = favorite.getId();
        this.bookId = favorite.getBook().getId();
        this.userId = favorite.getUser().getId();
    }
}
