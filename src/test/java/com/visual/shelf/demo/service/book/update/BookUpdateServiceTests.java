package com.visual.shelf.demo.service.book.update;


import com.visual.shelf.demo.db.entites.Book;
import com.visual.shelf.demo.db.entites.BookKey;
import com.visual.shelf.demo.db.repository.BookRepository;
import com.visual.shelf.demo.service.book.update.api.BookUpdateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookUpdateServiceTests {

    @Autowired
    private BookUpdateService bookUpdateService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void updateOwnedBook() {
        final String isbn = "0596007736";
        final Long ownerId = 123L;

        BookKey key = BookKey.builder().isbn(isbn).ownerId(ownerId).build();
        Book oldBook = Book.builder()
                .title("old_title")
                .description("old_description")
                .key(key)
                .build();

        bookRepository.save(oldBook);

        Book newBook = Book.builder()
                .title("new_title")
                .description("new_description")
                .key(key)
                .build();

        bookUpdateService.updateBook(newBook);

        Optional<Book> updatedBook = bookRepository.findById(key);

        assertTrue(updatedBook.isPresent());
        assertEquals(newBook.getTitle(), updatedBook.get().getTitle());
        assertEquals(newBook.getDescription(), updatedBook.get().getDescription());
    }

    @Test
    public void updateNotOwnedBook() {
        final String isbn = "0596007736";
        final Long ownerId = 123L;

        BookKey key = BookKey.builder().isbn(isbn).ownerId(ownerId).build();
        Book oldBook = Book.builder()
                .title("old_title")
                .description("old_description")
                .key(key)
                .build();

        bookRepository.save(oldBook);

        key.setOwnerId(456L);

        Book newBook = Book.builder()
                .title("new_title")
                .description("new_description")
                .key(key)
                .build();

        Optional<Book> book = bookUpdateService.updateBook(newBook);
        assertFalse(book.isPresent());

        key.setOwnerId(ownerId);
        Optional<Book> updatedBook = bookRepository.findById(key);

        assertTrue(updatedBook.isPresent());
        assertEquals(oldBook.getTitle(), updatedBook.get().getTitle());
        assertEquals(oldBook.getDescription(), updatedBook.get().getDescription());

    }

    @Test
    public void deleteOwnedBook() {
        final String isbn = "0596007736";
        final Long ownerId = 123L;

        BookKey key = BookKey.builder().isbn(isbn).ownerId(ownerId).build();
        Book book = Book.builder()
                .title("title")
                .description("description")
                .key(key)
                .build();

        bookRepository.save(book);


        assertTrue(bookUpdateService.deleteBook(isbn, ownerId));

        Optional<Book> updatedBook = bookRepository.findById(key);

        assertFalse(updatedBook.isPresent());
    }

    @Test
    public void deleteNotOwnedBook() {
        final String isbn = "0596007736";
        final Long ownerId = 123L;

        BookKey key = BookKey.builder().isbn(isbn).ownerId(ownerId).build();
        Book book = Book.builder()
                .title("title")
                .description("description")
                .key(key)
                .build();

        bookRepository.save(book);


        assertFalse(bookUpdateService.deleteBook(isbn, 456L));

        Optional<Book> updatedBook = bookRepository.findById(key);

        assertTrue(updatedBook.isPresent());

    }

}
