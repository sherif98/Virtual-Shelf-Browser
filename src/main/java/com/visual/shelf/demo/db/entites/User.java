package com.visual.shelf.demo.db.entites;

import lombok.*;
import org.hibernate.validator.constraints.Length;

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
    @Length(min = 5, max = 20)
    private String userName;

    @Column(columnDefinition = "text")
    @Length(min = 5, max = 20)
    private String password;

    private AuthorityLevel authorityLevel;


}
