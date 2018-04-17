package com.visual.shelf.demo.service.book.search.api;

import com.visual.shelf.demo.db.entites.Book;

import java.util.List;
import java.util.Optional;

public interface BookSearchService {

    Optional<Book> findByISBN(String isbn);

    List<Book> findByTitle(String title);

    List<Book> findByOwnerId(Long ownderId);
}
