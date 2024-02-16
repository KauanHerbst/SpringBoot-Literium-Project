package com.herbst.literium.dto;

import com.herbst.literium.entities.Book;
import com.herbst.literium.entities.Order;
import com.herbst.literium.entities.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderDTO {
    private Long id;
    private User user;
    private Book book;

    public OrderDTO(Order order){
        this.id = order.getId();
        this.user = order.getUser();
        this.book = order.getBook();
    }
}
