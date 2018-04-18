package com.visual.shelf.demo;

import com.visual.shelf.demo.api.client.BookClient;
import com.visual.shelf.demo.api.dto.mapper.BookResultSetMapper;
import com.visual.shelf.demo.db.entites.AuthorityLevel;
import com.visual.shelf.demo.db.entites.Book;
import com.visual.shelf.demo.db.entites.User;
import com.visual.shelf.demo.db.repository.BookRepository;
import com.visual.shelf.demo.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookClient bookClient;

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final Long ownerId = 123L;

        List<Book> books = Stream.of("9789793780146", "0596007736", "9781784398941")
                .map(bookClient::findBookByISBN)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(book ->
                        BookResultSetMapper.mapToBook(book, ownerId, book.getVolumeInfo().getIndustryIdentifiers().get(0).getIdentifier()))
                .collect(Collectors.toList());

        books.forEach(bookRepository::save);


        User admin = User.builder().authorityLevel(AuthorityLevel.ADMIN)
                .password("admin")
                .userName("admin").build();

        userRepository.save(admin);

        final Optional<User> retrievedAdmin = userRepository.findByUserName("admin");
        System.out.println(retrievedAdmin);
    }
}
