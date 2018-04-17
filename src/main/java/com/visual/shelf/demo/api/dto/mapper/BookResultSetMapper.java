package com.visual.shelf.demo.api.dto.mapper;

import com.visual.shelf.demo.api.dto.Item;
import com.visual.shelf.demo.db.entites.Book;
import com.visual.shelf.demo.db.entites.BookKey;

public final class BookResultSetMapper {

    private BookResultSetMapper() {
    }

    public static Book mapToBook(Item book, long ownerId, String isbn) {
        BookKey bookKey = BookKey.builder().isbn(isbn).ownerId(ownerId).build();
        return Book.builder()
                .title(book.getVolumeInfo().getTitle())
                .description(book.getVolumeInfo().getDescription())
                .reviewsLink(book.getVolumeInfo().getInfoLink())
                .thumbnailLink(book.getVolumeInfo().getImageLinks().getThumbnail())
                .key(bookKey)
                .build();
    }
}
