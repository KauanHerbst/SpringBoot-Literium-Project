package com.herbst.projectspringtwo.dto;

import com.herbst.projectspringtwo.entities.Book;
import com.herbst.projectspringtwo.entities.Order;
import com.herbst.projectspringtwo.entities.User;
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
