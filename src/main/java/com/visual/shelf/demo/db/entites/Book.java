package com.visual.shelf.demo.db.entites;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@ToString
public class Book {
    private String description;
    private List<String> authors;
    private String libraryLocation;
    private Category category;
}
