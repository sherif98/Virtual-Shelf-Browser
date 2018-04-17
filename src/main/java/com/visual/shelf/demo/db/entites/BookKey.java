package com.visual.shelf.demo.db.entites;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookKey implements Serializable {

    private String isbn;
    private Long ownerId;

}
