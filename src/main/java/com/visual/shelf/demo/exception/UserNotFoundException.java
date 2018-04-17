package com.visual.shelf.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserNotFoundException extends RuntimeException {

    private String userName;

}
