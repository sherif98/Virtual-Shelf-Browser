package com.visual.shelf.demo.api.client;


import com.visual.shelf.demo.api.dto.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookClientTests {


    @Autowired
    private BookClient bookClient;

    @Test
    public void findBookByISBNTest() {
        Optional<Item> book = bookClient.findBookByISBN("0596007736");
        assertTrue(book.isPresent());
        assertEquals("0596007736", book.get().getVolumeInfo().getIndustryIdentifiers().get(0).getIdentifier());
    }

    @Test
    public void findBooksByTitle() {
        List<Item> books = bookClient.findBooksByTitle("Java");
        boolean containsQuery = books.stream().allMatch(book -> book.getVolumeInfo().getTitle().contains("Java"));
        assertTrue(containsQuery);
    }
}
