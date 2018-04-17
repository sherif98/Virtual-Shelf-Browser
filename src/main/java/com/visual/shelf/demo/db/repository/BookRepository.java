package com.visual.shelf.demo.db.repository;

import com.visual.shelf.demo.db.entites.Book;
import com.visual.shelf.demo.db.entites.BookKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, BookKey> {


    Optional<Book> findByKey_Isbn(String isbn);

    List<Book> findByKey_OwnerId(Long ownerId);

    List<Book> findByTitle(String title);

}
