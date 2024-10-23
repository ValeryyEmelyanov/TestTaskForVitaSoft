package com.example.testtaskforvitasoftapplication.entity;

import com.example.testtaskforvitasoftapplication.entity.enumClasses.RoleEnum;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;
import lombok.*;


@Entity
@Getter
@Setter
@Table(name = "UserEntity")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<RoleEnum> roles;

}
