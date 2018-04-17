package com.visual.shelf.demo.api.dto.mapper;


import com.visual.shelf.demo.api.client.BookClient;
import com.visual.shelf.demo.api.dto.Item;
import com.visual.shelf.demo.db.entites.Book;
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
public class BookResultSetMapperTests {


    @Autowired
    private BookClient bookClient;


    @Test
    public void mapBookResultSetToBook() {
        final String isbn = "0596007736";
        final Long ownerId = 123L;

        Optional<Item> book = bookClient.findBookByISBN(isbn);
        assertTrue(book.isPresent());
        Book bookEntity = BookResultSetMapper.mapToBook(book.get(), ownerId, isbn);
        assertEquals(isbn, bookEntity.getKey().getIsbn());
        assertEquals(ownerId, bookEntity.getKey().getOwnerId());
    }
}
