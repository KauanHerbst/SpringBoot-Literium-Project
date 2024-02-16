package com.herbst.literium.entities;

import com.herbst.literium.dto.RoleDTO;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String authority;

    public Role(RoleDTO entity){
        this.id = entity.getId();
        this.authority = entity.getAuthority();
    }

}
