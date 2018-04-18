package com.visual.shelf.demo.service.book.add.impl;

import com.visual.shelf.demo.api.client.BookClient;
import com.visual.shelf.demo.api.dto.Item;
import com.visual.shelf.demo.api.dto.mapper.BookResultSetMapper;
import com.visual.shelf.demo.db.entites.AuthorityLevel;
import com.visual.shelf.demo.db.entites.Book;
import com.visual.shelf.demo.db.entites.User;
import com.visual.shelf.demo.db.repository.BookRepository;
import com.visual.shelf.demo.db.repository.UserRepository;
import com.visual.shelf.demo.service.book.add.api.BookAdditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookAdditionServiceImpl implements BookAdditionService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookClient bookClient;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<Book> addBook(String isbn, Long ownerId, String libraryLocation) {
        Optional<Item> bookByISBN = bookClient.findBookByISBN(isbn);
        final Optional<User> requester = userRepository.findById(ownerId);
        if (!requester.isPresent() || requester.get().getAuthorityLevel().equals(AuthorityLevel.NORMAL_USER)) {
            return Optional.empty();
        }

        Optional<Book> bookToPersist = bookByISBN
                .map(item -> BookResultSetMapper.mapToBook(item, ownerId, isbn))
                .map(book -> {
                    book.setLibraryLocation(libraryLocation);
                    return book;
                });
        bookToPersist.ifPresent(bookRepository::save);
        return bookToPersist;
    }
}
