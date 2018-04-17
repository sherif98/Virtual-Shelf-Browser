package com.visual.shelf.demo.db.repository;

import com.visual.shelf.demo.api.client.BookClient;
import com.visual.shelf.demo.api.dto.Item;
import com.visual.shelf.demo.api.dto.mapper.BookResultSetMapper;
import com.visual.shelf.demo.db.entites.Book;
import com.visual.shelf.demo.db.entites.BookKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTests {

    @Autowired
    private BookClient bookClient;

    @Autowired
    private BookRepository bookRepository;


    @Test
    public void persistAndRetrieveBook() {
        final String isbn = "0596007736";
        final Long ownerId = 123L;


        Optional<Item> book = bookClient.findBookByISBN(isbn);
        assertTrue(book.isPresent());
        Book bookEntity = BookResultSetMapper.mapToBook(book.get(), ownerId, isbn);

        bookRepository.save(bookEntity);

        Optional<Book> retrievedBook = bookRepository.findById(BookKey.builder().ownerId(ownerId).isbn(isbn).build());
        assertTrue(retrievedBook.isPresent());

        assertEquals("Java in a Nutshell", retrievedBook.get().getTitle());
    }
}
