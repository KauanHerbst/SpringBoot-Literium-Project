package com.herbst.projectspringtwo.dto;

import com.herbst.projectspringtwo.entities.Favorite;
import com.herbst.projectspringtwo.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    @NotBlank(message = "Campo não pode ser vazio")
    private String name;
    @Email(message = "Precisa ser um email valido")
    private String email;
    private List<RoleDTO> roles = new ArrayList<>();
    private List<FavoriteDTO > favorites = new ArrayList<>();
    private List<OrderDTO > orders = new ArrayList<>();
    public UserDTO(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        user.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
        user.getFavorites().forEach(favorite -> this.favorites.add(new FavoriteDTO(favorite)));
        user.getOrders().forEach(order -> this.orders.add(new OrderDTO(order)));
    }
}
