package com.visual.shelf.demo.service.book.update.api;

import com.visual.shelf.demo.db.entites.Book;

import java.util.Optional;

public interface BookUpdateService {

    Optional<Book> updateBook(Book book);

    boolean deleteBook(String isbn, Long ownerId);
}
