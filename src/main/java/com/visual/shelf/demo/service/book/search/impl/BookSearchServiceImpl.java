package com.visual.shelf.demo.service.book.search.impl;

import com.visual.shelf.demo.db.entites.Book;
import com.visual.shelf.demo.db.repository.BookRepository;
import com.visual.shelf.demo.service.book.search.api.BookSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookSearchServiceImpl implements BookSearchService {

    @Autowired
    private BookRepository bookRepository;


    @Override
    public Optional<Book> findByISBN(String isbn) {
        return bookRepository.findByKey_Isbn(isbn);
    }


    @Override
    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> findByOwnerId(Long ownerId) {
        return bookRepository.findByKey_OwnerId(ownerId);
    }
}
