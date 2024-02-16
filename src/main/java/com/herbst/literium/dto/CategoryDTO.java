package com.herbst.literium.dto;

import com.herbst.literium.entities.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Long id;
    @NotBlank(message = "Campo não pode ser vazio")
    private String name;

    public CategoryDTO(Category entite){
        this.id = entite.getId();
        this.name = entite.getName();
    }
}
