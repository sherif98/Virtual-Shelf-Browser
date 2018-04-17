package com.visual.shelf.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookAddRequest {

    private String isbn;
    private Long ownerId;
    private String libraryLocation;
}
