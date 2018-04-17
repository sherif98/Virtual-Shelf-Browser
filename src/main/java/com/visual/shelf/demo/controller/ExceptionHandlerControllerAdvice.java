package com.visual.shelf.demo.controller;


import com.visual.shelf.demo.exception.BookNotFoundException;
import com.visual.shelf.demo.exception.DuplicateUserException;
import com.visual.shelf.demo.exception.ErrorResponse;
import com.visual.shelf.demo.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
