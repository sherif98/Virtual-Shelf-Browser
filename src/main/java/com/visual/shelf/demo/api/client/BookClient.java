package com.visual.shelf.demo.api.client;

import com.visual.shelf.demo.api.dto.Item;

import java.util.List;
import java.util.Optional;

public interface BookClient {

    Optional<Item> findBookByISBN(String isbn);

    List<Item> findBooksByTitle(String title);

}
