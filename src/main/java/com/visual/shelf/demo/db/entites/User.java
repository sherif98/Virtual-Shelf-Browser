package com.visual.shelf.demo.db.entites;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "text")
    private String firstName;

    @Column(columnDefinition = "text")
    private String lastName;

    @Column(columnDefinition = "text")
    private String password;

    private AuthorityLevel authorityLevel;


}
