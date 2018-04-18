package com.visual.shelf.demo.db.repository;


import com.visual.shelf.demo.db.entites.AuthorityLevel;
import com.visual.shelf.demo.db.entites.User;
import org.junit.After;
import org.junit.Before;
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
public class UserRepositoryTests {


    @Autowired
    private UserRepository userRepository;

    @Before
    public void clearDataBefore() {
        userRepository.deleteAll();
    }

    @Test
    public void persistAndRetrieveUser() {
        final String userName = "userName";
        final String password = "pass";
        User user = User.builder()
                .userName(userName)
                .password(password)
                .authorityLevel(AuthorityLevel.ADMIN)
                .build();

        User savedUser = userRepository.save(user);
        Long id = savedUser.getId();

        Optional<User> retrievedUser = userRepository.findById(id);
        assertTrue(retrievedUser.isPresent());
        assertEquals(userName, retrievedUser.get().getUserName());
        assertEquals(password, retrievedUser.get().getPassword());
        assertEquals(AuthorityLevel.ADMIN, retrievedUser.get().getAuthorityLevel());
    }

    @After
    public void clearDataAfter() {
        userRepository.deleteAll();
    }
}
