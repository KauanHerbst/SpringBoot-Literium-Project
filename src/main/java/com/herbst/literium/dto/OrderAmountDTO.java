package com.herbst.literium.dto;

import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderAmountDTO {
    @NotBlank(message = "Amount não pode ser vázio")
    @Negative(message = "Amount não pode ser Negativo")
    private Integer amount;
}
