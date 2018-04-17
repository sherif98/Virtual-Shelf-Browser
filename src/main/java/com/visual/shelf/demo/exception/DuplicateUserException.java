package com.visual.shelf.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DuplicateUserException extends RuntimeException {
    private String userName;
}
