package com.visual.shelf.demo.db.entites;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @EmbeddedId
    private BookKey key;

    @Column(columnDefinition = "text")
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text")
    private String libraryLocation;

    @Column(columnDefinition = "text")
    private String reviewsLink;

    @Column(columnDefinition = "text")
    private String thumbnailLink;


}
