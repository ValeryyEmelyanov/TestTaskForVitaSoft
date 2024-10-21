package com.example.testtaskforvitasoftapplication.entity;

import com.example.testtaskforvitasoftapplication.entity.enumClasses.RoleEnum;

import javax.persistence.*;
import java.util.Set;
import lombok.*;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<RoleEnum> roles;

}
