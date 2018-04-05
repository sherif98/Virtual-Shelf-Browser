package com.visual.shelf.demo.db.entites;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@ToString
public class Book {
    @Id
    private String isbn;
    private String description;
    private String libraryLocation;
    private Category category;
}
