package com.example.carros.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class User {

    @EqualsAndHashCode.Include
    @Id
    private Integer id;

    private String nome;
    private String email;
    private String login;
    private String senha;
}
