package com.visual.shelf.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookNotFoundException extends RuntimeException {

    private String isbn;

}
