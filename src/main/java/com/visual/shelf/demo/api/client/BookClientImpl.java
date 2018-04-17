package com.visual.shelf.demo.api.client;

import com.visual.shelf.demo.api.dto.BookResultSet;
import com.visual.shelf.demo.api.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class BookClientImpl implements BookClient {

    private static final String FIND_BOOK_BY_ISBN = "https://www.googleapis.com/books/v1/volumes?q=isbn:";
    private static final String FIND_BOOKS_BY_TITLE = "https://www.googleapis.com/books/v1/volumes?q=";

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public Optional<Item> findBookByISBN(String isbn) {
        BookResultSet bookResultSet = restTemplate.getForObject(FIND_BOOK_BY_ISBN + isbn, BookResultSet.class);
        if (bookResultSet.getItems().isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(bookResultSet.getItems().get(0));
    }

    @Override
    public List<Item> findBooksByTitle(String title) {
        BookResultSet bookResultSet = restTemplate.getForObject(FIND_BOOKS_BY_TITLE + title, BookResultSet.class);
        return bookResultSet.getItems();
    }
}
