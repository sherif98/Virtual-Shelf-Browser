package com.visual.shelf.demo.service.book.add.api;

import com.visual.shelf.demo.db.entites.Book;

import java.util.Optional;

public interface BookAdditionService {

    Optional<Book> addBook(String isbn, Long ownerId, String libraryLocation);

}
