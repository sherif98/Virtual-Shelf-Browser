package com.visual.shelf.demo.db.repository;

import com.visual.shelf.demo.api.client.BookClient;
import com.visual.shelf.demo.api.dto.Item;
import com.visual.shelf.demo.api.dto.mapper.BookResultSetMapper;
import com.visual.shelf.demo.db.entites.Book;
import com.visual.shelf.demo.db.entites.BookKey;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTests {

    @Autowired
    private BookClient bookClient;

    @Autowired
    private BookRepository bookRepository;

    @Before
    public void clearDataBefore() {
        bookRepository.deleteAll();
    }

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

    @Test
    public void retrieveBookByISBN() {
        final String isbn = "0596007736";
        final Long ownerId = 123L;


        Optional<Item> book = bookClient.findBookByISBN(isbn);
        assertTrue(book.isPresent());
        Book bookEntity = BookResultSetMapper.mapToBook(book.get(), ownerId, isbn);

        bookRepository.save(bookEntity);


        Optional<Book> retrievedBook = bookRepository.findByKey_Isbn(isbn);
        assertTrue(retrievedBook.isPresent());

        assertEquals("Java in a Nutshell", retrievedBook.get().getTitle());
    }

    @Test
    public void retrieveBookByOwnerId() {
        final Long ownerId = 123L;

        List<Book> books = Stream.of("9789793780146", "0596007736", "9781784398941")
                .map(bookClient::findBookByISBN)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(book ->
                        BookResultSetMapper.mapToBook(book, ownerId, book.getVolumeInfo().getIndustryIdentifiers().get(0).getIdentifier()))
                .collect(Collectors.toList());

        books.forEach(bookRepository::save);


        List<Book> retrievedBooks = bookRepository.findByKey_OwnerId(ownerId);

        assertEquals(books.size(), retrievedBooks.size());
    }

    @After
    public void clearDataAfter() {
        bookRepository.deleteAll();
    }

}
