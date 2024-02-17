package com.herbst.literium.dto;

import com.herbst.literium.entities.Favorite;
import com.herbst.literium.entities.Order;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderResponseDTO {
    private Long id;
    private Long userId;
    private Long bookId;
    private Integer amount;
    private Double price;

    public OrderResponseDTO(Order order){
        this.id = order.getId();
        this.bookId = order.getBook().getId();
        this.userId = order.getUser().getId();
        this.amount = order.getAmount();
        this.price = order.getAmount() * order.getBook().getPrice();
    }
}
