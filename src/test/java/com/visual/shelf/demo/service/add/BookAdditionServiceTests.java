package com.visual.shelf.demo.service.add;


import com.visual.shelf.demo.db.entites.Book;
import com.visual.shelf.demo.db.repository.BookRepository;
import com.visual.shelf.demo.service.book.add.api.BookAdditionService;
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
public class BookAdditionServiceTests {

    @Autowired
    private BookAdditionService bookAdditionService;

    @Autowired
    private BookRepository bookRepository;


    @Test
    public void bookShouldBePersisted() {
        final String isbn = "0596007736";
        final Long ownerId = 123L;
        final String location = "location";

        bookAdditionService.addBook(isbn, ownerId, location);

        Optional<Book> retrievedBook = bookRepository.findByKey_Isbn(isbn);
        assertTrue(retrievedBook.isPresent());

        assertEquals(isbn, retrievedBook.get().getKey().getIsbn());
        assertEquals(ownerId, retrievedBook.get().getKey().getOwnerId());
        assertEquals(location, retrievedBook.get().getLibraryLocation());
    }
}
