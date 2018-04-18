package com.visual.shelf.demo.service.book.add;


import com.visual.shelf.demo.db.entites.AuthorityLevel;
import com.visual.shelf.demo.db.entites.Book;
import com.visual.shelf.demo.db.entites.User;
import com.visual.shelf.demo.db.repository.BookRepository;
import com.visual.shelf.demo.db.repository.UserRepository;
import com.visual.shelf.demo.service.book.add.api.BookAdditionService;
import org.junit.After;
import org.junit.Before;
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
public class BookAdditionServiceTests {

    @Autowired
    private BookAdditionService bookAdditionService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void clearDataBefore() {
        userRepository.deleteAll();
        bookRepository.deleteAll();
    }

    @Test
    public void bookShouldBePersisted() {
        final String isbn = "0596007736";
        final String location = "location";

        User admin = User.builder()
                .userName("admin")
                .password("admin")
                .authorityLevel(AuthorityLevel.ADMIN)
                .build();

        userRepository.save(admin);
        final Optional<User> retrievedAdmin = userRepository.findByUserName("admin");
        assertTrue(retrievedAdmin.isPresent());

        final Long ownerId = retrievedAdmin.get().getId();

        bookAdditionService.addBook(isbn, ownerId, location);

        Optional<Book> retrievedBook = bookRepository.findByKey_Isbn(isbn);
        assertTrue(retrievedBook.isPresent());

        assertEquals(isbn, retrievedBook.get().getKey().getIsbn());
        assertEquals(ownerId, retrievedBook.get().getKey().getOwnerId());
        assertEquals(location, retrievedBook.get().getLibraryLocation());
    }

    @Test
    public void normalUserCannotAddBooks() {
        final String isbn = "0596007736";
        final String location = "location";

        User normalUser = User.builder()
                .userName("normal")
                .password("normal")
                .authorityLevel(AuthorityLevel.NORMAL_USER)
                .build();

        userRepository.save(normalUser);
        final Optional<User> retrievedAdmin = userRepository.findByUserName("normal");
        assertTrue(retrievedAdmin.isPresent());

        final Long ownerId = retrievedAdmin.get().getId();

        bookAdditionService.addBook(isbn, ownerId, location);

        Optional<Book> retrievedBook = bookRepository.findByKey_Isbn(isbn);
        assertFalse(retrievedBook.isPresent());
    }

    @Test
    public void unregisteredUserCannotAddBooks() {
        final String isbn = "0596007736";
        final String location = "location";

        final Long ownerId = 123L;
        bookAdditionService.addBook(isbn, ownerId, location);

        Optional<Book> retrievedBook = bookRepository.findByKey_Isbn(isbn);
        assertFalse(retrievedBook.isPresent());
    }

    @After
    public void clearDataAfter() {
        bookRepository.deleteAll();
    }
}
