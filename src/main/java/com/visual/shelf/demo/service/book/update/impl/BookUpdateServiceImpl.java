package com.visual.shelf.demo.service.book.update.impl;

import com.visual.shelf.demo.db.entites.Book;
import com.visual.shelf.demo.db.entites.BookKey;
import com.visual.shelf.demo.db.repository.BookRepository;
import com.visual.shelf.demo.service.book.update.api.BookUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookUpdateServiceImpl implements BookUpdateService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Optional<Book> updateBook(Book book) {
        BookKey key = book.getKey();
        Optional<Book> bookToBeEdited = bookRepository.findById(key);
        return bookToBeEdited.flatMap(bookEntity -> Optional.of(bookRepository.save(book)));
    }

    @Override
    public boolean deleteBook(String isbn, Long ownerId) {
        BookKey key = BookKey.builder().isbn(isbn).ownerId(ownerId).build();
        boolean exists = bookRepository.existsById(key);
        if (exists) {
            bookRepository.deleteById(key);
            return true;
        }
        return false;
    }
}
