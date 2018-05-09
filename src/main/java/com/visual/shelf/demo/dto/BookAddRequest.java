package com.visual.shelf.demo.dto;

import lombok.*;
import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Valid
public class BookAddRequest {

    @ISBN
    private String isbn;

    private Long ownerId;

    @Length(min = 1, max = 1000)
    private String libraryLocation;
}
