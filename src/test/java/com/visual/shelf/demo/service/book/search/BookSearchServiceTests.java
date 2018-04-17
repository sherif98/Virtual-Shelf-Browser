package com.visual.shelf.demo.service.book.search;

import com.visual.shelf.demo.api.client.BookClient;
import com.visual.shelf.demo.api.dto.Item;
import com.visual.shelf.demo.api.dto.mapper.BookResultSetMapper;
import com.visual.shelf.demo.db.entites.Book;
import com.visual.shelf.demo.db.repository.BookRepository;
import com.visual.shelf.demo.service.book.search.api.BookSearchService;
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
public class BookSearchServiceTests {


    @Autowired
    private BookSearchService bookSearchService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookClient bookClient;


    @Test
    public void findBookByISBN() {
        final String isbn = "0596007736";
        final Long ownerId = 123L;

        Optional<Item> book = bookClient.findBookByISBN(isbn);
        assertTrue(book.isPresent());
        Book bookEntity = BookResultSetMapper.mapToBook(book.get(), ownerId, isbn);

        bookRepository.save(bookEntity);

        Optional<Book> retrievedBook = bookSearchService.findByISBN(isbn);
        assertTrue(retrievedBook.isPresent());

        assertEquals("Java in a Nutshell", retrievedBook.get().getTitle());

    }

    @Test
    public void findBookByTitle() {
        final String isbn = "0596007736";
        final Long ownerId = 123L;

        Optional<Item> book = bookClient.findBookByISBN(isbn);
        assertTrue(book.isPresent());
        Book bookEntity = BookResultSetMapper.mapToBook(book.get(), ownerId, isbn);

        bookRepository.save(bookEntity);

        List<Book> retrievedBook = bookSearchService.findByTitle("Java in a Nutshell");

        assertEquals(1, retrievedBook.size());

    }

    @Test
    public void findBookByOwnerId() {
        final Long ownerId = 123L;

        List<Book> books = Stream.of("9789793780146", "0596007736", "9781784398941")
                .map(bookClient::findBookByISBN)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(book ->
                        BookResultSetMapper.mapToBook(book, ownerId, book.getVolumeInfo().getIndustryIdentifiers().get(0).getIdentifier()))
                .collect(Collectors.toList());

        books.forEach(bookRepository::save);


        List<Book> retrievedBooks = bookSearchService.findByOwnerId(ownerId);

        assertEquals(books.size(), retrievedBooks.size());

    }
}
