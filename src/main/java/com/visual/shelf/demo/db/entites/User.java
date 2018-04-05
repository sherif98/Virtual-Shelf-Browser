package com.visual.shelf.demo.db.entites;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@EqualsAndHashCode
public class User {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private AuthorityLevel authorityLevel;
}
