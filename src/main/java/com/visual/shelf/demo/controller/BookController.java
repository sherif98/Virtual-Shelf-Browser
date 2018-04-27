package com.visual.shelf.demo.controller;

import com.visual.shelf.demo.db.entites.Book;
import com.visual.shelf.demo.dto.BookAddRequest;
import com.visual.shelf.demo.dto.BookDeleteRequest;
import com.visual.shelf.demo.exception.BookNotFoundException;
import com.visual.shelf.demo.service.book.add.api.BookAdditionService;
import com.visual.shelf.demo.service.book.search.api.BookSearchService;
import com.visual.shelf.demo.service.book.update.api.BookUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookSearchService bookSearchService;


    @Autowired
    private BookAdditionService bookAdditionService;

    @Autowired
    private BookUpdateService bookUpdateService;

    @RequestMapping(method = RequestMethod.GET, params = {"isbn", "ownerId"})
    public Book findBookById(@RequestParam("isbn") String isbn, @RequestParam("ownerId") Long ownerId) {
        return bookSearchService.findByKey(isbn, ownerId).orElseThrow(() -> new BookNotFoundException(isbn));
    }

    @RequestMapping(method = RequestMethod.GET, params = "title")
    public List<Book> findBookByTitle(@RequestParam("title") String title) {
        return bookSearchService.findByTitle(title);
    }

    @RequestMapping(method = RequestMethod.GET, params = "ownerId")
    public List<Book> findBookByOwnerId(@RequestParam("ownerId") Long ownerId) {
        return bookSearchService.findByOwnerId(ownerId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> findAllBooks() {
        return bookSearchService.findAllBooks();
    }

    @PostMapping
    public Book addBook(@RequestBody BookAddRequest bookAddRequest) {
        return bookAdditionService.addBook(bookAddRequest.getIsbn(),
                bookAddRequest.getOwnerId(),
                bookAddRequest.getLibraryLocation()).orElseThrow(() -> new BookNotFoundException(bookAddRequest.getIsbn()));
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        return bookUpdateService.updateBook(book)
                .orElseThrow(() -> new BookNotFoundException(book.getKey().getIsbn()));
    }

    @DeleteMapping
    public boolean deleteBook(@RequestBody BookDeleteRequest bookDeleteRequest) {
        return bookUpdateService.deleteBook(bookDeleteRequest.getIsbn(), bookDeleteRequest.getOwnerId());
    }


}
