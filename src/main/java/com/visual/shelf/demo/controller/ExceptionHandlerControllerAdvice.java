package com.visual.shelf.demo.controller;


import com.visual.shelf.demo.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {


    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse bookNotFound(BookNotFoundException exc) {
        String isbn = exc.getIsbn();
        return new ErrorResponse(String.format("Can't find book with isbn %s", isbn));
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse userNotFound(UserNotFoundException exc) {
        String userName = exc.getUserName();
        return new ErrorResponse(String.format("Can't find user with userName %s", userName));
    }

    @ExceptionHandler(DuplicateUserException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse duplicateUser(DuplicateUserException exc) {
        String userName = exc.getUserName();
        return new ErrorResponse(String.format("User already exists with user name %s", userName));
    }

    @ExceptionHandler(InvalidDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse userNotFound(InvalidDataException exc) {
        String errorMessage = exc.getFieldErrors().stream()
                .map(fieldError -> String.format("%s:%s", fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.joining("\n"));
        return new ErrorResponse(errorMessage);
    }
}
